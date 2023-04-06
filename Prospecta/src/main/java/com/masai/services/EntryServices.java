package com.masai.services;

import java.util.List;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modeldto.EntryResponseDto;
import com.masai.models.Entry;
import com.masai.payloads.ApiResponse;

public interface EntryServices {

	public ApiResponse saveEntry(Entry entries) throws ResourceNotFoundException;

	List<EntryResponseDto> getAllEntries();
}
