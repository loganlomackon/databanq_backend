package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.util.DateUtil;

@SuppressWarnings("serial")
public class DeviceDTO implements Serializable {

	private String did;
	
	private String product_id;
	private String product_type;
	private String mac_address;
	private String device_name;
	private String warranty_period;
	private String warranty_date;
	
	private UserDTO owner;
	
	public DeviceDTO() {
	}
	
	public static DeviceDTO createDTO(Device d) {
		DeviceDTO dto = new DeviceDTO();
		dto.setDid(d.getDid());
		dto.setProduct_id(d.getProductId());
		if (d.getProductType() != null) {
			dto.setProduct_type(d.getProductType().toString());
		}
		dto.setMac_address(d.getMacAddress());
		dto.setDevice_name(d.getDeviceName());
		if (d.getWarrantyPeriod() != null) {
			dto.setWarranty_period(d.getWarrantyPeriod().toString());
		}
		if (d.getWarrantyDate() != null) {
			dto.setWarranty_date(DateUtil.convertDateToStringUntilSeconds(d.getWarrantyDate()));
		}
		return dto;
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

	public String getWarranty_date() {
		return warranty_date;
	}

	public void setWarranty_date(String warranty_date) {
		this.warranty_date = warranty_date;
	}

	public String getWarranty_period() {
		return warranty_period;
	}

	public void setWarranty_period(String warranty_period) {
		this.warranty_period = warranty_period;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}
	
}
