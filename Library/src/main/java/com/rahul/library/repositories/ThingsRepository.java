package com.rahul.library.repositories;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rahul.library.entities.Book;
import com.rahul.library.utility.EmailUtil;

@Repository
@Transactional
public class ThingsRepository extends SimpleJpaRepository<Book, Long> {

	@Autowired
	private EmailUtil emailUtil;

	private EntityManager entityManager;

	int BATCH_SIZE = 50;
	int count = 1;

	public ThingsRepository(EntityManager entityManager) {
		super(Book.class, entityManager);
		this.entityManager = entityManager;
	}

	public List<Book> save(List<Book> things) {
		System.out.println("---------------------------------");
		System.out.println("Uploading start");
		System.out.println("---------------------------------");

		Instant start = Instant.now();
		
		for (int i = 0; i < things.size(); i++) {

			if (i > 0 && i % BATCH_SIZE == 0) {
				System.out.println("Created batch upto: " + i);
				entityManager.flush();// flush a batch of inserts and release memory:
				entityManager.clear();
			}
			entityManager.persist(things.get(i));
			System.out.println("id: " + things.get(i).getId() +" --- " + count++ +" uploaded");
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken: "+ timeElapsed.getSeconds() +" seconds");

//		things.forEach(thing -> {
//		entityManager.persist(thing);
//		System.out.println("persist called");
//		
//		try {
//			Thread.sleep(3*1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(thing);
//	});

//		try {
//			emailUtil.sendmail();
//		} catch (MessagingException | IOException e) {
//			e.printStackTrace();
//		}

//		System.out.println("---------------------------------");
//		System.out.println("Upload success,mail Sent !!");
//		System.out.println("---------------------------------");
		return things;
	}
}
