package com.ss.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer bookId;
	private String title;
	private Integer authorId;
	private Integer publisherId;

	public Book() {}
	
	public Book(Integer bookId, String title, Integer authorId, Integer publisherId)
	{
		this.bookId = bookId;
		this.title = title;
		this.authorId = authorId;
		this.publisherId = publisherId;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Book other = (Book) obj;
		if (authorId == null)
		{
			if (other.authorId != null)
			{
				return false;
			}
		} else if (!authorId.equals(other.authorId))
		{
			return false;
		}
		if (bookId == null)
		{
			if (other.bookId != null)
			{
				return false;
			}
		} else if (!bookId.equals(other.bookId))
		{
			return false;
		}
		if (publisherId == null)
		{
			if (other.publisherId != null)
			{
				return false;
			}
		} else if (!publisherId.equals(other.publisherId))
		{
			return false;
		}
		if (title == null)
		{
			if (other.title != null)
			{
				return false;
			}
		} else if (!title.equals(other.title))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "Book [bookId=" + bookId + ", title=" + title + ", authorId=" + authorId + ", publisherId=" + publisherId
				+ "]";
	}

	public Integer getBookId()
	{
		return bookId;
	}

	public void setBookId(Integer bookId)
	{
		this.bookId = bookId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Integer getAuthorId()
	{
		return authorId;
	}

	public void setAuthorId(Integer authorId)
	{
		this.authorId = authorId;
	}

	public Integer getPublisherId()
	{
		return publisherId;
	}

	public void setPublisherId(Integer publisherId)
	{
		this.publisherId = publisherId;
	}
	
	
}
