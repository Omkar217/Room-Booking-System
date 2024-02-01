package com.nityaobject.roombooking.test;

import org.testng.annotations.Test;

import com.nityaobject.roombooking.BookingDetails;
import com.nityaobject.roombooking.Customer;
import com.nityaobject.roombooking.Room;
import com.nityaobject.roombooking.RoomBooking;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest 
{
	private RoomBooking roomBooking = new RoomBooking();


    @BeforeTest   
    public void setup()
    {
        BookingDetails currentBooking1 = new BookingDetails(1,1,"1/1/2022","5/1/2022",true,1045,0);

        roomBooking.setBookDetail1(currentBooking1);
    }
    
  
  public void f(Integer n, String s) {
  }
  
  @Test(dataProvider = "GetSampleData")
  public void testingBooking(String startDate) 
  {
	  System.out.println("start date : " + startDate);
		Room room1 = new Room(1,150,0);
	    Customer customer1 = new Customer("Ajay",1);
	    BookingDetails expectedBookingDetail = roomBooking.bookingRoom("1/1/2022", "5/1/2022", room1, customer1);
	  
	  Assert.assertEquals(roomBooking.getBookDetail1(), expectedBookingDetail);
  }

  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider(name = "GetSampleData")
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "startDate","1/1/2022" }
    };
  }
  
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
