package com.rbc.databanqbackend.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

}
