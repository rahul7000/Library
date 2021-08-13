package com.rahul.library.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Book model with name and isbn to register a book
 * @author rahul
 *
 */

//One to Many mapping Between Author and Book,
//One to Many mapping between Book and Lend


@Entity
@Table(name = "book")
public class Book {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="book_id")
    private Long id;
    
	private String name;
    private String isbn;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;
    
    @JsonBackReference
    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lend> lends;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Lend> getLends() {
		return lends;
	}

	public void setLends(List<Lend> lends) {
		this.lends = lends;
	}
    
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", isbn=" + isbn + ", author=" + author + ", lends=" + lends + "]";
	} 
}
