package com.rbc.databanqbackend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rbc.databanqbackend.restful.dto.baasid.BaasIdLoginResDTO;

@Service
public class HttpClientService {

	private static final String LOGIN_URL = "http://220.133.169.222:8080/auth/realms/dev/protocol/openid-connect/token";
	private static final String BUCKET_URL = "http://220.133.169.222:19191";
	
	public String sendPost(String path, Map<String, String> form) throws Exception {
		return sendPost(path, new HashMap<String,String>(), form);
	}
	public String sendPost(String path, Map<String, String> headers, Map<String, String> form) throws Exception {
		HttpPost post = new HttpPost(path);
		RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		post.setConfig(defaultConfig);
		post.addHeader("Content-type", "application/x-www-form-urlencoded");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : form.keySet()) {
			params.add(new BasicNameValuePair(key, form.get(key)));
		}
    	post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    	
    	CloseableHttpClient client = HttpClients.createDefault();
	    CloseableHttpResponse response = client.execute(post);
		int status = response.getStatusLine().getStatusCode();
		String res = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		client.close();
		    
		//logger.info("POST:"+path+"::"+content+"  ;; response:"+res);
		if (status != 200)
			throw new Exception(String.valueOf(status)+":"+res);
		    
		return res;
	}
	public String sendGet(String path, Map<String,String> headers) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(path);
		for (String key : headers.keySet()) {
			get.addHeader(key, headers.get(key));
		}
		
		CloseableHttpResponse response = client.execute(get);
		int status = response.getStatusLine().getStatusCode();
		String res = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		client.close();
		
		//logger.info("GET:"+path+" ;; Response:"+content);
		
		if (status != 200)
			throw new Exception(String.valueOf(status));
		return res;
	}
	
	public String loginBaasId() throws Exception {
		 Map<String, String> form = new HashMap<String, String>();
		 form.put("client_id", "honglo");
		 form.put("grant_type", "password");
		 form.put("username", "fanchuang");
		 form.put("password", "honglo@baasid");
		 
		 String content = sendPost(LOGIN_URL, form);
		 System.out.println("res:"+content);
		 Gson gson = new Gson();
		 BaasIdLoginResDTO resDTO = gson.fromJson(content, BaasIdLoginResDTO.class);
		 return resDTO.getAccess_token();
	}
	
	public String getBaasIdBucketList(String token) throws Exception {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer "+token);
		String content = sendGet(BUCKET_URL, headers);
		
		System.out.println("res:"+content);
		return content;
	}
	
	
}
