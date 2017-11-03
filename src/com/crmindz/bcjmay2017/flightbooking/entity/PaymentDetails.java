package com.crmindz.bcjmay2017.flightbooking.entity;

/**
 * @author Yeshwanth Konakanchi
 * Entity class with customer payment details
 */
public class PaymentDetails {
	private String ccNo;     // credit card number
	private String nameOnCard;
	private String cvv;		 // CVV Number for credit card	
	private String expiryMonth;
	private String expiryYear;
	public String getCcNo() {
		return ccNo;
	}
	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	
	
}
