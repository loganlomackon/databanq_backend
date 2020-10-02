package com.rbc.databanqbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.DeviceTransferHistory;
import com.rbc.databanqbackend.domain.User;

public interface DeviceTransferHistoryRepository extends JpaRepository<DeviceTransferHistory, Long> {

	List<DeviceTransferHistory> findByToUser(User toUser);
	List<DeviceTransferHistory> findByDevice(Device device);
	DeviceTransferHistory findFirstByToUserAndDeviceOrderByIdDesc(User toUser, Device device);
	List<DeviceTransferHistory> findByDeviceAndIdLessThanEqual(Device device, Long id);
	DeviceTransferHistory findFirstByDeviceOrderByIdDesc(Device device);
}
