package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;
import com.rbc.databanqbackend.domain.UserDevice;

@SuppressWarnings("serial")
public class UserDeviceDTO implements Serializable {

	private String user_did;
	private String device_did;
	private String history;
	
	public UserDeviceDTO() {
	}
	
	public static UserDeviceDTO createDTO(UserDevice d) {
		UserDeviceDTO dto = new UserDeviceDTO();
		dto.setDevice_did(d.getDeviceDid());
		dto.setUser_did(d.getUserDid());
		dto.setHistory(d.getHistory());
		return dto;
	}
	public UserDevice convertToPojo() {
		UserDevice d = new UserDevice();
		d.setDeviceDid(this.getDevice_did());
		d.setUserDid(this.getUser_did());
		d.setHistory(this.getHistory());
		return d;
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

	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	
}
