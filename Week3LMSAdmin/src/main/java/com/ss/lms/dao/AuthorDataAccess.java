package com.ss.lms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Publisher;

public class AuthorDataAccess extends DataAccess<Author>
{

	public AuthorDataAccess() throws SQLException, ClassNotFoundException
	{
		super();
	}

	@Override
	public void insert(Author entity) throws SQLException
	{
		PreparedStatement query;
		String sql = "INSERT INTO library.tbl_author(authorId,authorName) " + "VALUES (?, ?);";

		query = con.prepareStatement(sql);
		query.setInt(1, entity.getAuthorId());
		query.setString(2, entity.getAuthorName());

		query.executeUpdate();
	}

	@Override
	public ArrayList<Author> find(Author entity) throws SQLException
	{
		ArrayList<Publisher> publishers = new ArrayList<Publisher>();
		ResultSet result;
		PreparedStatement query;
		String sql;

		if (entity.getAuthorId() == -1)
		{
			sql = "SELECT * FROM library.tbl_author " + "WHERE authorId > ? " // index 1
					+ "AND authorName LIKE ?;"; // index 2
		} else
		{
			sql = "SELECT * FROM library.tbl_author " + "WHERE authorId = ? " // index 1
					+ "AND authorName LIKE ?;"; // index 2
		}

		query = con.prepareStatement(sql);
		query.setInt(1, entity.getAuthorId());
		query.setString(2, entity.getAuthorName());

		result = query.executeQuery();

		return packageResultSet(result);
	}

	@Override
	public void update(Author entity) throws SQLException
	{
		PreparedStatement query;
		String sql = "UPDATE library.tbl_author SET " + "authorName = ? " // index 1
				+ "WHERE authorId = ?;"; // index 2

		query = con.prepareStatement(sql);
		query.setInt(2, entity.getAuthorId());
		query.setString(1, entity.getAuthorName());

		query.executeUpdate();
	}

	@Override
	public void delete(Author entity) throws SQLException
	{
		PreparedStatement query;
		String sql = "DELETE FROM library.tbl_author WHERE " + "authorId = ?;"; // index 1

		query = con.prepareStatement(sql);
		query.setInt(1, entity.getAuthorId());

		query.executeUpdate();
	}

	@Override
	public ArrayList<Author> packageResultSet(ResultSet result) throws SQLException
	{
		ArrayList<Author> output = new ArrayList<Author>();
		Author author;

		// TODO why dont this work?
		// output.addAll(ResultSetUtils.getCollection(new Publisher(), result));
		// https://commons.apache.org/dormant/scaffold/apidocs/org/apache/commons/scaffold/sql/ResultSetUtils.html

		while (result.next())
		{
			author = new Author();

			author.setAuthorId(result.getInt("authorId")); // get pk
			author.setAuthorName(result.getString("authorName")); // get name

			output.add(author);
		}

		return output;
	}

	@Override
	public Integer generatePrimaryKey() throws SQLException
	{
		String sql = "SELECT MAX(authorId) AS max FROM library.tbl_author;";
		Statement query = con.createStatement();

		ResultSet result = query.executeQuery(sql);
		result.next();

		return (result.getInt("max") + 1);
	}
}
