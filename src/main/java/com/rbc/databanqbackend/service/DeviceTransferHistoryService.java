package com.rbc.databanqbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.DeviceTransferHistory;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.repository.DeviceTransferHistoryRepository;

@Service
public class DeviceTransferHistoryService {

	@Autowired
	private DeviceTransferHistoryRepository historyRepository;
	
	@Transactional
	public List<DeviceTransferHistory> getByToUser(User user) {
		return historyRepository.findByToUser(user);
	}
	@Transactional
	public List<DeviceTransferHistory> getByDevice(Device device) {
		return historyRepository.findByDevice(device);
	}
	@Transactional
	public DeviceTransferHistory save(DeviceTransferHistory history) {
		return historyRepository.save(history);
	}
	@Transactional
	public List<DeviceTransferHistory> getByUserAndDevice(User toUser, Device device) {
		DeviceTransferHistory last = historyRepository.findFirstByToUserAndDeviceOrderByIdDesc(toUser, device);
		List<DeviceTransferHistory> history = historyRepository.findByDeviceAndIdLessThanEqual(device, last.getId());
		return history;
	}
	@Transactional
	public User getDeviceOwner(Device device) {
		DeviceTransferHistory last = historyRepository.findFirstByDeviceOrderByIdDesc(device);
		return last.getToUser();
	}
	
}
