package com.crmindz.bcjmay2017.flightbooking.service;

import java.sql.Date;

import com.crmindz.bcjmay2017.flightbooking.dao.FlightBookingDAO;
import com.crmindz.bcjmay2017.flightbooking.entity.BookingDetails;
import com.crmindz.bcjmay2017.flightbooking.entity.CardDetails;
import com.crmindz.bcjmay2017.flightbooking.entity.Customer;

public class FlightBookingService {
	
	public static String RandomNumber(long number){
		long n = (long) (Math.random()*number);
		return Long.toString(n);
	}
	//gitTest2
	
	public static boolean validateCardDetails(CardDetails cardDetails, BookingDetails booking){
		
		CardDetails getDetails = FlightBookingDAO.getThirdPartyDetails(cardDetails);
		if(cardDetails.getCcNo().equals(getDetails.getCcNo()) && cardDetails.getCvv().equals(getDetails.getCvv()))
		{
			int availableBalance= Integer.parseInt(getDetails.getAvailableBalance());
			int fare = Integer.parseInt(booking.getFare());
			if(fare<availableBalance)
			return true;  
			else return false;
		}
		
		else{return false;}
	}
	
	public static Customer getCustomerBookingDetails(Customer customer, BookingDetails booking){
		booking.setReferenceNo(RandomNumber(2000000));
		booking.setItenaryNo(RandomNumber(1234567));
		
		long millis=System.currentTimeMillis();
		Date date = new Date(millis);
		booking.setBookingDate(date);
		customer.setbDetails(booking);
		
		Customer customer1=FlightBookingDAO.registerCustomer(customer);
		
			return customer1;
		
		
	}
	
	

}
