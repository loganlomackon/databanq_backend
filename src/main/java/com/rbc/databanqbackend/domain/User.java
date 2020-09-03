package com.rbc.databanqbackend.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String did;
	private String email;
	private String phone;
	private List<UserPairedDevice> pairedDevices;
	
	public User() {
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

	public List<UserPairedDevice> getPairedDevices() {
		if (pairedDevices == null)
			pairedDevices = new ArrayList<UserPairedDevice>();
		return pairedDevices;
	}
	public void setPairedDevices(List<UserPairedDevice> pairedDevices) {
		this.pairedDevices = pairedDevices;
	}
	
	public UserPairedDevice getPairByDeviceDid(String deviceDid) {
		for (UserPairedDevice p : getPairedDevices()) {
			if (p.getDeviceDid().equals(deviceDid)) {
				return p;
			}
		}
		return null;
	}
	
}
