package com.rahul.library.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

/**
 * Lend model to keep track on books borrowed by members from the library with
 * status, startOn and dueOn dates and LendStatus with Enum
 * 
 * @author rahul
 *
 */

//One to Many mapping between Book and Lend
//One to Many mapping between Member and Lend

@Entity
@Table(name = "lend")
public class Lend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lend_id")
	private Long id;

	private String status;
	private Instant startOn;
	private Instant dueOn;

	@Enumerated(EnumType.ORDINAL)
	private LendStatus lendStatus;

	@ManyToOne
	@JoinColumn(name = "book_id")
	@JsonManagedReference
	private Book book;

	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonManagedReference
	private Member member;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getStartOn() {
		return startOn;
	}

	public void setStartOn(Instant startOn) {
		this.startOn = startOn;
	}

	public Instant getDueOn() {
		return dueOn;
	}

	public void setDueOn(Instant dueOn) {
		this.dueOn = dueOn;
	}

	public LendStatus getLendStatus() {
		return lendStatus;
	}

	public void setLendStatus(LendStatus lendStatus) {
		this.lendStatus = lendStatus;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
