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
import com.rbc.databanqbackend.domain.DeviceTransferHistory;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.DeviceDTO;
import com.rbc.databanqbackend.restful.dto.DeviceTransferHistoryStorageDTO;
import com.rbc.databanqbackend.restful.dto.UserDTO;
import com.rbc.databanqbackend.restful.dto.baasid.BaasIdLoginResDTO;
import com.rbc.databanqbackend.util.XmlUtil;

@Service
public class BaasIdService {

	public static final String BUCKET_NAME = "databanq/test";
	public static final String FOLDER_USER = "users";
	public static final String FOLDER_DEVICE = "devices";
	public static final String FOLDER_USER_DEVICE = "user_device";
	public static final String FOLDER_DEVICE_TRANSFER_HISTORY = "device_transfer_history";

	private static final String LOGIN_URL = "http://220.133.169.222:8080/auth/realms/dev/protocol/openid-connect/token";
	public static final String BUCKET_URL = "http://220.133.169.222:19191";

	@Autowired
	private HttpClientService httpClientService;

	public String getFolderList() {
		try {
			String token = login();
			String path = BUCKET_URL + "/" + BUCKET_NAME + "/";
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
			String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + folderName + "/";
			String res = getFolder(token, path);
			// XmlUtil.parseGetBucketListRes(res);
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * <ListBucketResult> <Name>databanq_test</Name> <Prefix>users/</Prefix>
	 * <KeyCount>0</KeyCount> <Marker></Marker> <MaxKeys>1000</MaxKeys>
	 * <IsTruncated>false</IsTruncated>
	 * 
	 * <Contents> <Key>users/user1/</Key> <LastModified>2020-08-27 09:03:39
	 * GMT</LastModified> <ETag></ETag> <Size>28</Size> <Path>users/</Path>
	 * <ObjType>FOLDER</ObjType> <StorageClass>STANDARD</StorageClass> </Contents>
	 * 
	 * </ListBucketResult>
	 */

	public User getUser(String userDid) throws Exception {
		String token = login();
		return getUser(token, userDid);
	}

	public User getUser(String token, String userDid) throws Exception {
		try {
			String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + FOLDER_USER + "/" + userDid + ".json";
			String res = downloadFile(token, path);
			Gson gson = new Gson();
			UserDTO dto = gson.fromJson(res, UserDTO.class);
			return dto.convertToPojo();
		} catch (BizException e) {
		}
		return null;
	}

	public User saveUser(String userDid, User user) throws BizException, Exception {
		String token = login();
		return saveUser(token, userDid, user);
	}

	public User saveUser(String token, String userDid, User user) throws BizException, Exception {
		String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + FOLDER_USER + "/" + userDid + ".json";
		File file = File.createTempFile(userDid, ".json");
		FileWriter myWriter = new FileWriter(file);
		Gson gson = new Gson();
		System.out.println("SaveUser:" + gson.toJson(UserDTO.createDTO(user)));
		myWriter.write(gson.toJson(UserDTO.createDTO(user)));
		myWriter.close();
		file.deleteOnExit();

		uploadFile(token, path, file);
		return user;
	}

	public void deleteUser(String did) throws BizException, Exception {
		String token = login();
		deleteUser(token, did);
	}
	public void deleteUser(String token, String did) throws BizException, Exception {
		String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + FOLDER_USER + "/" + did + ".json";
		deleteFile(token, path);
	}

	public Device getDevice(String did) throws Exception {
		String token = login();
		return getDevice(token, did);
	}
	public Device getDevice(String token, String did) throws Exception {
		try {
			String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + FOLDER_DEVICE + "/" + did + ".json";
			String res = downloadFile(token, path);
			Gson gson = new Gson();
			DeviceDTO dto = gson.fromJson(res, DeviceDTO.class);
			//return dto.convertToPojo();
			return new Device();
		} catch (BizException e) {
		}
		return null;
	}

	public Device saveDevice(String did, Device device) throws BizException, Exception {
		String token = login();
		return saveDevice(token, did, device);
	}

	public Device saveDevice(String token, String did, Device device) throws BizException, Exception {
		String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + FOLDER_DEVICE + "/" + did + ".json";
		File file = File.createTempFile(did, ".json");
		FileWriter myWriter = new FileWriter(file);
		Gson gson = new Gson();
		//myWriter.write(gson.toJson(DeviceDTO.createDTO(device)));
		myWriter.write(gson.toJson(new DeviceDTO()));
		myWriter.close();
		file.deleteOnExit();

		uploadFile(token, path, file);
		return device;
	}

	//Save history 
	public void saveDeviceTransferHistory(DeviceTransferHistory h) throws BizException, Exception {
		String token = login();
		DeviceTransferHistoryStorageDTO dto = DeviceTransferHistoryStorageDTO.createDTO(h);
		
		String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + FOLDER_DEVICE_TRANSFER_HISTORY + "/";
		File file = File.createTempFile(dto.getDevice_did()+"_"+dto.getTransfer_date(), ".json");
		FileWriter myWriter = new FileWriter(file);
		Gson gson = new Gson();
		myWriter.write(gson.toJson(dto));
		myWriter.close();
		file.deleteOnExit();

		uploadFile(token, path, file);
	}
	
	public void deleteDevice(String did) throws BizException, Exception {
		String token = login();
		deleteDevice(token, did);
	}

	public void deleteDevice(String token, String did) throws BizException, Exception {
		String path = BUCKET_URL + "/" + BUCKET_NAME + "/" + FOLDER_DEVICE + "/" + did + ".json";
		deleteFile(token, path);
	}

	public String login() throws Exception {
		Map<String, String> form = new HashMap<String, String>();
		form.put("client_id", "honglo");
		form.put("grant_type", "password");
		form.put("username", "fanchuang");
		form.put("password", "honglo@baasid");

		String content = httpClientService.sendPostForm(LOGIN_URL, form);
		Gson gson = new Gson();
		BaasIdLoginResDTO resDTO = gson.fromJson(content, BaasIdLoginResDTO.class);
		return resDTO.getAccess_token();
	}

	public String getFolder(String token, String path) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		return httpClientService.sendGet(path, headers);
	}

	public String createFolder(String token, String path) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		return httpClientService.sendPut(path, headers, null);
	}

	public String uploadFile(String token, String path, File file) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_OCTET_STREAM.getMimeType());
		FileEntity entity = new FileEntity(file, ContentType.APPLICATION_OCTET_STREAM);
		return httpClientService.sendPut(path, headers, entity);
	}

	public String downloadFile(String token, String path) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		return httpClientService.sendGet(path, headers);
	}

	public void deleteFile(String token, String path) throws BizException, Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		httpClientService.sendDelete(path, headers);
	}
}
