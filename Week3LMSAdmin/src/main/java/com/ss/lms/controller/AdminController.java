package com.ss.lms.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.Administrator;

@RestController
public class AdminController
{
	@Autowired
	Administrator admin;

	
	/*************************************************
	 * 
	 * ALL CREATE OPERATIONS
	 * 
	 *************************************************/

	@RequestMapping(path = "/lms/admin/author/", method = RequestMethod.PUT)
	public void createAuthor(Author author) 
	{
		
	}
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	@RequestMapping(path = "/lms/admin/author/", method = RequestMethod.GET)
	public ArrayList<Author> readAuthor(@RequestBody Author author)
	{
		return admin.readAuthor(author);
	}

	@RequestMapping(path = "/lms/admin/publisher/", method = RequestMethod.GET)
	public ArrayList<Publisher> readPublisher(Publisher publisher)
	{
		return admin.readPublisher(publisher);

	}

	@RequestMapping(path = "/lms/admin/book/", method = RequestMethod.GET)
	public ArrayList<Book> readBook(Book book)
	{
		return admin.readBook(book);
	}

	@RequestMapping(path = "/lms/admin/branch/", method = RequestMethod.GET)
	public ArrayList<LibraryBranch> readLibraryBranch(LibraryBranch libraryBranch)
	{
		return admin.readLibraryBranch(libraryBranch);
	}

	@RequestMapping(path = "/lms/admin/borrower/", method = RequestMethod.GET)
	public ArrayList<Borrower> readBorrower(Borrower borrower)
	{
		return admin.readBorrower(borrower);
	}

	@RequestMapping(path = "/lms/admin/loan/", method = RequestMethod.GET)
	public ArrayList<BookLoan> readBookLoan(BookLoan bookLoan)
	{
		return admin.readBookLoan(bookLoan);
	}

}
