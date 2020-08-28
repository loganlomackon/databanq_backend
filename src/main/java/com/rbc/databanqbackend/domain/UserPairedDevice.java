package com.rbc.databanqbackend.domain;

public class UserPairedDevice {

	private String deviceDid;
	private Boolean isRemoved;
	
	public UserPairedDevice() {
		this.isRemoved = false;
	}

	public String getDeviceDid() {
		return deviceDid;
	}

	public void setDeviceDid(String deviceDid) {
		this.deviceDid = deviceDid;
	}

	public Boolean getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(Boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	
}
