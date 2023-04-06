package com.masai.controller;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modeldto.Apidata;
import com.masai.modeldto.EntryDetailsDto;
import com.masai.modeldto.EntryResponseDto;
import com.masai.models.Entry;
import com.masai.payloads.ApiResponse;
import com.masai.services.EntryServices;

@RestController
public class EntryController {

	@Autowired
	private EntryServices entryServices;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper modelMapper;
    /*
		This API will list all the Entries from http:localhost:8088/entries
    */
	@GetMapping("/entries")
	public ResponseEntity<List<Entry>> getEntriesHandler() {

		Apidata data = restTemplate.getForObject("https://api.publicapis.org/entries", Apidata.class);

		List<Entry> apiEntries = data.getEntries();
		
		

		List<EntryDetailsDto> collect = apiEntries.stream()
				.map(e -> this.modelMapper.map(e, EntryDetailsDto.class)).collect(Collectors.toList());

		return new ResponseEntity<List<Entry>>(apiEntries, HttpStatus.OK);
	}
	/*
	
		API Endpoint: https://api.publicapis.org/entries
		Request Parameter: category=Animals
		Example API Call: https://api.publicapis.org/entries?category=Animals
		Note :  category parameter is case-sensitive
	 
    */
	
	@GetMapping("/entries/categories")
	public ResponseEntity<List<EntryResponseDto>> getEntriesHandler(@RequestParam String category) {

		String url = "https://api.publicapis.org/entries?category=" + category;

		Apidata data = restTemplate.getForObject(url, Apidata.class);

		List<Entry> apiEntries = data.getEntries();

		List<EntryResponseDto> collect = apiEntries.stream().map(e -> this.modelMapper.map(e, EntryResponseDto.class))
				.collect(Collectors.toList());

		return new ResponseEntity<List<EntryResponseDto>>(collect, HttpStatus.OK);
	}

	@PostMapping("/entries")
	public ResponseEntity<ApiResponse> saveEntryHandler(@RequestBody Entry entry) throws ResourceNotFoundException {

		ApiResponse apiResponse = this.entryServices.saveEntry(entry);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
	}

}
