package com.rbc.databanqbackend.restful.dto;

public class BlockchainDeviceTransferDTO {

	private String type;
	
	private String device_did;
	private String from_did;
	private String to_did;
	private String tx_id;
	
	private String transfer_date;
	private String warranty_date;
	
	public BlockchainDeviceTransferDTO() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTransfer_date() {
		return transfer_date;
	}

	public void setTransfer_date(String transfer_date) {
		this.transfer_date = transfer_date;
	}

	public String getWarranty_date() {
		return warranty_date;
	}

	public void setWarranty_date(String warranty_date) {
		this.warranty_date = warranty_date;
	}

}
