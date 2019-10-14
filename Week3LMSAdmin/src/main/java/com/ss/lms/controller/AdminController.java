package com.ss.lms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.BookLoanCompositeKey;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.AdminService;


@RestController
@RequestMapping(value = "/lms/admin*")
public class AdminController
{
	@Autowired
	AdminService admin;

	
	/*************************************************
	 * 
	 * ALL CREATE OPERATIONS
	 * 
	 *************************************************/

	@PostMapping(path = "/author", produces = "application/json", consumes="application/json")
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) 
	{
		// make sure the id is null and other fields aren't
		// remember kids, always check for null values before checking the content of the value...
		if(author.getAuthorId() != null || author.getAuthorName() == null || "".contentEquals(author.getAuthorName()) ) 
		{
			return new ResponseEntity<Author>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Author>(admin.saveAuthor(author), HttpStatus.CREATED);
	}

	@PostMapping(path = "/publisher", produces = "application/json", consumes="application/json")
	public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) 
	{
		// make sure the id is null, and the other fields aren't
		if(publisher.getPublisherId() != null || publisher.getPublisherName() == null || "".contentEquals(publisher.getPublisherName())
											|| publisher.getPublisherAddress() == null || "".contentEquals(publisher.getPublisherAddress())
											|| publisher.getPublisherPhone() == null || "".contentEquals(publisher.getPublisherPhone())) 
		{
			return new ResponseEntity<Publisher>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Publisher>(admin.savePublisher(publisher), HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/book", produces = "application/json", consumes="application/json")
	public ResponseEntity<Book> createBook(@RequestBody Book book)
	{
		// make sure the id is null, and the other fields aren't
		if(book.getBookId() != null || book.getTitle() == null || "".contentEquals(book.getTitle())
									|| book.getAuthorId() == null || book.getPublisherId() == null)
		{
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		
		// check author exists
		if(!admin.readAuthorById(book.getAuthorId()).isPresent()) 
		{
			return new ResponseEntity<Book>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		// check publisher exists
		if(!admin.readPublisherById(book.getPublisherId()).isPresent())
		{
			return new ResponseEntity<Book>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		// create the entity
		return new ResponseEntity<Book>(admin.createBook(book), HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/branch", produces = "application/json", consumes="application/json")
	public ResponseEntity<LibraryBranch> createLibraryBranch(@RequestBody LibraryBranch libraryBranch)
	{
		// make sure the id is null, and the other fields aren't
		if(libraryBranch.getBranchId() != null || libraryBranch.getBranchName() == null || "".contentEquals(libraryBranch.getBranchName())
									|| libraryBranch.getBranchAddress() == null || "".contentEquals(libraryBranch.getBranchAddress()))
		{
			return new ResponseEntity<LibraryBranch>(HttpStatus.BAD_REQUEST);
		}
		
		// create the entity
		return new ResponseEntity<LibraryBranch>(admin.saveLibraryBranch(libraryBranch), HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/borrower", produces = "application/json", consumes="application/json")
	public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower)
	{
		// make sure the id is null, and the other fields aren't
		if(borrower.getCardNo() != null || borrower.getName() == null || "".contentEquals(borrower.getName())
										|| borrower.getAddress() == null || "".contentEquals(borrower.getAddress())
										|| borrower.getPhone() == null || "".contentEquals(borrower.getPhone()))
		{
			return new ResponseEntity<Borrower>(HttpStatus.BAD_REQUEST);
		}
		
		// create the entity
		return new ResponseEntity<Borrower>(admin.saveBorrower(borrower), HttpStatus.CREATED);
	}
	
	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	@GetMapping(value = "/author/{authorId}", produces = "application/json")
	public ResponseEntity<Author> readAuthorById(@PathVariable Integer authorId)
	{
		Optional<Author> result = admin.readAuthorById(authorId);
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.isPresent()) 
		{
			return new ResponseEntity<Author>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Author>(result.get(), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/author", produces = "application/json")
	public ResponseEntity<Iterable<Author>> readAuthorAll()
	{
		Iterable<Author> result = admin.readAuthorAll();

		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<Iterable<Author>>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Iterable<Author>>(result, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/publisher/{publisherId}", produces = "application/json")
	public ResponseEntity<Publisher> readPublisherById(@PathVariable Integer publisherId)
	{
		Optional<Publisher> result = admin.readPublisherById(publisherId);
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.isPresent()) 
		{
			return new ResponseEntity<Publisher>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Publisher>(result.get(), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/publisher", produces = "application/json")
	public ResponseEntity<Iterable<Publisher>> readPublisherAll()
	{
		Iterable<Publisher> result = admin.readPublisherAll();
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<Iterable<Publisher>>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Iterable<Publisher>>(result, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/book/{bookId}", produces = "application/json")
	public ResponseEntity<Book> readBookById(@PathVariable Integer bookId)
	{
		Optional<Book> result = admin.readBookById(bookId);
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.isPresent()) 
		{
			return new ResponseEntity<Book>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Book>(result.get(), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/book", produces = "application/json")
	public ResponseEntity<Iterable<Book>> readBookAll()
	{
		Iterable<Book> result = admin.readBookAll();
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<Iterable<Book>>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Iterable<Book>>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/branch/{branchId}", produces = "application/json")
	public ResponseEntity<LibraryBranch> readLibraryBranchById(@PathVariable Integer branchId)
	{
		Optional<LibraryBranch> result = admin.readLibraryBranchById(branchId);
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.isPresent()) 
		{
			return new ResponseEntity<LibraryBranch>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<LibraryBranch>(result.get(), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/branch", produces = "application/json")
	public ResponseEntity<Iterable<LibraryBranch>> readLibraryBranchAll()
	{
		Iterable<LibraryBranch> result = admin.readLibraryBranchAll();
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<Iterable<LibraryBranch>>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Iterable<LibraryBranch>>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/borrower/{cardNo}", produces = "application/json")
	public ResponseEntity<Borrower> readBorrowerById(@PathVariable Integer cardNo)
	{
		Optional<Borrower> result = admin.readBorrowerById(cardNo);
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.isPresent()) 
		{
			return new ResponseEntity<Borrower>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Borrower>(result.get(), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/borrower", produces = "application/json")
	public ResponseEntity<Iterable<Borrower>> readBorrowerByAll()
	{
		Iterable<Borrower> result = admin.readBorrowerAll();
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<Iterable<Borrower>>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Iterable<Borrower>>(result, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/loan/borrower/{cardNo}/branch/{branchId}/book/{bookId}", produces = "application/json")
	public ResponseEntity<BookLoan> readBookLoanByAllId(@PathVariable("cardNo") Integer cardNo, @PathVariable("branchId") Integer branchId, @PathVariable("bookId") Integer bookId)
	{
		if(cardNo == null || branchId == null || bookId == null) 
		{
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		}
		
		BookLoanCompositeKey sentData = new BookLoanCompositeKey();
		sentData.setCardNo(cardNo);
		sentData.setBranchId(branchId);
		sentData.setBookId(bookId);
		
		Optional<BookLoan> result = admin.readBookLoanById(sentData);
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.isPresent()) 
		{
			return new ResponseEntity<BookLoan>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<BookLoan>(result.get(), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/loan", produces = "application/json")
	public ResponseEntity<Iterable<BookLoan>> readBookLoanAll()
	{
		Iterable<BookLoan> result = admin.readBookLoanAll();
		
		// 200 regardless of if we found it or not, the query was successful, it means they can keep doing it
		if(!result.iterator().hasNext()) 
		{
			return new ResponseEntity<Iterable<BookLoan>>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Iterable<BookLoan>>(result, HttpStatus.OK);
		}
	}
	
	/*************************************************
	 * 
	 * ALL UPDATE OPERATIONS
	 * 
	 *************************************************/
	
	
	/*************************************************
	 * 
	 * ALL DELETE OPERATIONS
	 * 
	 *************************************************/
	
	@DeleteMapping(value = "/author/{authorId}")
	public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable Integer authorId)
	{
		if(!admin.readAuthorById(authorId).isPresent()) 
		{
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		else 
		{
			admin.deleteAuthorById(authorId);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/publisher/{publisherId}")
	public ResponseEntity<HttpStatus> deletePublisher(@PathVariable Integer publisherId)
	{
		if(!admin.readPublisherById(publisherId).isPresent()) 
		{
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		else 
		{
			admin.deletePublisherById(publisherId);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/book/{bookId}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable Integer bookId)
	{
		if(!admin.readBookById(bookId).isPresent()) 
		{
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		else 
		{
			admin.deleteBookById(bookId);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/branch/{branchId}")
	public ResponseEntity<HttpStatus> deleteLibraryBranch(@PathVariable Integer branchId)
	{
		if(!admin.readLibraryBranchById(branchId).isPresent()) 
		{
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		else 
		{
			admin.deleteLibraryBranchById(branchId);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/borrower/{cardNo}")
	public ResponseEntity<HttpStatus> deleteBorrower(@PathVariable Integer cardNo)
	{
		if(!admin.readBorrowerById(cardNo).isPresent())
		{
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
		else 
		{
			admin.deleteBorrowerById(cardNo);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
}
