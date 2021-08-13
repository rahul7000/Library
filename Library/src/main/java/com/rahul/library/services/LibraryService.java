package com.rahul.library.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rahul.library.entities.Author;
import com.rahul.library.entities.Book;
import com.rahul.library.entities.Lend;
import com.rahul.library.entities.LendStatus;
import com.rahul.library.entities.Member;
import com.rahul.library.entities.MemberStatus;
import com.rahul.library.exceptions.EntityNotFoundException;
import com.rahul.library.exceptions.GlobalErrorCode;
import com.rahul.library.exceptions.MemberNotActiveException;
import com.rahul.library.repositories.AuthorRepository;
import com.rahul.library.repositories.BookRepository;
import com.rahul.library.repositories.LendRepository;
import com.rahul.library.repositories.MemberRepository;
import com.rahul.library.repositories.ThingsRepository;
import com.rahul.library.requests.AuthorCreationRequest;
import com.rahul.library.requests.BookCreationRequest;
import com.rahul.library.requests.BookLendRequest;
import com.rahul.library.requests.BookPersistRequest;
import com.rahul.library.requests.MemberCreationRequest;
import com.rahul.library.response.PaginatedBookResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private LendRepository lendRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ThingsRepository thingsRepository;

	/**
	 * This method will read Book using given String ID. and Here I’ve used
	 * java.util.Optional to easily check availability after reading any data. This
	 * will return a Runtime exception if there is no data present for the given ID.
	 * 
	 * @param id
	 * @return
	 */
	public Book readBook(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException("Cant find any book with id=" + id.toString(),
				GlobalErrorCode.ERROR_BOOK_ID_NOT_FOUND);
	}

	public Book readBook(String isbn) {
		Optional<Book> book = bookRepository.findByIsbn(isbn);
		if (book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException("Cant find any book with ISBN=" + isbn,
				GlobalErrorCode.ERROR_BOOK__ISBN_NOT_FOUND);
	}

	/**
	 * Read all authors stored in the database
	 * 
	 * @return
	 */
	public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

	/**
	 * Read all books stored in the database
	 * 
	 * @return
	 */
	public List<Book> readBooks() {
		return bookRepository.findAll();
	}

	/**
	 * This method will bring data from the controller using BookCreationRequest and
	 * store those data in the database and return created book after successfully
	 * completed.
	 * 
	 * @param book
	 * @return
	 */
	public Book createBook(BookCreationRequest book) {
		Optional<Author> author = authorRepository.findById(book.getAuthorId());
		if (!author.isPresent()) {
			throw new EntityNotFoundException("Author Not Found with id=" + book.getAuthorId().toString(),
					GlobalErrorCode.ERROR_AUTHOR_ID_NOT_FOUND);
		}
		Book bookToCreate = new Book();
		BeanUtils.copyProperties(book, bookToCreate);
		bookToCreate.setAuthor(author.get());
		return bookRepository.save(bookToCreate);
	}

	/**
	 * Deletes a book by a given ID
	 * 
	 * @param id
	 */
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	/**
	 * This creates a member using membercreationrequest.
	 * 
	 * @param request
	 * @return
	 */
	public Member createMember(MemberCreationRequest request) {
		Member member = new Member();
		BeanUtils.copyProperties(request, member);
		return memberRepository.save(member);
	}

	/**
	 * Update the first name and last name of a member under a given ID.
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public Member updateMember(Long id, MemberCreationRequest request) {
		Optional<Member> optionalMember = memberRepository.findById(id);
		if (!optionalMember.isPresent()) {
			throw new EntityNotFoundException("Member not present in the database with id:" + id.toString(),
					GlobalErrorCode.ERROR_MEMBER_ID_NOT_FOUND);
		}
		Member member = optionalMember.get();
		member.setLastName(request.getLastName());
		member.setFirstName(request.getFirstName());
		return memberRepository.save(member);
	}

	/**
	 * Create an author using given author properties.
	 * 
	 * @param request
	 * @return
	 */
	public Author createAuthor(AuthorCreationRequest request) {
		Author author = new Author();
		BeanUtils.copyProperties(request, author);
		return authorRepository.save(author);
	}

	/**
	 * Create book lending on after reading book and member. Additionally, this
	 * allows lending multiple books for a single member.
	 * 
	 * @param list
	 * @return
	 */
	public List<String> lendABook(List<BookLendRequest> list) {
		List<String> booksApprovedToBorrow = new ArrayList<>();

		list.forEach(bookLendRequest -> {
			Optional<Book> bookForId = bookRepository.findById(bookLendRequest.getBookId());
			if (!bookForId.isPresent()) {
				throw new EntityNotFoundException(
						"Cant find any book with id=" + bookLendRequest.getBookId().toString(),
						GlobalErrorCode.ERROR_BOOK_ID_NOT_FOUND);
			}
			Optional<Member> memberForId = memberRepository.findById(bookLendRequest.getMemberId());
			if (!memberForId.isPresent()) {
				throw new EntityNotFoundException(
						"Member not present in the database with id:" + bookLendRequest.getMemberId().toString(),
						GlobalErrorCode.ERROR_MEMBER_ID_NOT_FOUND);
			}
			Member member = memberForId.get();
			if (member.getStatus() != MemberStatus.ACTIVE) {
				throw new MemberNotActiveException("User is not active to proceed a lending.",
						GlobalErrorCode.ERROR_MEMBER_ID_NOT_ACTIVE);
			}
			Optional<Lend> borrowedBook = lendRepository.findByBookAndLendStatus(bookForId.get(), LendStatus.BORROWED);
			if (!borrowedBook.isPresent()) {
				booksApprovedToBorrow.add(bookForId.get().getName());
				Lend lend = new Lend();
				lend.setMember(memberForId.get());
				lend.setBook(bookForId.get());
				lend.setLendStatus(LendStatus.BORROWED);
				lend.setStartOn(Instant.now());
				lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
				lendRepository.save(lend);
			}
		});
		if (booksApprovedToBorrow.isEmpty()) {
			throw new MemberNotActiveException("Member already borrowed this book",
					GlobalErrorCode.ERROR_MEMBER_ALREADY_BORROWED);
		}
		return booksApprovedToBorrow;
	}

	/**
	 * Service method to read paginated data set from database using repository
	 * 
	 * @param pageable
	 * @return
	 */
	public PaginatedBookResponse readBooks(Pageable pageable) {
		System.out.println("PageNo=" + pageable.getPageNumber());
		System.out.println("PageSize=" + pageable.getPageSize());
		System.out.println("Sort=" + pageable.getSort());
		System.out.println("Offset=" + pageable.getOffset());
		Page<Book> books = bookRepository.findAll(pageable);
		return new PaginatedBookResponse(books.getContent(), books.getTotalElements(), books.getTotalPages());
	}

	/**
	 * Service code to support filtering on the basis of name
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	public PaginatedBookResponse filterBooks(String name, Pageable pageable) {

		Page<Book> books = bookRepository.findAllByNameContains(name, pageable);
		return new PaginatedBookResponse(books.getContent(), books.getTotalElements(), books.getTotalPages());
	}

	@Async("uploadExecutor")
	public List<Book> persistBook(List<BookCreationRequest> request) {

		List<Book> bookToCreate = new ArrayList<>();
		Book tempBook = null;
		int count = 0;
		Optional<Author> author = null;

		for (BookCreationRequest book : request) {

			if (count == 0) {
				author = authorRepository.findById(book.getAuthorId());
				if (!author.isPresent()) {
					throw new EntityNotFoundException("Author Not Found with id=" + book.getAuthorId().toString(),
							GlobalErrorCode.ERROR_AUTHOR_ID_NOT_FOUND);
				}
				count++;
			}
			tempBook = new Book();
			BeanUtils.copyProperties(book, tempBook);
			tempBook.setAuthor(author.get());
			// for (int i = 0; i < 100000; i++) {
			bookToCreate.add(tempBook);
			// }
		}

		return thingsRepository.save(bookToCreate);
	}

}
