package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.domain.UserPairedDevice;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	
	private String did;
	private String email;
	private String phone;
	private List<UserPairedDeviceDTO> paired_devices;
	
	public UserDTO() {
	}
	
	public static UserDTO createDTO(User u) {
		UserDTO dto = createSimpleDTO(u);
		for (UserPairedDevice p : u.getPairedDevices()) {
			dto.getPaired_devices().add(UserPairedDeviceDTO.createDTO(p));
		}
		return dto;
	}
	public static UserDTO createSimpleDTO(User u) {
		UserDTO dto = new UserDTO();
		dto.setDid(u.getDid());
		dto.setEmail(u.getEmail());
		dto.setPhone(u.getPhone());
		return dto;
	}
	public User convertToPojo() {
		User u = new User();
		u.setDid(this.getDid());
		u.setEmail(this.getEmail());
		u.setPhone(this.getPhone());
		for (UserPairedDeviceDTO pDTO : this.getPaired_devices()) {
			u.getPairedDevices().add(pDTO.convertToPojo());
		}
		return u;
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

	public List<UserPairedDeviceDTO> getPaired_devices() {
		if (paired_devices == null)
			paired_devices = new ArrayList<UserPairedDeviceDTO>();
		return paired_devices;
	}
	public void setPaired_devices(List<UserPairedDeviceDTO> paired_devices) {
		this.paired_devices = paired_devices;
	}
	
}
