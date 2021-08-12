package com.rahul.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
}
