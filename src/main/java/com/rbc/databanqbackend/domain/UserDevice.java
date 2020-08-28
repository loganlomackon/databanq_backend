package com.rbc.databanqbackend.domain;

public class UserDevice {

	private String userDid;
	private String deviceDid;
	private String history;
	
	public UserDevice() {
	}

	public String getUserDid() {
		return userDid;
	}
	public void setUserDid(String userDid) {
		this.userDid = userDid;
	}

	public String getDeviceDid() {
		return deviceDid;
	}
	public void setDeviceDid(String deviceDid) {
		this.deviceDid = deviceDid;
	}

	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	
}
