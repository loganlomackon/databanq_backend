package com.rbc.databanqbackend.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.DeviceTransferHistory;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.BlockchainDeviceTransferDTO;
import com.rbc.databanqbackend.restful.dto.DeviceTransferDTO;
import com.rbc.databanqbackend.restful.dto.DeviceTransferHistoryFullDTO;
import com.rbc.databanqbackend.restful.dto.PairingDTO;
import com.rbc.databanqbackend.restful.dto.UserDTO;
import com.rbc.databanqbackend.restful.dto.UserDeviceDTO;
import com.rbc.databanqbackend.util.DateUtil;

@Service
public class DatabanqService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceTransferHistoryService historyService;
	@Autowired
	private HttpClientService httpClientService;
	
	public UserDeviceDTO pairing(PairingDTO inputDTO) throws BizException, Exception {
		String deviceDid = inputDTO.getDevice_did();
		String userDid = inputDTO.getUser_did();
		
		User user = userService.getByDid(userDid);
		if (user == null) {
			throw new BizException("Invalid user DID");
		}
		
		Device device = deviceService.getByDid(deviceDid);
		if (device == null) {
			//Create new device
			device = new Device();
			device.setDid(deviceDid);
			device.setMacAddress(inputDTO.getMac_address());
			device.setDeviceName(inputDTO.getDevice_name());
			device.setProductId(inputDTO.getProduct_id());
			if (inputDTO.getProduct_type() != null) {
				device.setProductType(Integer.valueOf(inputDTO.getProduct_type()));
			}
			Date now = new Date();
			Date warrantyDate = null;
			if (inputDTO.getWarranty_period() != null) {
				Integer warrantyPeriod = Integer.valueOf(inputDTO.getWarranty_period());
				device.setWarrantyPeriod(warrantyPeriod);
				
				Date today = DateUtil.getDay(now);
				warrantyDate = DateUtil.getDateFromDate(today, Calendar.DAY_OF_MONTH, warrantyPeriod+1);
				device.setWarrantyDate(warrantyDate);
			}
			device = deviceService.save(device);
			
			//Post to blockchain
			BlockchainDeviceTransferDTO toChainDTO = new BlockchainDeviceTransferDTO();
			toChainDTO.setType("device_transfer");
			toChainDTO.setDevice_did(deviceDid);
			toChainDTO.setTo_did(userDid);
			toChainDTO.setTransfer_date(DateUtil.convertDateToStringUntilSeconds(now));
			if (warrantyDate != null) {
				toChainDTO.setWarranty_date(DateUtil.convertDateToStringUntilSeconds(warrantyDate));
			}
			Gson gson = new Gson();
			//TODO
			//String txId = httpClientService.sendToBaasIdBlockChain(gson.toJson(toChainDTO));
			String txId = null;
			
			//Create transfer history
			DeviceTransferHistory history = new DeviceTransferHistory();
			history.setTransferDate(now);
			history.setToUser(user);
			history.setDevice(device);
			history.setTxId(txId);
			history = historyService.save(history);
		}
		
		UserDeviceDTO dto = new UserDeviceDTO();
		dto.setDevice_did(deviceDid);
		dto.setUser_did(userDid);
		return dto;
	}
	
	public UserDeviceDTO transfer(DeviceTransferDTO inputDTO) throws BizException,Exception {
		String deviceDid = inputDTO.getDevice_did();
		String fromDid = inputDTO.getFrom_did();
		String toDid = inputDTO.getTo_did();
		
		User fromUser = userService.getByDid(fromDid);
		User toUser = userService.getByDid(toDid);
		if (fromUser == null || toUser == null) {
			throw new BizException("Invalid user DID");
		}
		Device device = deviceService.getByDid(deviceDid);
		if (device == null) {
			throw new BizException("Invalid device DID");
		}
		
		Date now = new Date();
		//Post to blockchain
		BlockchainDeviceTransferDTO toChainDTO = new BlockchainDeviceTransferDTO();
		toChainDTO.setType("device_transfer");
		toChainDTO.setDevice_did(deviceDid);
		toChainDTO.setFrom_did(fromDid);
		toChainDTO.setTo_did(toDid);
		toChainDTO.setTransfer_date(DateUtil.convertDateToStringUntilSeconds(now));
		if (device.getWarrantyDate() != null) {
			toChainDTO.setWarranty_date(DateUtil.convertDateToStringUntilSeconds(device.getWarrantyDate()));
		}
		Gson gson = new Gson();
		//TODO
		//String txId = httpClientService.sendToBaasIdBlockChain(gson.toJson(toChainDTO));
		String txId = null;
		
		//Create transfer history
		DeviceTransferHistory history = new DeviceTransferHistory();
		history.setFromUser(fromUser);
		history.setToUser(toUser);
		history.setDevice(device);
		history.setTransferDate(now);
		history.setTxId(txId);
		history = historyService.save(history);
		
		UserDeviceDTO dto = new UserDeviceDTO();
		dto.setUser_did(toDid);
		dto.setDevice_did(deviceDid);
		return dto;
	}
	
	public List<DeviceTransferHistoryFullDTO> getDeviceTransferHistory(String userDid, String deviceDid) throws BizException, Exception {
		User user = userService.getByDid(userDid);
		if (user == null) {
			throw new BizException("Invalid user DID");
		}
		Device device = deviceService.getByDid(deviceDid);
		if (device == null) {
			throw new BizException("Invalid device DID");
		}
		
		List<DeviceTransferHistory> history = historyService.getByUserAndDevice(user, device);
		
		List<DeviceTransferHistoryFullDTO> dtos = new ArrayList<DeviceTransferHistoryFullDTO>();
		for (DeviceTransferHistory h : history) {
			DeviceTransferHistoryFullDTO dto = new DeviceTransferHistoryFullDTO();
			dto.setTransfer_date(DateUtil.convertDateToStringUntilSeconds(h.getTransferDate()));
			dto.setTx_id(h.getTxId());
			if (h.getFromUser() != null) {
				dto.setFrom_user(UserDTO.createSimpleDTO(h.getFromUser()));
			}
			dto.setTo_user(UserDTO.createSimpleDTO(h.getToUser()));
			dtos.add(dto);
		}
		return dtos;
	}
	
}
