package com.rahul.library.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByIsbn(String isbn);
	
	Page<Book> findAllByNameContains(String name, Pageable pageable);
	
	//Page<Book> findByPublished(boolean published, Pageable pageable);

}
