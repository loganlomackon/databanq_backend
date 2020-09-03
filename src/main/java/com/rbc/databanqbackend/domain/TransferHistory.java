package com.rbc.databanqbackend.domain;

public class TransferHistory {

	private Long timestamp;
	private String fromDid;
	private String toDid;
	
	private String txId;
	
	public TransferHistory() {
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getFromDid() {
		return fromDid;
	}

	public void setFromDid(String fromDid) {
		this.fromDid = fromDid;
	}

	public String getToDid() {
		return toDid;
	}

	public void setToDid(String toDid) {
		this.toDid = toDid;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

}
