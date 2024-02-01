
package com.nityaobject.roombooking.main;

import com.nityaobject.roombooking.AlreadyBookedDates;
import com.nityaobject.roombooking.BookingDetails;
import com.nityaobject.roombooking.Customer;
import com.nityaobject.roombooking.CustomerRegister;
import com.nityaobject.roombooking.GuestListException;
import com.nityaobject.roombooking.InvalidCustomer;
import com.nityaobject.roombooking.InvalidDateException;
import com.nityaobject.roombooking.InvalidRoom;
import com.nityaobject.roombooking.Room;
import com.nityaobject.roombooking.RoomBooking;

import java.text.ParseException;


public class Main 
{

    public static void main(String[] args) throws InvalidDateException, AlreadyBookedDates,InvalidRoom, GuestListException, ParseException, InvalidCustomer 
    {

        Room room1 = new Room(1,150,0);
        Room room2 = new Room(2,150,1);
        Room room3 = new Room(3,350,2);

        Customer customer1 = new Customer("Ajay",1);
        Customer customer2 = new Customer("Raju",2);
        Customer customer3 = new Customer("Vijay",3);

        RoomBooking hotelbooking1= new RoomBooking();
        
        BookingDetails bookingdetails1 = hotelbooking1.bookingRoom("1/1/2022", "5/1/2022", room1, customer1);
//	System.out.println(" The booking details are : " + hotelbooking1.bookingRoom("16/1/2022", "6/2/2022", room1,customer1));//startdate,enddate,room..
        BookingDetails bookingdetails2 = hotelbooking1.bookingRoom("2/1/2022", "5/1/2022", room2,customer2);//startdate,enddate,room..

        System.out.println("---------------------------- For booking room test cases------------------------------");
        try
        {
            if(hotelbooking1.bookingRoom("6/1/2022", "11/1/2022", room1,customer1)!=null)
            {
                System.out.println("SUCCESS");
            }
            else
            {
                System.out.println("FAIL");
            }
        }
        catch(AlreadyBookedDates e)
        {
            System.out.println("FAILED");
            System.out.println(e.getMessage());
        }
    //	System.out.println(" The booking details are : " + hotelbooking1.calculateThetotalbillOfARoomFromParticularDate("6/1/2022", "11/1/2022", 1));
        try
        {
            if(hotelbooking1.bookingRoom("6/1/2022", "10/1/2022", room2,customer2)!=null)
            {
                System.out.println("SUCCESS");
            }
            else
            {
                System.out.println("FAILED");
            }
        }
        catch(AlreadyBookedDates e)
        {
            System.out.println("FAILED");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    	System.out.println(" The booking details are : " + hotelbooking1.calculateThetotalbillOfARoomFromParticularDate("6/1/2022", "10/1/2022", 2));
    	System.out.println(hotelbooking1.findBookingByCustomerId(2));


//        try
//        {
//            if(hotelbooking1.bookingRoom("1/1/2022", "8/1/2022", room2,customer2) == null)
//            {
//                System.out.println("FAIL");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//        catch(AlreadyBookedDates e)
//        {
//            System.out.println("SUCCESS");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        try
//        {
//            if(hotelbooking1.bookingRoom("1/1/2022", "3/1/2022", room1,customer1) == null)
//            {
//                System.out.println("FAIL");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//        catch(AlreadyBookedDates e)
//        {
//                System.out.println("SUCCESS");
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//        }
//
//        try
//        {
//            if(hotelbooking1.bookingRoom("4/1/2022", "8/1/2022", room1,customer1) != null)
//            {
//                System.out.println("FAILED");
//            }
//            else
//            {
//                System.out.println("FAILED");
//            }
//        }
//        catch(AlreadyBookedDates e)
//        {
//            System.out.println("SUCCESS");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//        hotelbooking1.findBookingByCustomerId(1);
//
//        System.out.println("-------------------------- For cancelling  room test cases using Object-------------------------------------");
//        try
//        {
//            if(hotelbooking1.cancelRoomforASpecificRangeOfdatesUsingBookingDetailsObject(bookingdetails2))//Here I am cancelling customer 2 whole bookings by passing object.......
//            {
//                System.out.println("SUCCESS");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//         catch(InvalidRoom e) 
//        {
//            System.out.println("FAIL");
//        } 
//        catch(InvalidDateException e) 
//        {
//            System.out.println("FAIL");
//            System.out.println(e.getMessage());
//        }
//    //    hotelbooking1.printAllCustomerNameAndtheirRespectiveBookings();
//
//        System.out.println("-------------------------- For cancelling  room test cases-------------------------------------");
// //       hotelbooking1.bookingRoom("1/1/2022", "15/1/2022", room1, customer1);//        
        try 
        {
            if(hotelbooking1.cancelRoomforASpecificRangeOfdates(1, "1/1/2022", "5/1/2022",customer1))
            {
                System.out.println("SUCCESS");
            } 
            else 
            {
                System.out.println("FAIL");
            }
        } 
        catch(InvalidRoom e) 
        {
            System.out.println("FAIL");
        } 
        catch(InvalidDateException e) 
        {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
        }
        try 
        {
            if(hotelbooking1.cancelRoomforASpecificRangeOfdates(2, "2/1/2022", "5/1/2022",customer2))
            {
                System.out.println("SUCCESS");
            } 
            else 
            {
                System.out.println("FAIL");
            }
        } 
        catch(InvalidRoom e) 
        {
            System.out.println("FAIL");
        } 
        catch(InvalidDateException e) 
        {
            System.out.println("FAIL");
            System.out.println(e.getMessage());
        }
        System.out.println(hotelbooking1.getCustomerRecord().getCustomerIdToCustomerMap());

//        try 
//        {
//            if(hotelbooking1.cancelRoomforASpecificRangeOfdates(1, "1/1/2022", "5/1/2022",customer1))//Room Id,startDate,endDate............
//            {
//                System.out.println("SUCCESS");
//            } 
//            else 
//            {
//                System.out.println("FAIL");
//            }
//        } 
//        catch(InvalidCustomer e)
//        {
//            System.out.println("FAIL");
//            System.out.println(e.getMessage());
//        }
//        catch(InvalidRoom e) 
//        {
//            System.out.println(e.getMessage());
//            System.out.println("FAIL");
//        } 
//        catch(InvalidDateException e) 
//        {
//            System.out.println("FAIL");
//            System.out.println(e.getMessage());
//        }
//        try
//        {
//            hotelbooking1.findBookingByCustomerId(2);
//        }
//        catch(InvalidCustomer e)
//        {
//            System.out.println("FAILs");
//            System.out.println(e.getMessage());
//        }
//        try
//        {
//            hotelbooking1.findBookingByCustomerId(3);
//        }
//        catch(InvalidCustomer e)
//        {
//            System.out.println("SUCCESS");
//            System.out.println(e.getMessage());
//        }        
//        try 
//        {
//            if (hotelbooking1.cancelRoomforASpecificRangeOfdates(1, "3/1/2022", "4/1/2022",customer1))//Room Id,startDate,endDate............
//            {
//                System.out.println("FAIL");
//            } 
//            else 
//            {
//                System.out.println("FAIL");
//            }
//        } 
//        catch(InvalidCustomer e)
//        {
//            System.out.println("SUCCESS");
//            System.out.println(e.getMessage());
//        }   
//        catch(InvalidRoom e) 
//        {
//            System.out.println("FAILED");
//            System.out.println(e.getMessage());
//        } 
//        catch(InvalidDateException e) 
//        {
//            System.out.println("FAILED");
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("-----------------------------------------------To calculate total bill of a room-----------------------------------");
//        hotelbooking1.bookingRoom("01/01/2022", "15/01/2022", room1,customer1);
//        hotelbooking1.bookingRoom("1/1/02022", "1/02/2022", room2,customer2);
//        try
//        {
//            if(hotelbooking1.calculateThetotalbillOfARoomFromParticularDate("1/1/2022","1/2/2022",2) == 22220)//When guest is 1......
//            {
//                System.out.println("SUCCESS");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//        catch(InvalidRoom e)
//        {
//            System.out.println("FAIL");
//            e.printStackTrace();
//        }
//        try
//        {
//            if(hotelbooking1.calculateThetotalbillOfARoomFromParticularDate("11/01/2022","14/01/2022",1) == 660)//when guest room is 0 in room1....
//            {
//                System.out.println("SUCCESS ");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//        catch(InvalidRoom e)
//        {
//            System.out.println("FAIL");
//        }
//        hotelbooking1.printBookingByRoomId(1);
//        try
//        {
//            if(hotelbooking1.calculateThetotalbillOfARoomFromParticularDate("11/1/2022","14/1/2022",3) == 760)//when guest room is 0 and dates are in the range .
//            {
//                System.out.println("FAILED");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//        catch(InvalidRoom e)
//        {
//            System.out.println("SUCCESS");
//            e.printStackTrace();
//        }
//        try
//        {
//            if(hotelbooking1.calculateThetotalbillOfARoomFromParticularDate("7/01/2022","10/01/2022",1) == 880)
//            {
//                System.out.println("SUCCESS");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//        catch(InvalidRoom e)
//        {
//            System.out.println("FAIL");
//            e.printStackTrace();
//        }
//        try
//        {
//            if(hotelbooking1.calculateThetotalbillOfARoomFromParticularDate("1/4/2022","1/5/2022",4) == 5115)//Tested this condition to be failed as it is out of range......
//            {
//                System.out.println("FAILED");
//            }
//            else
//            {
//                System.out.println("FAIL");
//            }
//        }
//        catch(InvalidRoom e)
//        {
//            System.out.println("SUCCESS");
//            e.printStackTrace();
//        }
    }	
}
