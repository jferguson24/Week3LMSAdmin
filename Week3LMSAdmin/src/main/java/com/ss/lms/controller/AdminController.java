package com.ss.lms.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.*;
import com.ss.lms.service.Administrator;


@RestController
@RequestMapping(path = "/lms/admin")
public class AdminController
{
	@Autowired
	Administrator admin;

	
	/*************************************************
	 * 
	 * ALL CREATE OPERATIONS
	 * 
	 *************************************************/

	@PutMapping(path = "/author/", consumes="application/json")
	public void createAuthor(Author author) 
	{
		
	}
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	@GetMapping(path = "/author/{authorId}", consumes="application/json", produces = "application/json")
	public ResponseEntity<Author> readAuthorById(@PathVariable Integer authorId)
	{
		if(authorId == null) // if the user sent empty values, 400
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Author> result = admin.readAuthorById(authorId);
		
		result.isPresent();
		// if there is no entry for the sent author, 404
		if(!result.isPresent() || result.get().getAuthorId() == null || result.get().getAuthorName() == null) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else // if we found an author, all good, 200
		{
			return new ResponseEntity<Author>(result.get(), HttpStatus.OK);
		}
	}

	@GetMapping(path = "/publisher/{publisherId}", consumes="application/json", produces = "application/json")
	public Publisher readPublisherById(@PathVariable Integer publisherId)
	{
		// TODO
		return admin.readPublisherById(publisherId).get();
	}

	@GetMapping(path = "/book/{bookId}", consumes="application/json", produces = "application/json")
	public Book readBookById(@PathVariable Integer bookId)
	{
		// TODO
		return admin.readBookById(bookId).get();
	}

	@GetMapping(path = "/branch/{branchId}", consumes="application/json", produces = "application/json")
	public LibraryBranch readLibraryBranchById(Integer branchId)
	{
		// TODO
		return admin.readLibraryBranchById(branchId).get();
	}

	@GetMapping(path = "/borrower/{cardNo}", consumes="application/json", produces = "application/json")
	public Borrower readBorrowerById(Integer cardNo)
	{
		// TODO
		return admin.readBorrowerById(cardNo).get();
	}

	@GetMapping(path = "/branch/{branchId}", consumes="application/json", produces = "application/json")
	public BookLoan readBookLoanById(BookLoanCompositeKey bookLoanCompositeKey)
	{
		// TODO
		return admin.readBookLoanById(bookLoanCompositeKey).get();
	}

}
