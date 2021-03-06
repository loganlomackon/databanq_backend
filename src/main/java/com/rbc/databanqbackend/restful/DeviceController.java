package com.rbc.databanqbackend.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.exception.BizException;
import com.rbc.databanqbackend.restful.dto.DeviceDTO;
import com.rbc.databanqbackend.restful.dto.DeviceTransferDTO;
import com.rbc.databanqbackend.restful.dto.DeviceTransferHistoryFullDTO;
import com.rbc.databanqbackend.restful.dto.UserDTO;
import com.rbc.databanqbackend.restful.dto.UserDeviceDTO;
import com.rbc.databanqbackend.service.DatabanqService;
import com.rbc.databanqbackend.service.DeviceService;
import com.rbc.databanqbackend.service.DeviceTransferHistoryService;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

	@Autowired
	private DatabanqService databanqService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceTransferHistoryService historyService;
	
	@RequestMapping(value="/{did}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getDevice(@PathVariable String did) {
		try {
			Device device = deviceService.getByDid(did);
			User user = historyService.getDeviceOwner(device);
			DeviceDTO dto = DeviceDTO.createDTO(device);
			dto.setOwner(UserDTO.createDTO(user));
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
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody DeviceDTO inputDTO) {
		if (inputDTO.getDid() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid DID");
		}
		
		try {
			Device device = deviceService.getByDid(inputDTO.getDid());
			device.setDeleted(true);
			device = deviceService.save(device);
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
	
	@RequestMapping(value="/transfer", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> transferDevice(@RequestBody DeviceTransferDTO inputDTO) {
		if (inputDTO.getDevice_did() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid device DID");
		}
		if (inputDTO.getFrom_did() == null || inputDTO.getTo_did() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.body("Invalid user DID");
		}
		
		try {
			UserDeviceDTO dto = databanqService.transfer(inputDTO);
			return ResponseEntity.status(HttpStatus.OK)
					.header("Access-Control-Allow-Origin", "*")
					.body(dto);
		} 
		catch (BizException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
		catch (Exception e2) {
			e2.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e2.getMessage());
		}
	}
	
	@RequestMapping(value="/transfer/history", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getDeviceTransferHistory(@RequestBody UserDeviceDTO inputDTO) {
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
			List<DeviceTransferHistoryFullDTO> dtos = databanqService.getDeviceTransferHistory(inputDTO.getUser_did(), inputDTO.getDevice_did());
			return ResponseEntity.status(HttpStatus.OK)
					.header("Access-Control-Allow-Origin", "*")
					.body(dtos);
		} 
		catch (BizException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e.getMessage());
		}
		catch (Exception e2) {
			e2.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.body(e2.getMessage());
		}
	}

}
