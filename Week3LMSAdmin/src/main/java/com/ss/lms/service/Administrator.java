package com.ss.lms.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.dao.AuthorDataAccess;
import com.ss.lms.dao.BookDataAccess;
import com.ss.lms.dao.BookLoanDataAccess;
import com.ss.lms.dao.BorrowerDataAccess;
import com.ss.lms.dao.LibraryBranchDataAccess;
import com.ss.lms.dao.PublisherDataAccess;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.BookLoanCompositeKey;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.LibraryBranch;
import com.ss.lms.entity.Publisher;

@Component
public class Administrator
{
	@Autowired
	private AuthorDataAccess authorDao;
	@Autowired
	private PublisherDataAccess publisherDao;
	@Autowired
	private BookDataAccess bookDao;
	@Autowired
	private LibraryBranchDataAccess libraryBranchDao;
	@Autowired
	private BorrowerDataAccess borrowerDao;
	@Autowired
	private BookLoanDataAccess bookLoanDao;

	/*************************************************
	 * 
	 * ALL CREATE OPERATIONS
	 * 
	 *************************************************/

	public void createAuthor(Author author)
	{
		try
		{
			// assigning new key
			author.setAuthorId(authorDao.generatePrimaryKey());

			// creating new author entry
			authorDao.insert(author);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void createPublisher(Publisher publisher)
	{
		try
		{
			// assigning new key
			publisher.setPublisherId(publisherDao.generatePrimaryKey());

			// creating new publisher entry
			publisherDao.insert(publisher);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void createBook(Book book)
	{
		try
		{
			// assigning new key
			book.setBookId(bookDao.generatePrimaryKey());

			// A new book much have exisiting correspinding publisher and author ID's
			// check the pub and auths exist

			ArrayList<Author> authorResult = authorDao.findById(new Author(book.getAuthor().getAuthorId(), "%"));
			if (authorResult.size() != 1)
			{
				System.out.println("Unique Author ID for " + book.getAuthor().getAuthorId() + " couldn't be found.");
				return;
			}

			ArrayList<Publisher> publisherResult = publisherDao
					.findById(new Publisher(book.getPublisher().getPublisherId(), "%", "%", "%"));
			if (publisherResult.size() != 1)
			{
				System.out.println(
						"Unique Publisher ID for " + book.getPublisher().getPublisherId() + " couldn't be found.");
				return;
			}

			// creating new book entry
			bookDao.insert(book);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void createLibraryBranch(LibraryBranch libraryBranch)
	{
		try
		{
			// assigning new key
			libraryBranch.setBranchId(libraryBranchDao.generatePrimaryKey());

			// creating new library branch
			libraryBranchDao.insert(libraryBranch);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void createBorrower(Borrower borrower)
	{
		try
		{
			// assigning new key
			borrower.setCardNo(borrowerDao.generatePrimaryKey());

			// creating new borrower
			borrowerDao.insert(borrower);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/*************************************************
	 * 
	 * ALL READ OPERATIONS
	 * 
	 *************************************************/

	public Optional<Author> readAuthorById(Integer authorId)
	{
		return authorDao.findById(authorId);
	}

	public Optional<Publisher> readPublisherById(Integer publisherId)
	{
		return publisherDao.findById(publisherId);
	}

	public Optional<Book> readBookById(Integer bookId)
	{
		return bookDao.findById(bookId);
	}

	public Optional<LibraryBranch> readLibraryBranchById(Integer branchId)
	{
		return libraryBranchDao.findById(branchId);
	}

	public Optional<Borrower> readBorrowerById(Integer cardNo)
	{
		return borrowerDao.findById(cardNo);
	}

	public Optional<BookLoan> readBookLoanById(BookLoanCompositeKey bookLoanCompositeKey)
	{
		return bookLoanDao.findById(bookLoanCompositeKey);
	}

	/*************************************************
	 * 
	 * ALL UPDATE OPERATIONS
	 * 
	 *************************************************/

	public void updateAuthor(Author author)
	{
		try
		{
			// get the existing data for the author sent in
			ArrayList<Author> oldData = authorDao.findById(new Author(author.getAuthorId(), "%"));

			// ensure exactly one exists
			if (oldData.size() != 1)
			{
				System.out.println("Unique Author ID for " + author.getAuthorId() + " couldn't be found.");
				return;
			} else
			{
				// substitute %'s, -1's, and 0001-01-01 with the existing data for this record
				switch (author.getAuthorName())
				{
				case "%":
					author.setAuthorName(oldData.get(0).getAuthorName());
					break;
				}
			}

			// write to the db the filled out author
			authorDao.update(author);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updatePublisher(Publisher publisher)
	{
		try
		{
			// get the existing data for the publisher sent in
			ArrayList<Publisher> oldData = publisherDao
					.findById(new Publisher(publisher.getPublisherId(), "%", "%", "%"));

			// ensure only one record exists
			if (oldData.size() != 1)
			{
				System.out.println("Unique Publisher ID for " + publisher.getPublisherId() + " couldn't be found.");
				return;
			} else
			{
				// substitute %'s, -1's, and 0001-01-01 with the existing data for this record
				switch (publisher.getPublisherName())
				{
				case "%":
					publisher.setPublisherName(oldData.get(0).getPublisherName());
					break;
				}

				switch (publisher.getPublisherAddress())
				{
				case "%":
					publisher.setPublisherAddress(oldData.get(0).getPublisherAddress());
					break;
				}
				switch (publisher.getPublisherPhone())
				{
				case "%":
					publisher.setPublisherPhone(oldData.get(0).getPublisherPhone());
					break;
				}
			}

			// write to the db the filled out author
			publisherDao.update(publisher);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateBook(Book book)
	{
		try
		{
			// Make sure the new Ids exist
			if (authorDao.findById(new Author(book.getAuthor().getAuthorId(), "%")).size() < 1)
			{
				System.out.println("Unique Author ID for " + book.getAuthor().getAuthorId() + " couldn't be found.");
				return;
			}

			if (publisherDao.findById(new Publisher(book.getPublisher().getPublisherId(), "%", "%", "%")).size() < 1)
			{
				System.out.println(
						"Unique Publisher ID for " + book.getPublisher().getPublisherId() + " couldn't be found.");
				return;
			}

			// get exisitng data for the book sent in
			ArrayList<Book> oldData = bookDao
					.findById(new Book(book.getBookId(), "%", new Author(-1, "%"), new Publisher(-1, "%", "%", "%")));

			if (oldData.size() != 1)
			{
				System.out.println("Unique Book ID for " + book.getBookId() + " couldn't be found.");
				return;
			} else
			{
				// substitute %'s, -1's, and 0001-01-01 with the existing data for this record
				switch (book.getTitle())
				{
				case "%":
					book.setTitle(oldData.get(0).getTitle());
				}

				switch (book.getAuthor().getAuthorId())
				{
				case -1:
					book.setAuthor(oldData.get(0).getAuthor());
					break;
				}

				switch (book.getPublisher().getPublisherId())
				{
				case -1:
					book.setPublisher(oldData.get(0).getPublisher());
					break;
				}
			}

			bookDao.update(book);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateLibraryBranch(LibraryBranch libraryBranch)
	{
		try
		{
			// get existing data for the branch sent in
			ArrayList<LibraryBranch> oldData = libraryBranchDao
					.findById(new LibraryBranch(libraryBranch.getBranchId(), "%", "%"));

			if (oldData.size() != 1)
			{
				System.out.println("Unique Branch ID for " + libraryBranch.getBranchId() + " couldn't be found.");
				return;
			} else
			{
				// substitute %'s, -1's, and 0001-01-01 with the existing data for this record
				switch (libraryBranch.getBranchName())
				{
				case "%":
					libraryBranch.setBranchName(oldData.get(0).getBranchName());
					break;
				}

				switch (libraryBranch.getBranchAddress())
				{
				case "%":
					libraryBranch.setBranchAddress(oldData.get(0).getBranchAddress());
					break;
				}
			}

			libraryBranchDao.update(libraryBranch);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateBorrower(Borrower borrower)
	{
		try
		{
			// get existing data for borrower sent in
			ArrayList<Borrower> oldData = borrowerDao.findById(new Borrower(borrower.getCardNo(), "%", "%", "%"));

			if (oldData.size() != 1)
			{
				System.out.println("Unique Card Number for " + borrower.getCardNo() + " couldn't be found.");
				return;
			} else
			{
				// substitute %'s, -1's, and 0001-01-01 with the existing data for this record
				switch (borrower.getName())
				{
				case "%":
					borrower.setName(oldData.get(0).getName());
					break;
				}

				switch (borrower.getAddress())
				{
				case "%":
					borrower.setAddress(oldData.get(0).getAddress());
					break;
				}

				switch (borrower.getPhone())
				{
				case "%":
					borrower.setPhone(oldData.get(0).getPhone());
					break;
				}
			}

			borrowerDao.update(borrower);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateBookLoan(BookLoan bookLoan)
	{
		try
		{
			// make sure new ids exist
			ArrayList<Book> bookQueryResult = bookDao.findById(new Book(bookLoan.getBook().getBookId(), "%",
					new Author(-1, "%"), new Publisher(-1, "%", "%", "%")));
			if (bookQueryResult.size() != 1)
			{
				System.out.println("Unique Book ID for " + bookLoan.getBook().getBookId() + " couldn't be found.");
				return;
			}

			ArrayList<LibraryBranch> libraryBranchResult = libraryBranchDao
					.findById(new LibraryBranch(bookLoan.getBranch().getBranchId(), "%", "%"));
			if (libraryBranchResult.size() != 1)
			{
				System.out
						.println("Unique Branch ID for " + bookLoan.getBranch().getBranchId() + " couldn't be found.");
				return;
			}

			ArrayList<Borrower> borrowerResult = borrowerDao
					.findById(new Borrower(bookLoan.getBorrower().getCardNo(), "%", "%", "%"));
			if (borrowerResult.size() != 1)
			{
				System.out.println(
						"Unique Card Number for " + bookLoan.getBorrower().getCardNo() + " couldn't be found.");
				return;
			}

			ArrayList<BookLoan> oldData = bookLoanDao
					.findById(new BookLoan(bookQueryResult.get(0), libraryBranchResult.get(0), borrowerResult.get(0),
							Date.valueOf("0001-01-01"), Date.valueOf("0001-01-01")));

			if (oldData.size() != 1)
			{
				System.out.println("Unique Book Loan with:" + "\nBook:\t" + bookQueryResult.get(0).getBookId() + "\t"
						+ bookQueryResult.get(0).getTitle() + "\nBranch:\t" + libraryBranchResult.get(0).getBranchId()
						+ "\t" + libraryBranchResult.get(0).getBranchName() + "\nBorrower:\t"
						+ borrowerResult.get(0).getCardNo() + "\t" + borrowerResult.get(0).getName()
						+ "\ncouldn't be found.");
				return;
			} else
			{
				// if the user sent a due date 0001-01-01, use existing data
				if (Date.valueOf("0001-01-01").equals(bookLoan.getDueDate()))
				{
					bookLoan.setDueDate(oldData.get(0).getDueDate());
				}

				bookLoan.setDateOut(oldData.get(0).getDateOut());
			}

			bookLoanDao.update(bookLoan);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/*************************************************
	 * 
	 * ALL DELETE OPERATIONS
	 * 
	 *************************************************/

	public void deleteAuthor(Author author)
	{
		try
		{
			authorDao.delete(author);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deletePublisher(Publisher publisher)
	{
		try
		{
			publisherDao.delete(publisher);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteBook(Book book)
	{
		try
		{
			bookDao.delete(book);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteLibraryBranch(LibraryBranch libraryBranch)
	{
		try
		{
			libraryBranchDao.delete(libraryBranch);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void deleteBorrower(Borrower borrower)
	{
		borrowerDao.delete(borrower);
	}
}
