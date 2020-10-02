package com.rbc.databanqbackend.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_USER", uniqueConstraints={
		@UniqueConstraint(columnNames={"ID"})})
public class User extends AbstractObject implements Serializable {

	@Column(name="DID")
	private String did;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE")
	private String phone;
	
	@OneToMany(mappedBy = "fromUser", cascade = CascadeType.REFRESH)
	private List<DeviceTransferHistory> fromHistory;
	
	@OneToMany(mappedBy = "toUser", cascade = CascadeType.REFRESH)
	private List<DeviceTransferHistory> toHistory;
	
	public User() {
		this.setDeleted(false);
	}

	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<DeviceTransferHistory> getFromHistory() {
		return fromHistory;
	}

	public void setFromHistory(List<DeviceTransferHistory> fromHistory) {
		this.fromHistory = fromHistory;
	}

	public List<DeviceTransferHistory> getToHistory() {
		return toHistory;
	}

	public void setToHistory(List<DeviceTransferHistory> toHistory) {
		this.toHistory = toHistory;
	}
	/*
	public UserPairedDevice getPairByDeviceDid(String deviceDid) {
		for (UserPairedDevice p : getPairedDevices()) {
			if (p.getDeviceDid().equals(deviceDid)) {
				return p;
			}
		}
		return null;
	}*/
	
}
