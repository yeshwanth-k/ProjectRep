package com.crmindz.bcjmay2017.flightbooking.entity;


/**
 * @author Yeshwanth Konakanchi
 * Customer entity class with customer details
 */
public class Customer {
	
	private String firstName;
	private String lastName;
	private String emailID;
	private String phoneNumber;
	private String save;
	private Address addr;
	private BookingDetails bDetails;
	private Login login;
	private PaymentDetails pDetails;
	private CardDetails tpDetails;
	private TicketDetails tDetails;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {
		this.addr = addr;
	}
	public BookingDetails getbDetails() {
		return bDetails;
	}
	public void setbDetails(BookingDetails bDetails) {
		this.bDetails = bDetails;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public PaymentDetails getpDetails() {
		return pDetails;
	}
	public void setpDetails(PaymentDetails pDetails) {
		this.pDetails = pDetails;
	}
	public CardDetails getTpDetails() {
		return tpDetails;
	}
	public void setTpDetails(CardDetails tpDetails) {
		this.tpDetails = tpDetails;
	}
	public TicketDetails gettDetails() {
		return tDetails;
	}
	public void settDetails(TicketDetails tDetails) {
		this.tDetails = tDetails;
	}
	
	
	
}
