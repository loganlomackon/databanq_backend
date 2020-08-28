package com.rbc.databanqbackend.domain;

import java.util.ArrayList;
import java.util.List;

public class Device {

	private String did;
	
	private String productId;
	private Integer productType;
	private String macAddress;
	private String deviceName;
	//Integer deviceID
	
	private String history;
	 
	private List<DevicePairedUser> pairedUsers;
	
	public Device() {
	}

	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}

	public List<DevicePairedUser> getPairedUsers() {
		if (pairedUsers == null)
			pairedUsers = new ArrayList<DevicePairedUser>();
		return pairedUsers;
	}
	public void setPairedUsers(List<DevicePairedUser> pairedUsers) {
		this.pairedUsers = pairedUsers;
	}

}
