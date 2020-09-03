package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;

import com.rbc.databanqbackend.domain.UserPairedDevice;

@SuppressWarnings("serial")
public class UserPairedDeviceDTO implements Serializable {

	private String device_did;
	private String due_timestamp;
	private String is_removed;
	
	public UserPairedDeviceDTO() {
	}

	public static UserPairedDeviceDTO createDTO(UserPairedDevice p) {
		UserPairedDeviceDTO dto = new UserPairedDeviceDTO();
		dto.setDevice_did(p.getDeviceDid());
		if (p.getDueTimestamp() != null) {
			dto.setDue_timestamp(p.getDueTimestamp().toString());
		}
		dto.setIs_removed(p.getIsRemoved().toString());
		return dto;
	}
	public UserPairedDevice convertToPojo() {
		UserPairedDevice p = new UserPairedDevice();
		p.setDeviceDid(this.getDevice_did());
		if (this.getDue_timestamp() != null) {
			p.setDueTimestamp(Long.valueOf(this.getDue_timestamp()));
		}
		p.setIsRemoved("true".equals(this.getIs_removed()));
		return p;
	}
	
	public String getDevice_did() {
		return device_did;
	}

	public void setDevice_did(String device_did) {
		this.device_did = device_did;
	}

	public String getIs_removed() {
		return is_removed;
	}

	public void setIs_removed(String is_removed) {
		this.is_removed = is_removed;
	}

	public String getDue_timestamp() {
		return due_timestamp;
	}

	public void setDue_timestamp(String due_timestamp) {
		this.due_timestamp = due_timestamp;
	}

}
