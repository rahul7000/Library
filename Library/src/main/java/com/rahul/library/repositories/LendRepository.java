package com.rahul.library.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.library.entities.Book;
import com.rahul.library.entities.Lend;
import com.rahul.library.entities.LendStatus;

public interface LendRepository extends JpaRepository<Lend, Long> {

	Optional<Lend> findByBookAndLendStatus(Book book, LendStatus burrowed);

}
