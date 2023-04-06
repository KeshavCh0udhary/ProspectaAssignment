package com.masai.servicesImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modeldto.EntryDetailsDto;
import com.masai.modeldto.EntryResponseDto;
import com.masai.models.Entry;
import com.masai.payloads.ApiResponse;
import com.masai.repositories.EntryRepo;
import com.masai.services.EntryServices;

@Service
public class ServiceImplementation implements EntryServices {

	@Autowired
	private EntryRepo entryRepo;

	@Autowired
	private ModelMapper modelmaping;

	@Override
	public ApiResponse saveEntry(Entry entry) throws ResourceNotFoundException {

		Entry Api = this.entryRepo.findByApi(entry.getApi())
				.orElseThrow(() -> new ResourceNotFoundException("Entry", "API", entry.getApi()));

		EntryDetailsDto entryDetailsResponseDto = this.entryDetails(entry);

		return new ApiResponse(LocalDateTime.now(), "Entry Saved Successfully !", true, entryDetailsResponseDto);
	}

	@Override
	public List<EntryResponseDto> getAllEntries() {

		List<Entry> list = this.entryRepo.findAll();

		return list.stream().map(e -> this.entryResponse(e)).collect(Collectors.toList());

	}

	private EntryDetailsDto entryDetails(Entry entry) {

		return this.modelmaping.map(entry, EntryDetailsDto.class);
		
	}

	private EntryResponseDto entryResponse(Entry entry) {

		return this.modelmaping.map(entry, EntryResponseDto.class);
	}

}
