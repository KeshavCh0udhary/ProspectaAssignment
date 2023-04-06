package com.masai.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class EntryDetailsDto {
	
	private String api;
	private String link;
	private String description;
	private String auth;
	private boolean HTTPS;
	private String cors;
	private String category;
	
}
