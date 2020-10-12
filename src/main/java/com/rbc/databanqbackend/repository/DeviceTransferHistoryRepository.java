package com.rbc.databanqbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.DeviceTransferHistory;
import com.rbc.databanqbackend.domain.User;

public interface DeviceTransferHistoryRepository extends JpaRepository<DeviceTransferHistory, Long> {

	List<DeviceTransferHistory> findByDeletedFalse();
	List<DeviceTransferHistory> findByDeletedFalseAndToUser(User toUser);
	List<DeviceTransferHistory> findByDeletedFalseAndDevice(Device device);
	DeviceTransferHistory findFirstByDeletedFalseAndToUserAndDeviceOrderByIdDesc(User toUser, Device device);
	List<DeviceTransferHistory> findByDeletedFalseAndDeviceAndIdLessThanEqual(Device device, Long id);
	DeviceTransferHistory findFirstByDeletedFalseAndDeviceOrderByIdDesc(Device device);
}
