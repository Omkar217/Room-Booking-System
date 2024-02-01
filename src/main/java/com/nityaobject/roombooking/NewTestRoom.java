package com.nityaobject.roombooking;

import org.testng.annotations.Test;

import com.nityaobject.roombooking.BookingDetails;
import com.nityaobject.roombooking.Customer;
import com.nityaobject.roombooking.Room;
import com.nityaobject.roombooking.RoomBooking;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;


public class NewTestRoom
{
	private RoomBooking roomBooking = new RoomBooking();
	private SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private BookingDetails	expectedbookDetail1;
	private BookingDetails	expectedbookDetail2;
	private Room room1;
	private Room room2;
	private Customer customer1;
	private Customer customer2;
	private List<Date> expectedListOfDateOfRoom1 = new ArrayList<>();
  	private List<Date> expectedListOfDateOfRoom2 = new ArrayList<>();
	private List<BookingDetails> expectedListOfBookingDetails1 = new ArrayList<>();
	private List<BookingDetails> expectedListOfBookingDetails2 = new ArrayList<>();
	private int count = 1;
    
  
  @Test(dataProvider = "GetSampleDataForBookingPositive",priority = 1)
  public void testingBookingPositive(String startDate, String endDate , Room room1, Customer customer1, BookingDetails expectedBookingDetails) throws InvalidDateException, AlreadyBookedDates, ParseException, InvalidRoom 
  {
	 System.out.println("Room Bookings Positive: " + count++);
 
	BookingDetails actualBookingDetail = roomBooking.bookingRoom(startDate, endDate, room1, customer1);
	System.out.println( "actual : " + actualBookingDetail);
	System.out.println( "expected : " + expectedBookingDetails);

	Assert.assertEquals(actualBookingDetail,expectedBookingDetails);
	
}


  @DataProvider(name = "GetSampleDataForBookingPositive")
  public Object[][] dp34() throws GuestListException, ParseException {

	room1 = new Room(1,150,0);
	customer1 = new Customer("Ajay",1);
	  
	room2 = new Room(2,150,1);
	customer2 = new Customer("Vijay",2);
 
	Date date1 = SIMPLE_DATE_FORMAT.parse("1/1/2022");
  	Date date2 = SIMPLE_DATE_FORMAT.parse("5/1/2022");

  	expectedbookDetail1 = new BookingDetails(1,1,date1,date2,true,1045,0);
  	
  	Date date3 = SIMPLE_DATE_FORMAT.parse("6/1/2022");
  	Date date4 = SIMPLE_DATE_FORMAT.parse("10/1/2022");

  	expectedbookDetail2 = new BookingDetails(2,2,date3,date4,true,3520,1);
  	
  	BookingDetails expectedbookDetail3 = new BookingDetails(1,2,date1,date2,true,3520,1);

    return new Object[][] {
      {"1/1/2022 " ,"5/1/2022" ,room1, customer1,expectedbookDetail1},
//      {"1/1/2022 " ,"5/1/2022" ,room1, customer1,expectedbookDetail1},
      {"1/1/2022 " ,"5/1/2022" ,room2, customer1,expectedbookDetail3},
      {"6/1/2022 " ,"10/1/2022" ,room2, customer2,expectedbookDetail2},
    };
  }
  
  @Test(dataProvider = "GetSampleDataForBookingNegative" , expectedExceptions = {InvalidDateException.class ,AlreadyBookedDates.class,ParseException.class,InvalidRoom.class},priority =2)
  public void testingBookingNegative(String startDate, String endDate , Room room1, Customer customer1, BookingDetails expectedBookingDetails) throws InvalidDateException, AlreadyBookedDates, ParseException, InvalidRoom 
  {
	 System.out.println("Room Bookings Negative: " + count++);
	 System.out.println("start date : " + startDate + " End date : " + endDate + " room1 : " + room1);
 
	BookingDetails actualBookingDetail = roomBooking.bookingRoom(startDate, endDate, room1, customer1);
	System.out.println( "actual : " + actualBookingDetail);
	System.out.println( "expected : " + expectedBookingDetails);

	Assert.assertEquals(actualBookingDetail,expectedBookingDetails);

  }
  
  @DataProvider(name = "GetSampleDataForBookingNegative")
  public Object[][] dp() throws GuestListException, ParseException {

	room1 = new Room(1,150,0);
	customer1 = new Customer("Ajay",1);
	  
	room2 = new Room(2,150,1);
	customer2 = new Customer("Vijay",2);
 
	Date date1 = SIMPLE_DATE_FORMAT.parse("1/1/2022");
  	Date date2 = SIMPLE_DATE_FORMAT.parse("5/1/2022");

  	expectedbookDetail1 = new BookingDetails(1,1,date1,date2,true,1045,0);
  	
  	Date date3 = SIMPLE_DATE_FORMAT.parse("6/1/2022");
  	Date date4 = SIMPLE_DATE_FORMAT.parse("10/1/2022");

  	expectedbookDetail2 = new BookingDetails(2,2,date3,date4,true,3520,1);
  	
  	BookingDetails expectedbookDetail3 = new BookingDetails(1,2,date1,date2,true,3520,1);

    return new Object[][] {
      {"1/1/2022 " ,"5/1/2022" ,room1, customer1, expectedbookDetail1},
      {"1/1/2022 " ,"4/1/2022" ,room1, customer1, expectedbookDetail1},
      {"3/1/2022 " ,"99/1/2022" ,room1, customer1, expectedbookDetail1},
      {"1/1/2022 " ,"5/1/2022" ,room2, customer1, expectedbookDetail1},
      {"1/1/2022 " ,"5/1/2022" ,room1, customer1, expectedbookDetail1},
      {"1/1/2022 " ,"5/1/2022" ,room2, customer1, expectedbookDetail3},
    };
  } 
  

  
  @DataProvider(name = "GetSampleDataToCalculateBookingsNegative")
  public Object[][] dp19() throws GuestListException {


    return new Object[][] {
    	 {"6/1/2022","10/1/2022",4,3520},
    	 {"6/1/2022","10/1/2022",1,1045},
    	 {"11/1/2022","155/1/2022",2,3520},
    	 {"11/1/2022","15/1/2022",2,3520},
    	 {"1/1/2022","5/1/2022",4,1045},
    	 {"6/1/2022","10/1/2022",1,1045},
    };
  }
  
  
  @Test(dataProvider = "GetSampleDataToFindBookingsRoomIdPositive",priority =3)
  public void testingOfFindBookingByRoomIdPositive(int roomId, List<Date> expectedDateList) throws InvalidRoom
  {
	System.out.println(" Find bookings by Room Id  Positive:  " + count++);

	List<Date> actualDateList= new ArrayList<>();
	actualDateList.addAll(roomBooking.findBookingByRoomId(roomId));
	System.out.println(actualDateList);
	Assert.assertEquals(actualDateList,expectedDateList);
  }
  
  @DataProvider(name = "GetSampleDataToFindBookingsRoomIdPositive")
  public Object[][] dp16() throws GuestListException, ParseException  {

	  	Date date1 = SIMPLE_DATE_FORMAT.parse("1/1/2022");
	  	Date date2 = SIMPLE_DATE_FORMAT.parse("2/1/2022");
	  	Date date3 = SIMPLE_DATE_FORMAT.parse("3/1/2022");
	  	Date date4 = SIMPLE_DATE_FORMAT.parse("4/1/2022");
	  	Date date5 = SIMPLE_DATE_FORMAT.parse("5/1/2022");
	  
	  	Date date6 = SIMPLE_DATE_FORMAT.parse("6/1/2022");
	  	Date date7 = SIMPLE_DATE_FORMAT.parse("7/1/2022");
	  	Date date8 = SIMPLE_DATE_FORMAT.parse("8/1/2022");
	  	Date date9 = SIMPLE_DATE_FORMAT.parse("9/1/2022");
	  	Date date10 = SIMPLE_DATE_FORMAT.parse("10/1/2022"); 
	  	
	  	expectedListOfDateOfRoom1.add(date1);
	  	expectedListOfDateOfRoom1.add(date2);
	  	expectedListOfDateOfRoom1.add(date3);
	  	expectedListOfDateOfRoom1.add(date4);
	  	expectedListOfDateOfRoom1.add(date5);  	
	  	
		expectedListOfDateOfRoom2.add(date1);
	  	expectedListOfDateOfRoom2.add(date2);
	  	expectedListOfDateOfRoom2.add(date3);
	  	expectedListOfDateOfRoom2.add(date4);
	  	expectedListOfDateOfRoom2.add(date5); 
		expectedListOfDateOfRoom2.add(date6);
	  	expectedListOfDateOfRoom2.add(date7);
	  	expectedListOfDateOfRoom2.add(date8);
	  	expectedListOfDateOfRoom2.add(date9);
	  	expectedListOfDateOfRoom2.add(date10); 
	  	
	    return new Object[][] {
	    	 {1 ,expectedListOfDateOfRoom1},
	    	 {2 ,expectedListOfDateOfRoom2},
	    };
  }
  
  @Test(dataProvider = "GetSampleDataToFindBookingsRoomIdNegative",expectedExceptions = {InvalidRoom.class},priority =4)
  public void testingOfFindBookingByRoomIdNegative(int roomId, List<Date> expectedDateList) throws InvalidRoom
  {
	System.out.println(" Find bookings by Room Id  Negative:  " + count++);

	List<Date> actualDateList= new ArrayList<>();
	actualDateList.addAll(roomBooking.findBookingByRoomId(roomId));
	System.out.println(actualDateList);
	Assert.assertEquals(actualDateList,expectedDateList);

  }
  
  @DataProvider(name = "GetSampleDataToFindBookingsRoomIdNegative")
  public Object[][] dp178() throws GuestListException, ParseException  {
	  	
	    return new Object[][] {
	    	 {3 ,expectedListOfDateOfRoom1},
	    	 {4 ,expectedListOfDateOfRoom2},
	    	 {3 ,expectedListOfDateOfRoom2},
	    	 {4 ,expectedListOfDateOfRoom1},
	    };
  }
  
  @Test(dataProvider = "GetSampleDataToFindBookingsCustomerIdPositive",priority =5)
  public void testingOfFindBookingByCustomerIdPositive(int customerIdToBeSearched, List<BookingDetails> expectedListOfBookingDetails) throws InvalidCustomer
  {
	 System.out.println(" Find bookings by Customer Id Positive :  " + count++);

		 List<BookingDetails> actualBookingDetailsList= new ArrayList<>();
		 actualBookingDetailsList.addAll(roomBooking.findBookingByCustomerId(customerIdToBeSearched));
		 
		 System.out.println(" List is : " + actualBookingDetailsList);
		 Assert.assertEquals(actualBookingDetailsList,expectedListOfBookingDetails);

}

@DataProvider(name = "GetSampleDataToFindBookingsCustomerIdPositive")
    public Object[][] dp14() throws GuestListException, ParseException  
	{
		Date date1 = SIMPLE_DATE_FORMAT.parse("1/1/2022");
		Date date2 = SIMPLE_DATE_FORMAT.parse("5/1/2022");
		BookingDetails expectedbookDetail3 = new BookingDetails(1,2,date1,date2,true,3520,1);
  	
	  	expectedListOfBookingDetails1.add(expectedbookDetail1);
	  	
	 	expectedListOfBookingDetails1.add(expectedbookDetail3);
	   	
	  	expectedListOfBookingDetails2.add(expectedbookDetail2);  	

    return new Object[][] {
    	 {1 ,expectedListOfBookingDetails1},
    	 {2 ,expectedListOfBookingDetails2},
    };
  }

@Test(dataProvider = "GetSampleDataToFindBookingsCustomerIdNegative",expectedExceptions= {InvalidCustomer.class},priority =6)
public void testingOfFindBookingByCustomerIdNegative(int customerIdToBeSearched, List<BookingDetails> expectedListOfBookingDetails) throws InvalidCustomer
{
	System.out.println(" Find bookings by Customer Id Negative :  " + count++);
	
	List<BookingDetails> actualBookingDetailsList= new ArrayList<>();
	actualBookingDetailsList.addAll(roomBooking.findBookingByCustomerId(customerIdToBeSearched));
	Assert.assertEquals(actualBookingDetailsList,expectedListOfBookingDetails);
		 
}



@DataProvider(name = "GetSampleDataToFindBookingsCustomerIdNegative")
public Object[][] dp15() throws GuestListException, ParseException  
{
  return new Object[][] 
  {
	 {3 ,expectedListOfBookingDetails2},
	 {4 ,expectedListOfBookingDetails1},
  };
}

@Test(dataProvider = "GetSampleDataToCalculateBookingsPositive",priority = 7)
public void testingCalculateAmountBookingPositive(String startDate, String endDate, int singleroomID, double expectedCalculatedAmount ) throws InvalidRoom, ParseException, InvalidDateException
{
	System.out.println("Calculate total Amount positive : " + count++);
	
	double actualCalculatedAmount = roomBooking.calculateThetotalbillOfARoomFromParticularDate(startDate, endDate, singleroomID);
	System.out.println( "actual : " + actualCalculatedAmount);
	System.out.println( "expected : " + expectedCalculatedAmount);

	Assert.assertEquals(actualCalculatedAmount,expectedCalculatedAmount);
	
}

@DataProvider(name = "GetSampleDataToCalculateBookingsPositive")
public Object[][] dp13() throws GuestListException {

  return new Object[][] {
  	 {"1/1/2022","5/1/2022",1,1045},
  	 {"6/1/2022","10/1/2022",2,3520},
  	 {"1/1/2022","5/1/2022",2,3520},
  };
}

@Test(dataProvider = "GetSampleDataToCalculateBookingsNegative", expectedExceptions = {InvalidRoom.class, ParseException.class, InvalidDateException.class}, priority =8)
public void testingCalculateAmountBookingNegative(String startDate, String endDate, int singleroomID, double expectedCalculatedAmount ) throws InvalidRoom, ParseException, InvalidDateException
{
		System.out.println("Calculate total Amount Negative : " + count++);

		double actualCalculatedAmount = roomBooking.calculateThetotalbillOfARoomFromParticularDate(startDate, endDate, singleroomID);
		System.out.println( "actual : " + actualCalculatedAmount);
		System.out.println( "expected : " + expectedCalculatedAmount);

		Assert.assertEquals(actualCalculatedAmount,expectedCalculatedAmount);

}

  @Test(dataProvider = "GetSampleDataForCancellingBookingPositive",priority = 9)
  public void testCancelBookingPositive(int roomId, String startDate, String endDate, Customer customer1) throws InvalidRoom, InvalidDateException, ParseException, InvalidCustomer 
  {
	System.out.println(" Cancel Booking without Object positive : " + count++);

	assertTrue(roomBooking.cancelRoomforASpecificRangeOfdates(roomId, startDate, endDate, customer1));
  }
  
  @DataProvider(name = "GetSampleDataForCancellingBookingPositive")
  public Object[][] dp10() throws GuestListException 
  {
    return new Object[][] {
         {1 ,"1/1/2022","5/1/2022" ,customer1},
         {2 ,"1/1/2022","5/1/2022" ,customer1},
//         {2 ,"6/1/2022","10/1/2022" ,customer2},
    };
  }
  
  
  @Test(dataProvider = "GetSampleDataForCancellingBookingNegative",expectedExceptions = {InvalidRoom.class, ParseException.class, InvalidCustomer.class, InvalidDateException.class}, priority =10)
  public void testCancelBookingNegative(int roomId, String startDate, String endDate, Customer customer1) throws InvalidRoom, InvalidDateException, ParseException, InvalidCustomer 
  {
	 System.out.println(" Cancel Booking without Object Negative : " + count++);
	 assertTrue(roomBooking.cancelRoomforASpecificRangeOfdates(roomId, startDate, endDate, customer1));

  }
  @DataProvider(name = "GetSampleDataForCancellingBookingNegative")
  public Object[][] dp12() throws GuestListException 
  {
    return new Object[][] {
         {3 ,"1/1/2022","5/1/2022" ,customer1},
         {2 ,"1/1/2022","5/1/2022" ,customer2},
         {1 ,"1/1/2022","5/1/2022" ,customer2},
         {1 ,"1/1/2022","8/1/2022" ,customer1},
         {2 ,"4/1/2022","12/1/2022" ,customer2},
         {2 ,"8/1/2022","12/1/2022" ,customer2},
     	 {1 ,"1/1/2022","3/1/2022" ,customer1},
         {2 ,"1/1/2022","5/1/2022" ,customer1},
//         {2 ,"6/1/2022","10/1/2022" ,customer2},
         {1 ,"1/1/2022","5/1/2022" ,customer1},
    };
  }
  
@Test(priority = 11)
public void testCancelRoomforARangeOfdatesUsingObjectPositive() throws ParseException, InvalidDateException, InvalidRoom, InvalidCustomer 
{
	System.out.println("Using object cancel it  Positive: " + count++);	
	assertTrue(roomBooking.cancelRoomforARangeOfdatesUsingObject(expectedbookDetail2));
}
@Test(expectedExceptions = {InvalidDateException.class , InvalidRoom.class, InvalidCustomer.class},priority = 12) 
public void testCancelRoomforARangeOfdatesUsingObjectNegative() throws ParseException, InvalidDateException, InvalidRoom, InvalidCustomer 
{
	Date date1 = SIMPLE_DATE_FORMAT.parse("1/1/2022");
  	Date date2 = SIMPLE_DATE_FORMAT.parse("5/1/2022");
	System.out.println("Using object cancel it  Negative : " + count++);
	BookingDetails expectedNegativeBookingDetail1 = new BookingDetails(1,1,date1,date2,true,1045,1);
		
	assertTrue(roomBooking.cancelRoomforARangeOfdatesUsingObject(expectedNegativeBookingDetail1));	
}
  
}
