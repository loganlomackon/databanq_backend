package com.rbc.databanqbackend.restful.dto;

public class DeviceTransferDTO {

	private String device_did;
	private String from_did;
	private String to_did;
	private String tx_id;
	
	public DeviceTransferDTO() {
	}

	public String getFrom_did() {
		return from_did;
	}

	public void setFrom_did(String from_did) {
		this.from_did = from_did;
	}

	public String getTo_did() {
		return to_did;
	}

	public void setTo_did(String to_did) {
		this.to_did = to_did;
	}

	public String getTx_id() {
		return tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

	public String getDevice_did() {
		return device_did;
	}

	public void setDevice_did(String device_did) {
		this.device_did = device_did;
	}

}
