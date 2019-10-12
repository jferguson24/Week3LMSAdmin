/**
 * 
 */
package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;


@Component
public class BookDataAccess extends DataAccess<Book>
{
	public BookDataAccess() throws SQLException, ClassNotFoundException
	{
		super();
	}

	@Override
	public void insert(Book entity) throws SQLException
	{
		PreparedStatement query;
		String sql;
		// query = con.createStatement();
		sql = "insert into tbl_book (bookId, title, authId, pubId)" + "values (?,?,?,?);";

		query = con.prepareStatement(sql);
		query.setInt(1, entity.getBookId());
		query.setString(2, entity.getTitle());
		query.setInt(3, entity.getAuthor().getAuthorId());
		query.setInt(4, entity.getPublisher().getPublisherId());

		query.executeUpdate();

	}

	@Override
	public ArrayList<Book> find(Book entity) throws SQLException
	{
		// System.out.println("Hello." + entity.getPublisher().getPublisherId());
		// TODO Auto-generated method stub
		String sql;
		int findBookId = entity.getBookId();
		// System.out.println("bookId: "+ findBookId);

		String findTitle = entity.getTitle();
		// System.out.println("findTitle: "+ findTitle);

		int findAuthor = entity.getAuthor().getAuthorId();
		// System.out.println("findAuthor: "+ findAuthor);

		int findPublisher = entity.getPublisher().getPublisherId();
		// System.out.println("findPublisher: "+ findPublisher);

		String strBookId = "bookId = ? ";
		String strTitle = "title LIKE ? ";
		String strAuthor = "authId = ? ";
		String strPub = "pubId = ? ";

		// System.out.println("Try this1.");

		ResultSet result;
		PreparedStatement query;
		if (findBookId == -1)
		{
			strBookId = ("bookId > ? ");
		}
		if (findTitle == "%")
		{
			strTitle = ("title LIKE ? ");
		}
		if (findAuthor == -1)
		{
			strAuthor = ("authId > ? ");
		}
		if (findPublisher == -1)
		{
			strPub = ("pubId > ?");
		}

		// System.out.println("Try this.");

		sql = "select * from tbl_book " + "where " + strBookId + "and " + strTitle + "and " + strAuthor + "and "
				+ strPub;

		// System.out.println("Hello2");

		query = con.prepareStatement(sql);
		query.setInt(1, findBookId);
		query.setString(2, findTitle);
		query.setInt(3, findAuthor);
		query.setInt(4, findPublisher);

		// System.out.println(query.toString());

		result = query.executeQuery();

		return packageResultSet(result);
	}

	@Override
	public void update(Book entity) throws SQLException
	{
		// TODO Auto-generated method stub
		PreparedStatement query;
		String sql;

		String newTitle = entity.getTitle();
		int authorId = entity.getAuthor().getAuthorId();
		int publisherId = entity.getPublisher().getPublisherId();

//		sql = "select * from tbl_book "
//				+ "where bookId = ?";
//		query = con.prepareStatement(sql);
//		query.setInt(1, entity.getBookId());
//		
//		String title = "";
//		String author = "";
//		String publisher = "";
//		
//		//ResultSet result = query.executeQuery();
//		
//		if(newTitle == "%" && authorId == -1 && publisherId == -1) {
//			return;
//		}
//		
//		if(newTitle != "%") {
//			title = " title = ? and";
//			//newTitle = result.getString(2);
//		}
//		if(authorId == -1) {
//			author = " authId = ?";
//			//authorId = result.getInt(3);
//		}
//		if(publisherId == -1) {
//			publisher = " pubId = ?";
//			//publisherId = result.getInt(4);
//		}
//		
		sql = "update tbl_book set " + "title = ?, " + "authId = ?, " + "pubId = ? " + "where bookId = ?";
		query = con.prepareStatement(sql);
		query.setString(1, newTitle);
		query.setInt(2, authorId);
		query.setInt(3, publisherId);
		query.setInt(4, entity.getBookId());

		query.executeUpdate();

	}

	@Override
	public void delete(Book entity) throws SQLException
	{
		// TODO Auto-generated method stub
		PreparedStatement query;
		String sql;
		sql = "delete from tbl_book where bookId = ?";
		query = con.prepareStatement(sql);

		query.setInt(1, entity.getBookId());

		query.executeUpdate();
	}

	public ArrayList<Book> packageResultSet(ResultSet result) throws SQLException
	{
		PreparedStatement query;
		String sql;
		ArrayList<Book> bookList = new ArrayList<Book>();
		while (result.next())
		{
			sql = "select * from tbl_author, tbl_book where bookId = ? and authorId = authId";
			query = con.prepareStatement(sql);
			query.setInt(1, result.getInt(1));
			// System.out.println(query.toString());
			ResultSet resultAuthor = query.executeQuery();
			resultAuthor.next();
			Author author = new Author(resultAuthor.getInt(1), resultAuthor.getString(2));
			sql = "select * from tbl_publisher, tbl_book where bookId = ? and publisherId = pubId";
			query = con.prepareStatement(sql);
			query.setInt(1, result.getInt(1));

			// System.out.println(query.toString());
			ResultSet resultPublisher = query.executeQuery();
			resultPublisher.next();
			Publisher publisher = new Publisher(resultPublisher.getInt(1), resultPublisher.getString(2),
					resultPublisher.getString(3), resultPublisher.getString(4));
//			sql = "select * from tbl_book where bookId = ?";
//			query = con.prepareStatement(sql);
//			query.setInt(1, result.getInt(1));
			Book book = new Book(result.getInt(1), result.getString(2), author, publisher);
			bookList.add(book);
		}
		return bookList;
	}

	@Override
	public Integer generatePrimaryKey() throws SQLException
	{
		String sql = "SELECT MAX(bookId) AS max FROM library.tbl_book;";
		Statement query = con.createStatement();

		ResultSet result = query.executeQuery(sql);
		result.next();

		return (result.getInt("max") + 1);
	}
}
