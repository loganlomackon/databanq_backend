package com.rbc.databanqbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.domain.UserDevice;
import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.PairingDTO;
import com.rbc.databanqbackend.restful.dto.UserDeviceDTO;

@Service
public class DatabanqService {
	
	@Autowired
	private BaasIdService baasIdService;
	
	public UserDeviceDTO pairing(PairingDTO inputDTO) throws BizException {
		String deviceDid = inputDTO.getDevice_did();
		String userDid = inputDTO.getUser_did();
		UserDevice userDevice = baasIdService.getUserDevice(userDid, deviceDid);
		
		Device device = baasIdService.getDevice(deviceDid);
		if (device == null) {
			device = new Device();
			device.setDid(inputDTO.getDevice_did());
			device.setMacAddress(inputDTO.getMac_address());
			device.setDeviceName(inputDTO.getDevice_name());
			device.setProductId(inputDTO.getProduct_id());
			device.setProductType(Integer.valueOf(inputDTO.getProduct_type()));
			//TODO: history
			device = baasIdService.saveDevice(deviceDid, device);
		}
		
		User user = baasIdService.getUser(userDid);
		
		
		UserDeviceDTO dto = new UserDeviceDTO();
		dto.setDevice_did(deviceDid);
		dto.setUser_did(userDid);
		return dto;
	}
}
