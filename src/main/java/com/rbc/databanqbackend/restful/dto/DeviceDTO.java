package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.DevicePairedUser;

@SuppressWarnings("serial")
public class DeviceDTO implements Serializable {

	private String did;
	
	private String product_id;
	private String product_type;
	private String mac_address;
	private String device_name;
	
	private String history;
	
	private List<DevicePairedUserDTO> paired_users;
	
	public DeviceDTO() {
	}
	
	public static DeviceDTO createDTO(Device d) {
		DeviceDTO dto = new DeviceDTO();
		dto.setDid(d.getDid());
		dto.setProduct_id(d.getProductId());
		dto.setProduct_type(d.getProductType().toString());
		dto.setMac_address(d.getMacAddress());
		dto.setDevice_name(d.getDeviceName());
		dto.setHistory(d.getHistory());
		for (DevicePairedUser p : d.getPairedUsers()) {
			dto.getPaired_users().add(DevicePairedUserDTO.createDTO(p));
		}
		return dto;
	}
	public Device convertToPojo() {
		Device d = new Device();
		d.setDid(this.getDid());
		d.setProductId(this.getProduct_id());
		d.setProductType(Integer.valueOf(this.getProduct_type()));
		d.setMacAddress(this.getMac_address());
		d.setDeviceName(this.getDevice_name());
		d.setHistory(this.getHistory());
		for (DevicePairedUserDTO pDTO : this.getPaired_users()) {
			d.getPairedUsers().add(pDTO.convertToPojo());
		}
		return d;
	}

	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}

	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}

	public List<DevicePairedUserDTO> getPaired_users() {
		if (paired_users == null)
			paired_users = new ArrayList<DevicePairedUserDTO>();
		return paired_users;
	}
	public void setPaired_users(List<DevicePairedUserDTO> paired_users) {
		this.paired_users = paired_users;
	}
	
}
