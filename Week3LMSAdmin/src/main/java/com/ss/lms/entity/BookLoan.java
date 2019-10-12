package com.ss.lms.entity;

import java.sql.Date;

public class BookLoan {
	
	Book book;
	LibraryBranch branch;
	Borrower borrower;
	Date dateOut;
	Date dueDate;
	
	public BookLoan(Book book, LibraryBranch branch, Borrower borrower, Date dateOut,Date dueDate) {
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString() 
	{
		return  book.getTitle() + "\t" + branch.getBranchName() + "\t" + borrower.getName() + "\t" + dateOut.toString() + "\t" + dueDate.toString();
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
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public BookLoan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dateOut
	 */
	public Date getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
}