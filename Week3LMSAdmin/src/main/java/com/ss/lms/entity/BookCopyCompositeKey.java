package com.ss.lms.entity;

import java.io.Serializable;

public class BookCopyCompositeKey implements Serializable
{
	private static final long serialVersionUID = 7453106781264031549L;
	
	private Integer bookid;
	private Integer branchId;
	
	public BookCopyCompositeKey() {}
	
	public BookCopyCompositeKey(Integer bookid, Integer branchId)
	{
		super();
		this.bookid = bookid;
		this.branchId = branchId;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookid == null) ? 0 : bookid.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopyCompositeKey other = (BookCopyCompositeKey) obj;
		if (bookid == null)
		{
			if (other.bookid != null)
				return false;
		} else if (!bookid.equals(other.bookid))
			return false;
		if (branchId == null)
		{
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return "BookCopyCompositeKey [bookid=" + bookid + ", branchId=" + branchId + "]";
	}
	public Integer getBookid()
	{
		return bookid;
	}
	public void setBookid(Integer bookid)
	{
		this.bookid = bookid;
	}
	public Integer getBranchId()
	{
		return branchId;
	}
	public void setBranchId(Integer branchId)
	{
		this.branchId = branchId;
	}
	
	
}
