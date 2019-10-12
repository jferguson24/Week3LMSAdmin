
package com.ss.lms.entity;

public class Borrower {

	Integer cardNo;
	String name;
	String address;
	String phone;
	
	public Borrower(){
		
	}
	
	public Borrower (Integer cardNo, String name, String address,
			String phone) {
		this.cardNo = cardNo;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	@Override
	public String toString() 
	{
		return  cardNo + "\t" + name + "\t" + address + "\t" + phone;
	}
	
	/**
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}