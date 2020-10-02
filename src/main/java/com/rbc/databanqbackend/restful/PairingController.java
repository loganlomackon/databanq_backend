package com.rbc.databanqbackend.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.PairingDTO;
import com.rbc.databanqbackend.restful.dto.UserDeviceDTO;
import com.rbc.databanqbackend.service.DatabanqService;

@RestController
@RequestMapping("/api/pairing")
public class PairingController {

	@Autowired
	private DatabanqService databanqService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> pairing(@RequestBody PairingDTO inputDTO) {
		if (inputDTO.getDevice_did() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid device DID");
		}
		if (inputDTO.getUser_did() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid user DID");
		}
		
		try {
			UserDeviceDTO dto = databanqService.pairing(inputDTO);
			return ResponseEntity.status(HttpStatus.OK)
					.header("Access-Control-Allow-Origin", "*")
					.body(dto);
		} 
		catch (Exception e2) {
			e2.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e2.getMessage());
		}
	}

}
