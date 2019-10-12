package com.ss.lms.entity;

public class Book {
	
	private Integer bookId;
	private String title;
	private Author author;
	private Publisher publisher;
	
	public Book(Integer bookId, String title, Author author, Publisher publisher) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	
	@Override
	public String toString() 
	{
		return  bookId + "\t" + title + "\t" + author.getAuthorName() + "\t" + publisher.getPublisherName();
	}
	

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the authorId
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthor(Author author) {
		this.author= author;
	}
	
	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Book() {
		
	}
	
}