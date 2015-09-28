package com.hashbinary.config;
import java.util.Map;

public class Model {
	private int serialNo;
	private String serviceName;
	private String serviceURL;
	private RequestType requestType;
	
	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	private Map<String, String> serviceHeaders;
	private String serviceBody;
	private String serviceResult;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public Map<String, String> getServiceHeaders() {
		return serviceHeaders;
	}

	public void setServiceHeaders(Map<String, String> serviceHeaders) {
		this.serviceHeaders = serviceHeaders;
	}

	public String getServiceBody() {
		return serviceBody;
	}

	public void setServiceBody(String serviceBody) {
		this.serviceBody = serviceBody;
	}

	public String getServiceResult() {
		return serviceResult;
	}

	public void setServiceResult(String serviceResult) {
		this.serviceResult = serviceResult;
	}

	@Override
	public String toString() {
		return "Model [serialNo=" + serialNo + ", serviceName=" + serviceName
				+ ", serviceURL=" + serviceURL + ", requestType=" + requestType
				+ ", serviceHeaders=" + serviceHeaders + ", serviceBody="
				+ serviceBody + ", serviceResult=" + serviceResult + "]";
	}

}
