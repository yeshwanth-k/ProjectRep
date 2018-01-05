package com.crmindz.bcjmay2017.flightbooking.dao;
//gitTest
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.crmindz.bcjmay2017.flightbooking.entity.BookingDetails;
import com.crmindz.bcjmay2017.flightbooking.entity.CardDetails;
import com.crmindz.bcjmay2017.flightbooking.entity.Customer;
import com.crmindz.bcjmay2017.flightbooking.entity.TicketDetails;

public class FlightBookingDAO {
	
	public static CardDetails getThirdPartyDetails(CardDetails cardDetails){
		
		CardDetails getcard = new CardDetails();
		try{
			Connection con = Util.getConnection();
			PreparedStatement pStatement= con.prepareStatement("select *  from Third_Party_Details where CC_No=? ");
			pStatement.setString(1, cardDetails.getCcNo());
			ResultSet result = pStatement.executeQuery();
			
			while(result.next()){
				getcard.setCcNo(result.getString("CC_No"));
				getcard.setCvv(result.getString("CVV"));
				getcard.setAvailableBalance(result.getString("Available_Balance"));
			}
		}
		catch(SQLException e){
			
			e.printStackTrace();
			System.out.println(e);
		}
		return getcard;
	}
	
	public static Customer registerCustomer(Customer customer){
		Customer customer1=new Customer();
		BookingDetails booking1=new BookingDetails();
		TicketDetails ticket1=new TicketDetails();
		int id=0, id1=0, id2=0;
		
		try{
			Connection con = Util.getConnection();
			String query1 = "insert into Customer (First_Name,Last_Name,Email_Id,Phone_Number)"+ "values (?,?,?,?)";
			PreparedStatement pStatement1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			pStatement1.setString(1, customer.getFirstName());
			pStatement1.setString(2, customer.getLastName());
			pStatement1.setString(3, customer.getEmailID());
			pStatement1.setString(4, customer.getPhoneNumber());
			pStatement1.executeUpdate();
			ResultSet rset = pStatement1.getGeneratedKeys();
			
			if(rset.next()){
				id = rset.getInt(1);
			}
			
			PreparedStatement selectQuery2 = con.prepareStatement("select First_Name from Customer where C_Id=? ");
			selectQuery2.setInt(1, id);
			ResultSet rsetQuery2 = selectQuery2.executeQuery();

			while (rsetQuery2.next()) {
				customer1.setFirstName(rsetQuery2.getString("First_Name"));
				}
		
		
			String query2 = "insert into Address(Address_line1, Address_line2, City, State, ZipCode, Customer_C_Id)"+"values(?,?,?,?,?,?)";
			PreparedStatement pStatement2 = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
			pStatement2.setString(1, customer.getAddr().getAddressLine1());
			pStatement2.setString(2, customer.getAddr().getAddressLine2());
			pStatement2.setString(3, customer.getAddr().getCity());
			pStatement2.setString(4, customer.getAddr().getState());
			pStatement2.setString(5, customer.getAddr().getZip());
			pStatement2.setInt(6, id);
			pStatement2.executeUpdate();
			
			String query3 = "insert into Login_Details(User_Name, Password, Customer_C_Id)"+"values(?,?,?)";
			PreparedStatement pStatement3 = con.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
			
			pStatement3.setString(1, customer.getLogin().getUserID());
			pStatement3.setString(2, customer.getLogin().getPassword());
			pStatement3.setInt(3, id);
			pStatement3.executeUpdate();
			
			ResultSet rset1 = pStatement3.getGeneratedKeys();
			
			if(rset1.next()){
				id1 = rset1.getInt(1);
			}
			
			String check = customer.getSave();
			if("save".equals(check))
			{
				String query4 = "insert into Save_Payment_Details(CC_No, Name_on_Card, CVV, Expiry_Month, Expiry_Year,Login_Details_U_Id )"+ "values(?,?,?,?,?,?)";
				PreparedStatement pStatement4 = con.prepareStatement(query4, Statement.RETURN_GENERATED_KEYS);
				pStatement4.setString(1, customer.getpDetails().getCcNo());
				pStatement4.setString(2, customer.getpDetails().getNameOnCard());
				pStatement4.setString(3, customer.getpDetails().getCvv());
				pStatement4.setString(4, customer.getpDetails().getExpiryMonth());
				pStatement4.setString(5, customer.getpDetails().getExpiryYear());
				pStatement4.setInt(6, id1);
				pStatement4.executeUpdate();
			}
			
			String query5 = "insert into Booking_Details(Reference_No, Itenary_No, Booking_Date, Fare, Customer_C_Id)"+"values(?,?,?,?,?)";
				PreparedStatement pStatement5 = con.prepareStatement(query5, Statement.RETURN_GENERATED_KEYS);
				pStatement5.setString(1, customer.getbDetails().getReferenceNo());
				pStatement5.setString(2, customer.getbDetails().getItenaryNo());
				pStatement5.setDate(3, customer.getbDetails().getBookingDate());
				pStatement5.setString(4, customer.getbDetails().getFare());
				pStatement5.setInt(5, id);
				pStatement5.executeUpdate();
				ResultSet rset2= pStatement5.getGeneratedKeys();
				if(rset2.next()){
					id2= rset2.getInt(1);
				}
				
				PreparedStatement selectQuery = con.prepareStatement("select Reference_No, Itenary_No,Fare from Booking_Details where Itenary_No=? ");
				selectQuery.setString(1, customer.getbDetails().getItenaryNo());
				ResultSet rsetQuery = selectQuery.executeQuery();

				while (rsetQuery.next()) {
					booking1.setReferenceNo(rsetQuery.getString("Reference_No"));
					booking1.setItenaryNo(rsetQuery.getString("Itenary_No"));
					booking1.setFare(rsetQuery.getString("Fare"));
				}
		
		
			String query6 = "insert into Ticket_Details(Arrival, Departure, Booking_Details_B_Id)"+"values(?,?,?)";
			PreparedStatement pStatement6 = con.prepareStatement(query6, Statement.RETURN_GENERATED_KEYS);
			pStatement6.setString(1, customer.gettDetails().getFrom());
			pStatement6.setString(2, customer.gettDetails().getTo());
			pStatement6.setInt(3, id2);
			pStatement6.executeUpdate();
			
			ResultSet rsetTicket = pStatement6.getGeneratedKeys();

			int ticketID=0;
			if (rsetTicket.next()) {
				ticketID = rsetTicket.getInt(1);
			}
	
	
			PreparedStatement selectQuery1 = con.prepareStatement("select Arrival,Departure from Ticket_Details where Ticket_Id=? ");
			selectQuery1.setInt(1, ticketID);
			ResultSet rsetQuery1 = selectQuery1.executeQuery();

			while (rsetQuery1.next()) {
				ticket1.setFrom(rsetQuery1.getString("Arrival"));
				ticket1.setTo(rsetQuery1.getString("Departure"));
			}
			
		}
		catch(Exception e2){
			e2.printStackTrace();
			System.out.println(e2);
			
		}
		customer1.setbDetails(booking1);
		customer1.settDetails(ticket1);
		return customer1;
	}

}
