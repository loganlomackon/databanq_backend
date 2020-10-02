package com.rbc.databanqbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.databanqbackend.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	Device findByDid(String did);
	
}
