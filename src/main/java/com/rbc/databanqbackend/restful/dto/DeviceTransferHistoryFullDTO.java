package com.rbc.databanqbackend.restful.dto;

public class DeviceTransferHistoryFullDTO {

	private String transfer_date;
	private UserDTO from_user;
	private UserDTO to_user;
	private String tx_id;
	
	public DeviceTransferHistoryFullDTO() {
	}

	public String getTransfer_date() {
		return transfer_date;
	}

	public void setTransfer_date(String transfer_date) {
		this.transfer_date = transfer_date;
	}

	public UserDTO getFrom_user() {
		return from_user;
	}

	public void setFrom_user(UserDTO from_user) {
		this.from_user = from_user;
	}

	public UserDTO getTo_user() {
		return to_user;
	}

	public void setTo_user(UserDTO to_user) {
		this.to_user = to_user;
	}

	public String getTx_id() {
		return tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

}
