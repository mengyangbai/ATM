package bai.atm.model;

import bai.atm.dto.ReturnDtoInfo;

public class Log implements ReturnDtoInfo{
	private String method;
	
	private String request;
	
	private String response;
	
	private Integer status = 200; 

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
