package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;

import com.rbc.databanqbackend.domain.DevicePairedUser;

@SuppressWarnings("serial")
public class DevicePairedUserDTO implements Serializable {

	private String user_did;
	
	public DevicePairedUserDTO() {
	}

	public static DevicePairedUserDTO createDTO(DevicePairedUser p) {
		DevicePairedUserDTO dto = new DevicePairedUserDTO();
		dto.setUser_did(p.getUserDid());
		return dto;
	}
	public DevicePairedUser convertToPojo() {
		DevicePairedUser p = new DevicePairedUser();
		p.setUserDid(this.getUser_did());
		return p;
	}

	public String getUser_did() {
		return user_did;
	}
	public void setUser_did(String user_did) {
		this.user_did = user_did;
	}

}
