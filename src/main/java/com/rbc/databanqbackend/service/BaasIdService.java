package com.rbc.databanqbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaasIdService {

	@Autowired
	private HttpClientService httpClientService;
	
	public String getBucketList() {
	   try {
		String token = httpClientService.loginBaasId();
		String res = httpClientService.getBaasIdBucketList(token);
		return res;
	   } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	    return null;
	}
}
