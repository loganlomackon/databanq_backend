package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDeviceDTO implements Serializable {

	private String user_did;
	private String device_did;
	
	public UserDeviceDTO() {
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
	
}
