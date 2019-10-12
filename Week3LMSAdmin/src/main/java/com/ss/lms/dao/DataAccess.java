package com.ss.lms.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public abstract class DataAccess<T extends Serializable>
{
	Connection con;

	public DataAccess() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "");
	}

	public void close() throws SQLException
	{
		con.close();
	}

	public abstract void insert(T entity) throws SQLException;

	public abstract ArrayList<T> find(T entity) throws SQLException;

	public abstract void update(T entity) throws SQLException;

	public abstract void delete(T entity) throws SQLException;

	public abstract ArrayList<T> packageResultSet(ResultSet result) throws SQLException;

	public abstract Integer generatePrimaryKey() throws SQLException;
}
