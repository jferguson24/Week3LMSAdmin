package com.ss.lms.entity;

public class BookCopy {

	Book book;
	LibraryBranch branch;
	Integer noOfCopies;
	
	public BookCopy() {
		
	}
	
	public BookCopy(Book book, LibraryBranch branch, Integer noOfCopies) {
		this.book = book;
		this.branch = branch;
		this.noOfCopies = noOfCopies;
	}
	
	@Override
	public String toString() 
	{
		return book.getTitle() + "\t" + branch.getBranchName() + "\t" + noOfCopies;
	}
	
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the branch
	 */
	public LibraryBranch getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	/**
	 * @return the noOfCopies
	 */
	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	
	
}