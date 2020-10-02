package com.rbc.databanqbackend.restful.dto.baasid;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaasIdBlockchainResDTO implements Serializable {

	private String paylod;
	private String msg;
	private String result;
	private String txid;

	public BaasIdBlockchainResDTO() {
	}

	public String getPaylod() {
		return paylod;
	}

	public void setPaylod(String paylod) {
		this.paylod = paylod;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

}
