package com.masai.payloads;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	private LocalDateTime timestamp;
	private String message;
	private boolean status;
	private Object responseObject;
	
}
