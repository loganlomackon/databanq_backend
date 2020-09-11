package com.rbc.databanqbackend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.TransferHistory;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.domain.UserPairedDevice;
import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.DeviceTransferDTO;
import com.rbc.databanqbackend.restful.dto.PairingDTO;
import com.rbc.databanqbackend.restful.dto.TransferHistoryFullDTO;
import com.rbc.databanqbackend.restful.dto.UserDTO;
import com.rbc.databanqbackend.restful.dto.UserDeviceDTO;

@Service
public class DatabanqService {
	
	@Autowired
	private BaasIdService baasIdService;
	
	public UserDeviceDTO pairing(PairingDTO inputDTO) throws BizException,Exception {
		String deviceDid = inputDTO.getDevice_did();
		String userDid = inputDTO.getUser_did();
		
		String token = baasIdService.login();
		User user = baasIdService.getUser(token, userDid);
		if (user == null) {
			throw new BizException("Invalid user");
		}
		
		Device device = baasIdService.getDevice(token, deviceDid);
		if (device == null) {
			device = new Device();
			device.setDid(inputDTO.getDevice_did());
			device.setMacAddress(inputDTO.getMac_address());
			device.setDeviceName(inputDTO.getDevice_name());
			device.setProductId(inputDTO.getProduct_id());
			if (inputDTO.getProduct_type() != null) {
				device.setProductType(Integer.valueOf(inputDTO.getProduct_type()));
			}
			TransferHistory history = new TransferHistory();
			history.setTimestamp(new Date().getTime());
			history.setToDid(userDid);
			device.getHistory().add(history);
			device = baasIdService.saveDevice(token, deviceDid, device);
		}
		
		UserPairedDevice pair = user.getPairByDeviceDid(deviceDid);
		if (pair == null) {
			pair = new UserPairedDevice();
			pair.setDeviceDid(deviceDid);
			user.getPairedDevices().add(pair);
		}
		pair.setIsRemoved(false);
		pair.setDueTimestamp(null);
		baasIdService.deleteUser(token, userDid);
		user = baasIdService.saveUser(token, userDid, user);
		
		UserDeviceDTO dto = new UserDeviceDTO();
		dto.setDevice_did(deviceDid);
		dto.setUser_did(userDid);
		return dto;
	}
	
	public UserDeviceDTO transfer(DeviceTransferDTO inputDTO) throws BizException,Exception {
		String deviceDid = inputDTO.getDevice_did();
		String fromDid = inputDTO.getFrom_did();
		String toDid = inputDTO.getTo_did();
		String txId = inputDTO.getTx_id();
		
		//Acquire fromUser, toUser and device
		String token = baasIdService.login();
		User fromUser = baasIdService.getUser(token, fromDid);
		User toUser = baasIdService.getUser(token, toDid);
		if (fromUser == null || toUser == null) {
			throw new BizException("Invalid user ID");
		}
		Device device = baasIdService.getDevice(token, deviceDid);
		if (device == null) {
			throw new BizException("Invalid device ID");
		}
		
		Long timestamp = new Date().getTime();
		//Remove device from fromUser
		UserPairedDevice fromPair = fromUser.getPairByDeviceDid(deviceDid);
		if (fromPair == null) {
			throw new BizException("User is not authrized to transfer this device.");
		}
		fromPair.setIsRemoved(true);
		fromPair.setDueTimestamp(timestamp);
		
		//Append device to toUser
		UserPairedDevice toPair = toUser.getPairByDeviceDid(deviceDid);
		if (toPair == null) {
			toPair = new UserPairedDevice();
			toPair.setDeviceDid(deviceDid);
			toUser.getPairedDevices().add(toPair);
		}
		toPair.setDueTimestamp(null);
		toPair.setIsRemoved(false);
		
		//Append history to device
		TransferHistory newHistory = new TransferHistory();
		newHistory.setFromDid(fromDid);
		newHistory.setToDid(toDid);
		newHistory.setTimestamp(timestamp);
		newHistory.setTxId(txId);
		device.getHistory().add(newHistory);
		
		//Persist
		baasIdService.deleteUser(token, fromDid);
		baasIdService.deleteUser(token, toDid);
		baasIdService.deleteDevice(token, deviceDid);
		fromUser = baasIdService.saveUser(token, fromDid, fromUser);
		toUser = baasIdService.saveUser(token, toDid, toUser);
		device = baasIdService.saveDevice(token, deviceDid, device);
		
		UserDeviceDTO dto = new UserDeviceDTO();
		dto.setUser_did(toDid);
		dto.setDevice_did(deviceDid);
		return dto;
	}
	
	public List<TransferHistoryFullDTO> getDeviceTransferHistory(String userId, String deviceId) throws BizException, Exception {
		String token = baasIdService.login();
		User user = baasIdService.getUser(token, userId);
		if (user == null) {
			throw new BizException("Invalid user ID");
		}
		UserPairedDevice pair = user.getPairByDeviceDid(deviceId);
		if (pair == null) {
			throw new BizException("User is not authrized to review this device.");
		}
		Device device = baasIdService.getDevice(token, deviceId);
		if (device == null) {
			throw new BizException("Invalid device ID");
		}
		
		List<TransferHistory> sources = new ArrayList<TransferHistory>();
		if (pair.getDueTimestamp() == null) {
			sources = device.getHistory();
		}
		else {
			for (TransferHistory h : device.getHistory()) {
				if (h.getTimestamp() <= pair.getDueTimestamp()) {
					sources.add(h);
				}
			}
		}
		
		List<TransferHistoryFullDTO> dtos = new ArrayList<TransferHistoryFullDTO>();
		for (TransferHistory h : sources) {
			TransferHistoryFullDTO dto = new TransferHistoryFullDTO();
			dto.setTx_id(h.getTxId());
			dto.setTimestamp(h.getTimestamp().toString());
	
			if (h.getFromDid() != null) {
				dto.setFrom_user(UserDTO.createSimpleDTO(baasIdService.getUser(h.getFromDid())));
			}
			dto.setTo_user(UserDTO.createSimpleDTO(baasIdService.getUser(h.getToDid())));
			
			dtos.add(dto);
		}
		return dtos;
	}
	
}
