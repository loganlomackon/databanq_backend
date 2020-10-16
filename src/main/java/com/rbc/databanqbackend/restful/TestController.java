package com.rbc.databanqbackend.restful;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.DeviceTransferHistory;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.restful.dto.BlockchainDeviceTransferDTO;
import com.rbc.databanqbackend.restful.dto.DeviceTransferDTO;
import com.rbc.databanqbackend.service.BaasIdService;
import com.rbc.databanqbackend.service.DeviceService;
import com.rbc.databanqbackend.service.DeviceTransferHistoryService;
import com.rbc.databanqbackend.service.HttpClientService;
import com.rbc.databanqbackend.service.UserService;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private BaasIdService baasIdService;
	@Autowired
	private HttpClientService httpClientService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceTransferHistoryService deviceTransferHistoryService;
	
	@RequestMapping(value="/folders", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getFolderList() {
		try {
			String res = baasIdService.getFolderList();
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	@RequestMapping(value="/folder/{folderName}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getFolderByName(@PathVariable String folderName) {
		try {
			String res = baasIdService.getFolderByName(folderName);
			
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/chain", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testChain() {
		try {
			BlockchainDeviceTransferDTO dto = new BlockchainDeviceTransferDTO();
			dto.setType("device_transfer");
			dto.setFrom_did("abc123");
			dto.setTo_did("xyz123");
			dto.setWarranty_date("2021-01-01");
			String randDid = UUID.randomUUID().toString().substring(0, 10);
			dto.setDevice_did(randDid);
			Gson gson = new Gson();
			String txId = httpClientService.sendToBaasIdBlockChain(gson.toJson(dto));
			String res = httpClientService.getFromBaasIdBlockChain(txId);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete_all", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> deleteAll() {
		try {
			List<User> users = userService.getAll();
			for (User user : users) {
				user.setDeleted(true);
				try {
					baasIdService.deleteUser(user.getDid());
				}
				catch (Exception e) {
				}
			}
			userService.save(users);

			List<Device> devices = deviceService.getAll();
			for (Device device : devices) {
				device.setDeleted(true);
			}
			deviceService.save(devices);
			
			List<DeviceTransferHistory> hs = deviceTransferHistoryService.getAll();
			for (DeviceTransferHistory h : hs) {
				h.setDeleted(true);
				try {
					baasIdService.deleteHistory(h);
				}
				catch (Exception e) {
				}
			}
			deviceTransferHistoryService.save(hs);

			return ResponseEntity.status(HttpStatus.OK).body(null);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}

}
