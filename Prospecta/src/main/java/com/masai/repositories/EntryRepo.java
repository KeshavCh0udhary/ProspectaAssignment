package com.masai.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.Entry;

@Repository
public interface EntryRepo extends JpaRepository<Entry, Integer>{

	Optional<Entry> findByApi(String Api);
	
}
