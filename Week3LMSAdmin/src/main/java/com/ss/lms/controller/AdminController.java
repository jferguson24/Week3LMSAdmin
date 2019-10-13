package com.ss.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.BookLoanCompositeKey;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.Administrator;


@RestController
@RequestMapping(value = "/lms/admin*")
public class AdminController
{
	@Autowired
	Administrator admin;

	
	/*************************************************
	 * 
	 * ALL CREATE OPERATIONS
	 * 
	 *************************************************/

//	@PutMapping(path = "/author/", consumes="application/json")
//	public void createAuthor(Author author) 
//	{
//		
//	}
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	@GetMapping(value = "/author/{authorId}", produces = "application/json")
	public ResponseEntity<Author> readAuthorById(@PathVariable Integer authorId)
	{
		if(authorId == null) // if the user sent empty values, 400
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Author> result = admin.readAuthorById(authorId);
		
		// if there is no entry for the entity, 404
		if(!result.isPresent()) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else // if we found an author, all good, 200
		{
			return new ResponseEntity<Author>(result.get(), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/author", produces = "application/json")
	public ResponseEntity<Iterable<Author>> readAuthorAll()
	{
		Iterable<Author> result = admin.readAuthorAll();
		
		// if there is less than one entity, 404
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else // if we found an author, all good, 200
		{
			return new ResponseEntity<Iterable<Author>>(result, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/publisher/{publisherId}", produces = "application/json")
	public Publisher readPublisherById(@PathVariable Integer publisherId)
	{
		// TODO
		return admin.readPublisherById(publisherId).get();
	}
	
	@GetMapping(value = "/publisher", produces = "application/json")
	public ResponseEntity<Iterable<Publisher>> readPublisherAll()
	{
		return new ResponseEntity<>(admin.readPublisherAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/book/{bookId}", produces = "application/json")
	public Book readBookById(@PathVariable Integer bookId)
	{
		// TODO
		return admin.readBookById(bookId).get();
	}

	@GetMapping(value = "/book", produces = "application/json")
	public ResponseEntity<Iterable<Book>> readBookAll()
	{
		// TODO
		return new ResponseEntity<>(admin.readBookAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/branch/{branchId}", produces = "application/json")
	public LibraryBranch readLibraryBranchById(@PathVariable Integer branchId)
	{
		// TODO
		return admin.readLibraryBranchById(branchId).get();
	}

	@GetMapping(value = "/branch", produces = "application/json")
	public ResponseEntity<Iterable<LibraryBranch>> readLibraryBranchAll()
	{
		// TODO
		return new ResponseEntity<>(admin.readLibraryBranchAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/borrower/{cardNo}", produces = "application/json")
	public Borrower readBorrowerById(@PathVariable Integer cardNo)
	{
		// TODO
		return admin.readBorrowerById(cardNo).get();
	}
	
	@GetMapping(value = "/borrower", produces = "application/json")
	public ResponseEntity<Iterable<Borrower>> readBorrowerByAll()
	{
		// TODO
		return new ResponseEntity<>(admin.readBorrowerAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/loan/borrower/{cardNo}/branch/{branchId}/book/{bookId}", produces = "application/json")
	public BookLoan readBookLoanByAllId(@PathVariable("cardNo") Integer cardNo, @PathVariable("branchId") Integer branchId, @PathVariable("bookId") Integer bookId)
	{
		// TODO
		BookLoanCompositeKey sentData = new BookLoanCompositeKey();
		sentData.setCardNo(cardNo);
		sentData.setBranchId(branchId);
		sentData.setBookId(bookId);
		
		return admin.readBookLoanById(sentData).get();
	}

	@GetMapping(value = "/loan", produces = "application/json")
	public ResponseEntity<Iterable<BookLoan>> readBookLoanAll()
	{
		// TODO
		return new ResponseEntity<>(admin.readBookLoanAll(), HttpStatus.OK);
	}
}
