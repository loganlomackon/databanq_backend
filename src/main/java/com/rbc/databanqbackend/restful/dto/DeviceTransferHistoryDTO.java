package com.rbc.databanqbackend.restful.dto;

public class DeviceTransferHistoryDTO {

	private String timestamp;
	private String from_did;
	private String to_did;
	private String tx_id;
	
	public DeviceTransferHistoryDTO() {
	}
	/*
	public static TransferHistoryDTO createDTO(TransferHistory h) {
		TransferHistoryDTO dto = new TransferHistoryDTO();
		dto.setTimestamp(h.getTimestamp().toString());
		dto.setFrom_did(h.getFromDid());
		dto.setTo_did(h.getToDid());
		dto.setTx_id(h.getTxId());
		return dto;
	}
	public TransferHistory convertToPojo() {
		TransferHistory h = new TransferHistory();
		h.setFromDid(this.getFrom_did());
		h.setToDid(this.getTo_did());
		h.setTimestamp(Long.valueOf(this.getTimestamp()));
		h.setTxId(this.getTx_id());
		return h;
	}
*/
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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

}
