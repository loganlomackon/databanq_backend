package com.rbc.databanqbackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.repository.DeviceRepository;

@Service
public class DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;
	
	@Transactional
	public Device getByDid(String did) {
		return deviceRepository.findByDid(did);
	}
	@Transactional
	public Device save(Device device) {
		return deviceRepository.save(device);
	}
	
}
