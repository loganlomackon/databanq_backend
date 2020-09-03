package com.rbc.databanqbackend.restful.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rbc.databanqbackend.domain.Device;
import com.rbc.databanqbackend.domain.TransferHistory;

@SuppressWarnings("serial")
public class DeviceDTO implements Serializable {

	private String did;
	
	private String product_id;
	private String product_type;
	private String mac_address;
	private String device_name;
	
	private List<TransferHistoryDTO> history;
	
	public DeviceDTO() {
	}
	
	public static DeviceDTO createDTO(Device d) {
		DeviceDTO dto = new DeviceDTO();
		dto.setDid(d.getDid());
		dto.setProduct_id(d.getProductId());
		dto.setProduct_type(d.getProductType().toString());
		dto.setMac_address(d.getMacAddress());
		dto.setDevice_name(d.getDeviceName());
		for (TransferHistory h : d.getHistory()) {
			dto.getHistory().add(TransferHistoryDTO.createDTO(h));
		}
		return dto;
	}
	public Device convertToPojo() {
		Device d = new Device();
		d.setDid(this.getDid());
		d.setProductId(this.getProduct_id());
		d.setProductType(Integer.valueOf(this.getProduct_type()));
		d.setMacAddress(this.getMac_address());
		d.setDeviceName(this.getDevice_name());
		for (TransferHistoryDTO h : this.getHistory()) {
			d.getHistory().add(h.convertToPojo());
		}
		return d;
	}

	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}

	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public List<TransferHistoryDTO> getHistory() {
		if (history == null)
			history = new ArrayList<TransferHistoryDTO>();
		return history;
	}

	public void setHistory(List<TransferHistoryDTO> history) {
		this.history = history;
	}
	
}
