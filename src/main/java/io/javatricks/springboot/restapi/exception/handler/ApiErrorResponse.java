package io.javatricks.springboot.restapi.exception.handler;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

	private int code;

	private HttpStatus status;

	private String message;

	private String description;

	public ApiErrorResponse() {
		super();
	}

	public ApiErrorResponse(int code, HttpStatus status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public ApiErrorResponse(int code, HttpStatus status, String message, String description) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
