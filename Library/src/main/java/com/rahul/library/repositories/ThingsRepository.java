package com.rahul.library.repositories;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.library.entities.Book;
import com.rahul.library.utility.EmailUtil;

@Repository
public class ThingsRepository extends SimpleJpaRepository<Book, Long> {
	
	@Autowired
	private EmailUtil emailUtil;
	
	private EntityManager entityManager;

	
	public ThingsRepository(EntityManager entityManager) {
		super(Book.class, entityManager);
		this.entityManager = entityManager;
	}

	@Transactional
	public List<Book> save(List<Book> things) {
		things.forEach(thing -> {
			entityManager.persist(thing);
			try {
				Thread.sleep(2*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(thing);
		});
		
		try {
			emailUtil.sendmail();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------");
		System.out.println("Upload success,mail Sent !!");
		System.out.println("---------------------------------");
		return things;
	}
}
