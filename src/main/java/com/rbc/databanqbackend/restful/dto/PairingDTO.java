package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PairingDTO implements Serializable {

	private String user_did;
	private String device_did;
	
	private String product_id;
	private String product_type;
	private String mac_address;
	private String device_name;
	
	private String warranty_period;
	
	public PairingDTO() {
	}

	public String getUser_did() {
		return user_did;
	}
	public void setUser_did(String user_did) {
		this.user_did = user_did;
	}

	public String getDevice_did() {
		return device_did;
	}
	public void setDevice_did(String device_did) {
		this.device_did = device_did;
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

	public String getWarranty_period() {
		return warranty_period;
	}

	public void setWarranty_period(String warranty_period) {
		this.warranty_period = warranty_period;
	}
	
}
