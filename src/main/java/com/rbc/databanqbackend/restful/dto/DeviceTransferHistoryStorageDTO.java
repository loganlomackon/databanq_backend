package com.rbc.databanqbackend.restful.dto;

import com.rbc.databanqbackend.domain.DeviceTransferHistory;
import com.rbc.databanqbackend.util.DateUtil;

public class DeviceTransferHistoryStorageDTO {

	private String transfer_date;
	private String from_user_did;
	private String to_user_did;
	private String tx_id;

	private String device_did;

	public DeviceTransferHistoryStorageDTO() {
	}
	
	public static DeviceTransferHistoryStorageDTO createDTO(DeviceTransferHistory h) {
		DeviceTransferHistoryStorageDTO dto = new DeviceTransferHistoryStorageDTO();
		dto.setDevice_did(h.getDevice().getDid());
		dto.setTransfer_date(DateUtil.convertDateToDayString(h.getTransferDate()));
		dto.setTx_id(h.getTxId());
		dto.setFrom_user_did(h.getFromUser().getDid());
		dto.setTo_user_did(h.getToUser().getDid());
		return dto;
	}

	public String getTransfer_date() {
		return transfer_date;
	}

	public void setTransfer_date(String transfer_date) {
		this.transfer_date = transfer_date;
	}

	public String getFrom_user_did() {
		return from_user_did;
	}

	public void setFrom_user_did(String from_user_did) {
		this.from_user_did = from_user_did;
	}

	public String getTo_user_did() {
		return to_user_did;
	}

	public void setTo_user_did(String to_user_did) {
		this.to_user_did = to_user_did;
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
