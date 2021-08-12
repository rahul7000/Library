package com.rahul.library.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Member model with first name and last name and member status parameters to
 * store library member details
 * 
 * @author rahul
 *
 */

//One to Many mapping between Member and Lend

@Entity
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_id")
	private Long id;

	private String firstName;
	private String lastName;

	@Enumerated(EnumType.STRING)
	private MemberStatus status = MemberStatus.ACTIVE;

	@JsonBackReference
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lend> lends;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
	}

	public List<Lend> getLends() {
		return lends;
	}

	public void setLends(List<Lend> lends) {
		this.lends = lends;
	}

}