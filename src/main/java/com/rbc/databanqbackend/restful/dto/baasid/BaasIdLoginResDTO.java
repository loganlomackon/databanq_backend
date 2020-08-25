package com.rbc.databanqbackend.restful.dto.baasid;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaasIdLoginResDTO implements Serializable {

	private String access_token;
	private String token_type;
	//bearer
	private String session_state;
	private String scope;
	//profile email

	public BaasIdLoginResDTO() {
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getSession_state() {
		return session_state;
	}

	public void setSession_state(String session_state) {
		this.session_state = session_state;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
