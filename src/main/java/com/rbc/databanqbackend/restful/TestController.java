package com.rbc.databanqbackend.restful;

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
import com.rbc.databanqbackend.restful.dto.BlockchainDeviceTransferDTO;
import com.rbc.databanqbackend.restful.dto.DeviceTransferDTO;
import com.rbc.databanqbackend.service.BaasIdService;
import com.rbc.databanqbackend.service.HttpClientService;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private BaasIdService baasIdService;
	@Autowired
	private HttpClientService httpClientService;
	
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

}
