package com.rbc.databanqbackend.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.restful.dto.UserDTO;
import com.rbc.databanqbackend.service.BaasIdService;
import com.rbc.databanqbackend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BaasIdService baasIdService;
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> signup(@RequestBody UserDTO inputDTO) {
		if (inputDTO.getDid() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid DID");
		}
		
		try {
			User user = inputDTO.convertToPojo();
			user = userService.save(user);
			baasIdService.saveUser(user.getDid(), user);
			return ResponseEntity.status(HttpStatus.OK)
					.header("Access-Control-Allow-Origin", "*")
					.body(UserDTO.createDTO(user));
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody UserDTO inputDTO) {
		if (inputDTO.getDid() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid DID");
		}
		
		try {
			User user = userService.getByDid(inputDTO.getDid());
			return ResponseEntity.status(HttpStatus.OK)
					.header("Access-Control-Allow-Origin", "*")
					.body(UserDTO.createDTO(user));
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody UserDTO inputDTO) {
		if (inputDTO.getDid() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid DID");
		}
		
		try {
			userService.delete(inputDTO.getDid());
			return ResponseEntity.status(HttpStatus.OK)
					.header("Access-Control-Allow-Origin", "*")
					.body(null);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
	}

}
