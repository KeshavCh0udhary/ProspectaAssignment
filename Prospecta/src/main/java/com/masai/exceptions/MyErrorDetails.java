package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String description;

	public MyErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.description = details;
	}

	@Override
	public String toString() {
		return "MyErrorDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + description + "]";
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MyErrorDetails() {
		// TODO Auto-generated constructor stub
	}

}
