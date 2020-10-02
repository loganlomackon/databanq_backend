package com.rbc.databanqbackend.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_DEVICE_TRANSFER_HISTORY", uniqueConstraints={
		@UniqueConstraint(columnNames={"ID"})})
public class DeviceTransferHistory extends AbstractObject implements Serializable {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRANSFER_DATE")
	private Date transferDate;
	
	@Column(name="TX_ID")
	private String txId;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "from_user_id")
	private User fromUser;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "to_user_id")
	private User toUser;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "device_id")
	private Device device;
	
	public DeviceTransferHistory() {
		this.setDeleted(false);
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}
	
	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}
