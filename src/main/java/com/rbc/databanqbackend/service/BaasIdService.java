package com.rbc.databanqbackend.service;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.domain.UserDevice;
import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.DeviceDTO;
import com.rbc.databanqbackend.restful.dto.UserDTO;
import com.rbc.databanqbackend.restful.dto.UserDeviceDTO;
import com.rbc.databanqbackend.restful.dto.baasid.BaasIdLoginResDTO;
import com.rbc.databanqbackend.utils.XmlUtil;

@Service
public class BaasIdService {

	public static final String BUCKET_NAME = "databanq/test";
	public static final String FOLDER_USER = "users";
	public static final String FOLDER_DEVICE = "devices";
	public static final String FOLDER_USER_DEVICE = "user_device";
	
	private static final String LOGIN_URL = "http://220.133.169.222:8080/auth/realms/dev/protocol/openid-connect/token";
	public static final String BUCKET_URL = "http://220.133.169.222:19191";
	
	@Autowired
	private HttpClientService httpClientService;
	
	public String getFolderList() {
		try {
			String token = login();
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/";
			String res = getFolder(token, path);
			XmlUtil.parseGetBucketListRes(res);
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getFolderByName(String folderName) {
		try {
			String token = login();
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/"+folderName+"/";
			String res = getFolder(token, path);
			//XmlUtil.parseGetBucketListRes(res);
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	<ListBucketResult>
  <Name>databanq_test</Name>
  <Prefix>users/</Prefix>
  <KeyCount>0</KeyCount>
  <Marker></Marker>
  <MaxKeys>1000</MaxKeys>
  <IsTruncated>false</IsTruncated>
  
    <Contents>
      <Key>users/user1/</Key>
      <LastModified>2020-08-27 09:03:39 GMT</LastModified>
      <ETag></ETag>
      <Size>28</Size>
      <Path>users/</Path>
      <ObjType>FOLDER</ObjType>
      <StorageClass>STANDARD</StorageClass>
    </Contents>
  
</ListBucketResult>
	*/
	
	public User getUser(String userDid) throws BizException {
		try {
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/"+FOLDER_USER+"/"+userDid+".json";
			String token = login();
			String res = downloadFile(token, path);
			Gson gson = new Gson();
			UserDTO dto = gson.fromJson(res, UserDTO.class);
			return dto.convertToPojo();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}
	public User saveUser(String userDid, User user) throws BizException {
		try {
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/"+FOLDER_USER+"/"+userDid+".json";
			File file = File.createTempFile(userDid, ".json");
			FileWriter myWriter = new FileWriter(file);
			Gson gson = new Gson();
		    myWriter.write(gson.toJson(UserDTO.createDTO(user)));
		    myWriter.close();
		    file.deleteOnExit();
		    
		    String token = login();
		    uploadFile(token, path, file);
			return user;
		} 
		catch (BizException e) {
			throw e;
		}
		catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}
	
	public Device getDevice(String did) throws BizException {
		try {
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/"+FOLDER_DEVICE+"/"+did+".json";
			String token = login();
			String res = downloadFile(token, path);
			Gson gson = new Gson();
			DeviceDTO dto = gson.fromJson(res, DeviceDTO.class);
			return dto.convertToPojo();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}
	public Device saveDevice(String did, Device device) throws BizException {
		try {
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/"+FOLDER_DEVICE+"/"+did+".json";
			File file = File.createTempFile(did, ".json");
			FileWriter myWriter = new FileWriter(file);
			Gson gson = new Gson();
		    myWriter.write(gson.toJson(DeviceDTO.createDTO(device)));
		    myWriter.close();
		    file.deleteOnExit();
		    
		    String token = login();
		    uploadFile(token, path, file);
			return device;
		} 
		catch (BizException e) {
			throw e;
		}
		catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}
	
	public UserDevice getUserDevice(String userDid, String deviceDid) throws BizException {
		try {
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/"+FOLDER_USER_DEVICE+"/"+userDid+"_"+deviceDid+".json";
			String token = login();
			String res = downloadFile(token, path);
			Gson gson = new Gson();
			UserDeviceDTO dto = gson.fromJson(res, UserDeviceDTO.class);
			return dto.convertToPojo();
		} catch (BizException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}
	public UserDevice saveUserDevice(String userDid, String deviceDid, UserDevice userDevice) throws BizException {
		try {
			String path = BUCKET_URL+"/"+BUCKET_NAME+"/"+FOLDER_USER_DEVICE+"/"+userDid+"_"+deviceDid+".json";
			File file = File.createTempFile(userDid+"_"+deviceDid, ".json");
			FileWriter myWriter = new FileWriter(file);
			Gson gson = new Gson();
		    myWriter.write(gson.toJson(UserDeviceDTO.createDTO(userDevice)));
		    myWriter.close();
		    file.deleteOnExit();
		    
		    String token = login();
		    uploadFile(token, path, file);
			return userDevice;
		} 
		catch (BizException e) {
			throw e;
		}
		catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return null;
	}
	
	public String login() throws Exception {
		 Map<String, String> form = new HashMap<String, String>();
		 form.put("client_id", "honglo");
		 form.put("grant_type", "password");
		 form.put("username", "fanchuang");
		 form.put("password", "honglo@baasid");
		 
		 String content = httpClientService.sendPost(LOGIN_URL, form);
		 Gson gson = new Gson();
		 BaasIdLoginResDTO resDTO = gson.fromJson(content, BaasIdLoginResDTO.class);
		 return resDTO.getAccess_token();
	}
	public String getFolder(String token, String path) throws Exception {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer "+token);
		return httpClientService.sendGet(path, headers);
	}
	public String createFolder(String token, String path) throws Exception {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer "+token);
		return httpClientService.sendPut(path, headers, null);
	}
	public String uploadFile(String token, String path, File file) throws Exception {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer "+token);
		headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_OCTET_STREAM.getMimeType());
		FileEntity entity = new FileEntity(file, ContentType.APPLICATION_OCTET_STREAM);
        return httpClientService.sendPut(path, headers, entity);
	}
	public String downloadFile(String token, String path) throws Exception {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer "+token);
		return httpClientService.sendGet(path, headers);
	}
}