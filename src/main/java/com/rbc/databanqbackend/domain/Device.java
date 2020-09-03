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
	
	private List<TransferHistory> history;

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

	public List<TransferHistory> getHistory() {
		if (history == null)
			history = new ArrayList<TransferHistory>();
		return history;
	}
	public void setHistory(List<TransferHistory> history) {
		this.history = history;
	}
	
	public String getOwnerDid() {
		List<TransferHistory> historyNow = getHistory();
		if (historyNow.size() == 0) {
			return null;
		}
		
		return historyNow.get(historyNow.size()-1).getToDid();
	}

}
