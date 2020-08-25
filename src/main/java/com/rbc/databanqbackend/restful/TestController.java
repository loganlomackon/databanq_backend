package com.rbc.databanqbackend.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.databanqbackend.service.BaasIdService;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private BaasIdService baasIdService;
	
	@RequestMapping(value="/ok", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testPush() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body("ok");
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/bucket", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testGetBucketList() {
		try {
			String res = baasIdService.getBucketList();
			
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
