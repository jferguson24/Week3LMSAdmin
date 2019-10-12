package com.ss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.*;
import com.ss.lms.service.Administrator;

@RestController
public class AdminController
{
	@Autowired
	Administrator admin;
	
	@RequestMapping(path="/lms/author/readAuthor")
	public Author readAuthor(Author author) 
	{
		return author;
	}
	
	
}
