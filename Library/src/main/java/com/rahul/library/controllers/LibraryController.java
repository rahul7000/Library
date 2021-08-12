package com.rahul.library.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.library.entities.Author;
import com.rahul.library.entities.Book;
import com.rahul.library.entities.Member;
import com.rahul.library.requests.AuthorCreationRequest;
import com.rahul.library.requests.BookCreationRequest;
import com.rahul.library.requests.BookLendRequest;
import com.rahul.library.requests.BookPersistRequest;
import com.rahul.library.requests.MemberCreationRequest;
import com.rahul.library.response.PaginatedBookResponse;
import com.rahul.library.services.LibraryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	@Autowired
	private TaskExecutor uploadExecutor;
	
	

	@GetMapping("/book")
	public ResponseEntity<Object> readBooks(@RequestParam(required = false) String isbn) {
		if (isbn == null) {
			return ResponseEntity.ok(libraryService.readBooks());
		}
		return ResponseEntity.ok(libraryService.readBook(isbn));
	}

	@GetMapping("/book/{bookId}")
	public ResponseEntity<Book> readBook(@PathVariable Long bookId) {
		return ResponseEntity.ok(libraryService.readBook(bookId));
	}

	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request) {
		return ResponseEntity.ok(libraryService.createBook(request));
	}

	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
		libraryService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/member")
	public ResponseEntity<Member> createMember(@RequestBody MemberCreationRequest request) {
		return ResponseEntity.ok(libraryService.createMember(request));
	}

	@PatchMapping("/member/{memberId}")
	public ResponseEntity<Member> updateMember(@RequestBody MemberCreationRequest request,
			@PathVariable Long memberId) {
		return ResponseEntity.ok(libraryService.updateMember(memberId, request));
	}

	@PostMapping("/book/lend")
	public ResponseEntity<List<String>> lendABook(@RequestBody List<BookLendRequest> bookLendRequests) {
		return ResponseEntity.ok(libraryService.lendABook(bookLendRequests));
	}

	@PostMapping("/author")
	public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationRequest request) {
		return ResponseEntity.ok(libraryService.createAuthor(request));
	}

	@GetMapping("/book/search")
	public ResponseEntity<PaginatedBookResponse> readBooks(Pageable pageable) {
		return ResponseEntity.ok(libraryService.readBooks(pageable));
	}

	@GetMapping("/book/search/filter")
	public ResponseEntity<PaginatedBookResponse> readBooksWithFilter(@RequestParam("query") String query,
			Pageable pageable) {
		return ResponseEntity.ok(libraryService.filterBooks(query, pageable));
	}
	
	@PostMapping("/book/persist")
	public ResponseEntity<Map<String, String>> persistBook(@RequestBody List<BookCreationRequest> request) {
		
		String message ;
		
		if(((ThreadPoolTaskExecutor)uploadExecutor).getActiveCount() == 0) {
			libraryService.persistBook(request);
			message = "Upload Request is under process";
		}else {
			message = "One upload request is already in process try after some time";
		}
		Map<String, String> response = new HashMap<>();
		response.put("message", message);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}