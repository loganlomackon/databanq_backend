package com.rbc.databanqbackend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.rbc.databanqbackend.exception.BizException;


@Service
public class HttpClientService {
	
	public String sendPost(String path, Map<String, String> form) throws BizException,Exception {
		return sendPost(path, new HashMap<String,String>(), form);
	}
	public String sendPost(String path, Map<String, String> headers, Map<String, String> form) throws BizException,Exception {
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
		    
		System.out.println("POST:"+res);
		//logger.info("POST:"+path+"::"+content+"  ;; response:"+res);
		if (status != 200)
			throw new BizException(String.valueOf(status)+":"+res);
		    
		return res;
	}
	public String sendGet(String path, Map<String,String> headers) throws BizException,Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(path);
		for (String key : headers.keySet()) {
			get.addHeader(key, headers.get(key));
		}
		
		CloseableHttpResponse response = client.execute(get);
		int status = response.getStatusLine().getStatusCode();
		String res = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		client.close();
		
		System.out.println("GET:"+res);
		//logger.info("GET:"+path+" ;; Response:"+content);
		if (status != 200)
			throw new BizException(String.valueOf(status)+":"+res);
		return res;
	}
	public String sendPut(String path, Map<String,String> headers, HttpEntity entity) throws BizException,Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut put = new HttpPut(path);
		for (String key : headers.keySet()) {
			put.addHeader(key, headers.get(key));
		}
		if (entity != null) {
			put.setEntity(entity);
		}
		
		CloseableHttpResponse response = client.execute(put);
		int status = response.getStatusLine().getStatusCode();
		String res = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		client.close();
		
		System.out.println("PUT:"+res);
		//logger.info("GET:"+path+" ;; Response:"+content);
		if (status != 200 && status != 201)
			throw new BizException(String.valueOf(status)+":"+res);
		return res;
	}
	
	

	
	
}
