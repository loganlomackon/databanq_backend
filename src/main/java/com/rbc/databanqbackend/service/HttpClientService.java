package com.rbc.databanqbackend.service;

import java.nio.charset.Charset;
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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.baasid.BaasIdBlockchainResDTO;


@Service
public class HttpClientService {
	
	private static final String BAASID_TEST_CHAIN_URL = "http://chain-test.baasid.com.tw:8585/iotapi/v1/blockchain/vcs";
	
	public String sendPostForm(String path, Map<String, String> form) throws BizException,Exception {
		return sendPostForm(path, new HashMap<String,String>(), form);
	}
	public String sendPostForm(String path, Map<String, String> headers, Map<String, String> form) throws BizException,Exception {
		headers.put("Content-type", "application/x-www-form-urlencoded");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : form.keySet()) {
			params.add(new BasicNameValuePair(key, form.get(key)));
		}
		
		return sendPost(path, headers, new UrlEncodedFormEntity(params, "UTF-8"));
	}
	public String sendPost(String path, Map<String, String> headers, HttpEntity entity) throws BizException,Exception {
		HttpPost post = new HttpPost(path);
		RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		post.setConfig(defaultConfig);
		for (String key : headers.keySet()) {
			post.addHeader(key, headers.get(key));
		}
    	post.setEntity(entity);
    	
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
	
	public String sendGet(String path) throws BizException,Exception {
		return sendGet(path, new HashMap<String,String>());
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
	public String sendPut(String path, Map<String,String> headers, HttpEntity entity) throws BizException, Exception {
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
	public void sendDelete(String path, Map<String,String> headers) throws BizException, Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpDelete del = new HttpDelete(path);
		for (String key : headers.keySet()) {
			del.addHeader(key, headers.get(key));
		}
		
		CloseableHttpResponse response = client.execute(del);
		int status = response.getStatusLine().getStatusCode();
		String res = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		client.close();
		
		//logger.info("GET:"+path+" ;; Response:"+content);
		if (status != 200)
			throw new BizException(String.valueOf(status)+":"+res);
	}
	
	public String sendToBaasIdBlockChain(String content) throws BizException, Exception {
		StringEntity entity = new StringEntity(content, Charset.forName("UTF-8"));
		
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("Content-type", "application/json");
		headers.put("charset", "UTF-8");
		String res = sendPost(BAASID_TEST_CHAIN_URL, headers, entity);
		Gson gson = new Gson();
		BaasIdBlockchainResDTO resDTO = gson.fromJson(res, BaasIdBlockchainResDTO.class);
		return resDTO.getTxid();
	}
	
	public String getFromBaasIdBlockChain(String txId) throws BizException, Exception {
		return sendGet(BAASID_TEST_CHAIN_URL+"/"+txId);
	}
	
	
}
