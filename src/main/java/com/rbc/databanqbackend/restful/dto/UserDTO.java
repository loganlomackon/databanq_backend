package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;

import com.rbc.databanqbackend.domain.User;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	
	private String did;
	private String email;
	private String phone;
	
	public UserDTO() {
	}
	
	public static UserDTO createDTO(User u) {
		UserDTO dto = createSimpleDTO(u);
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
	
}
