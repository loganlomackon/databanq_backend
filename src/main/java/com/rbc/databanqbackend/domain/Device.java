package com.rbc.databanqbackend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_DEVICE", uniqueConstraints={
		@UniqueConstraint(columnNames={"ID"})})
public class Device extends AbstractObject implements Serializable {

	@Column(name="DID")
	private String did;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	//
	@Column(name="PRODUCT_TYPE")
	private Integer productType;
	
	@Column(name="MAC_ADDRESS")
	private String macAddress;
	
	@Column(name="DEVICE_NAME")
	private String deviceName;
	
	@Column(name="WARRANTY_PERIOD")
	private Integer warrantyPeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="WARRANTY_DATE")
	private Date warrantyDate;
	
	//Temp
	@Column(name="OWNER_DID")
	private String ownerDid;
	
	@OneToMany(mappedBy = "device", cascade = CascadeType.REFRESH)
	private List<DeviceTransferHistory> history;

	public Device() {
		this.setDeleted(false);
	}

	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(Integer warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public Date getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(Date warrantyDate) {
		this.warrantyDate = warrantyDate;
	}

	public List<DeviceTransferHistory> getHistory() {
		if (history == null)
			history = new ArrayList<DeviceTransferHistory>();
		return history;
	}
	public void setHistory(List<DeviceTransferHistory> history) {
		this.history = history;
	}

	public String getOwnerDid() {
		return ownerDid;
	}

	public void setOwnerDid(String ownerDid) {
		this.ownerDid = ownerDid;
	}

}
