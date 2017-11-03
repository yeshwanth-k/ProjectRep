package com.crmindz.bcjmay2017.flightbooking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crmindz.bcjmay2017.flightbooking.entity.Address;
import com.crmindz.bcjmay2017.flightbooking.entity.BookingDetails;
import com.crmindz.bcjmay2017.flightbooking.entity.Customer;
import com.crmindz.bcjmay2017.flightbooking.entity.Login;
import com.crmindz.bcjmay2017.flightbooking.entity.PaymentDetails;
import com.crmindz.bcjmay2017.flightbooking.entity.CardDetails;
import com.crmindz.bcjmay2017.flightbooking.entity.TicketDetails;
import com.crmindz.bcjmay2017.flightbooking.service.FlightBookingService;

/**
 * Servlet implementation class FlightBookingServlet
 */
@WebServlet("/FlightBookingServlet")
public class FlightBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Customer customer = new Customer();
		Address address = new Address();
		BookingDetails booking = new BookingDetails();
		Login login = new Login();
		PaymentDetails savepaymentDetails= new PaymentDetails();
		CardDetails cardDetails = new CardDetails();
		TicketDetails ticketDetails = new TicketDetails();
		
		
		
		String addressLine1 = request.getParameter("addressline1");
		String addressLine2 = request.getParameter("addressline2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		
		//System.out.println(request.getAttribute("zip"));
		
		System.out.println(addressLine1);
		
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		System.out.println(userId);
		
		String nameOnCard = request.getParameter("nameoncard");
		String ccNo = request.getParameter("ccno");
		String cvv = request.getParameter("cvv");
		String expMonth = request.getParameter("month");
		String expYear = request.getParameter("year");
		String checked = request.getParameter("save");
		
		String from = request.getParameter("source");
		String to = request.getParameter("destination");
		String departDate = request.getParameter("departdate");
		String returnDate = request.getParameter("other_text");
		String fare = request.getParameter("fare");

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String emailId = request.getParameter("emailid");
		String phoneNumber = request.getParameter("phonenumber");
		
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		
		login.setUserID(userId);
		login.setPassword(password);
		
		cardDetails.setNameOnCard(nameOnCard);
		cardDetails.setExpiryMonth(expMonth);
		cardDetails.setExpiryYear(expYear);
		cardDetails.setCcNo(ccNo);
		cardDetails.setCvv(cvv);
		
		if("save".equals(checked))
		{
			savepaymentDetails.setCcNo(ccNo);
			savepaymentDetails.setCvv(cvv);
			savepaymentDetails.setExpiryMonth(expMonth);
			savepaymentDetails.setExpiryYear(expYear);
			savepaymentDetails.setNameOnCard(nameOnCard);
		}
		
		ticketDetails.setFrom(from);
		ticketDetails.setTo(to);
		ticketDetails.setTravelDate(departDate);
		
		booking.setFare(fare);
		
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmailID(emailId);
		customer.setPhoneNumber(phoneNumber);
		customer.setSave(checked);
		customer.setAddr(address);
		customer.setbDetails(booking);
		customer.setTpDetails(cardDetails);
		customer.settDetails(ticketDetails);
		customer.setpDetails(savepaymentDetails);
		customer.setLogin(login);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		boolean result = FlightBookingService.validateCardDetails(cardDetails, booking);
		if(result){
			Customer customer_1 = FlightBookingService.getCustomerBookingDetails(customer, booking);
			out.println("Thank you"+customer_1.getFirstName()+ " for choosing JetBlue Airlines.");
			out.println("Your Reference Number: "+ customer_1.getbDetails().getReferenceNo()+".");
			out.println("Your Itenary Number: "+ customer_1.getbDetails().getItenaryNo()+".");
			out.println("Depart in: "+ customer_1.gettDetails().getFrom()+",");
			out.println("Arrive at: "+ customer_1.gettDetails().getTo()+".");
			out.println("Happy FLying!!");
			
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			out.println("Invalid Card Details");
			rd.include(request, response);
		}

		
	}

}
