package com.rbc.databanqbackend.restful.dto;

public class TransferHistoryFullDTO {

	private String timestamp;
	private UserDTO from_user;
	private UserDTO to_user;
	private String tx_id;
	
	public TransferHistoryFullDTO() {
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
