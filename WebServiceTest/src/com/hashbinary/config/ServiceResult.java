package com.hashbinary.config;

public class ServiceResult {
	private String result;
	private String status;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ServiceResult [result=" + result + ", status=" + status + "]";
	}
	
}
