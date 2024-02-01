

package com.nityaobject.roombooking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.testng.annotations.Test;
public class RoomBooking 
{
    private CustomerRegister customerRecord;
    private Map<Customer, List<BookingDetails>> customerToListOfBookingDetailsMap;
    private Map<Date, List<Room>> dateToListOfRoomsMap;
    private Map<Room, List<Date>> roomToListOfDatesMap;
    private Map<Integer, Room> roomIdToRoomMap;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final double GUEST_ROOM_RATE = 450;
    private static final double TAX_RATE = 10;
    private static final double EXTRA_ROOM_RATE_ON_WEEKEND = 100;

    public RoomBooking() 
    {
        customerRecord = new CustomerRegister();
        customerToListOfBookingDetailsMap = new HashMap<>();
        dateToListOfRoomsMap =  new HashMap<>();
        roomToListOfDatesMap = new HashMap<>();
        roomIdToRoomMap = new HashMap<>();
    }

	public CustomerRegister getCustomerRecord() 
	{
        return customerRecord;
    }
    
    public Map<Customer, List<BookingDetails>> getCustomerToListOfBookingDetailsMap() 
    {
        return customerToListOfBookingDetailsMap;
    }

    public void setCustomerToListOfBookingDetailsMap(Map<Customer, List<BookingDetails>> customerToListOfBookingDetailsMap) 
    {
        this.customerToListOfBookingDetailsMap = customerToListOfBookingDetailsMap;
    }
    
    public Map<Integer, Room> getRoomIdToRoomMap() 
    {
        return roomIdToRoomMap;
    }

    public void setRoomIdToRoomMap(Map<Integer, Room> roomIdToRoomMap) 
    {
        this.roomIdToRoomMap = roomIdToRoomMap;
    }

    public Map<Date, List<Room>> getDateToListOfRoomsMap() 
    {
        return dateToListOfRoomsMap;
    }

    public void setDateToListOfRoomsMap(Map<Date, List<Room>> dateToListOfRoomsMap) 
    {
        this.dateToListOfRoomsMap = dateToListOfRoomsMap;
    }

    public Map<Room, List<Date>> getRoomToListOfDatesMap() 
    {
        return roomToListOfDatesMap;
    }

    public void setRoomToListOfDatesMap(Map<Room, List<Date>> roomToListOfDatesMap) 
    {
        this.roomToListOfDatesMap = roomToListOfDatesMap;
    }
    
    public BookingDetails bookingRoom(String start, String end, Room singleroom, Customer currentCustomer) throws InvalidDateException,AlreadyBookedDates, ParseException, InvalidRoom
    {
        Date date1 = SIMPLE_DATE_FORMAT.parse(start);
        Date date2 = SIMPLE_DATE_FORMAT.parse(end);
        System.out.println(date1);
        Calendar currentDate  = GregorianCalendar.getInstance();
        currentDate.setTime(date1);
        Calendar endDate  = GregorianCalendar.getInstance();
        endDate.setTime(date2);
        endDate.add(Calendar.DATE, 1);//Increments 1 day(date) of endDate to satisfy for loop condition (<=)........
        Calendar endDate2  = GregorianCalendar.getInstance();
        endDate2.setTime(date2);
        if(roomToListOfDatesMap.containsKey(singleroom))
        {
        	for (Date date = currentDate.getTime(); currentDate.before(endDate); currentDate.add(Calendar.DATE, 1),date = currentDate.getTime()) 
        	{         
                if(roomToListOfDatesMap.get(singleroom).contains(date)) 
                {
                    throw new AlreadyBookedDates("Already Booked");
                }           
        	}
        }
        currentDate.setTime(date1);
        endDate.setTime(date2);
        endDate.add(Calendar.DATE, 1);
        for (Date date = currentDate.getTime(); currentDate.before(endDate); currentDate.add(Calendar.DATE, 1), date = currentDate.getTime()) 
        {
            List<Room> roomList = dateToListOfRoomsMap.get(date);
            if(roomList == null) 
            {
                roomList = new ArrayList<>();
                dateToListOfRoomsMap.put(date, roomList);
            }
            roomList.add(singleroom);
            List<Date> dateList = roomToListOfDatesMap.get(singleroom);
            if(dateList == null) 
            {
                dateList = new ArrayList<>();
                roomToListOfDatesMap.put(singleroom,dateList);//this map is used to calculate the totalbillamount of a single room and whole room allotted in a month .....
            }
            dateList.add(date);
            roomIdToRoomMap.put(singleroom.getRoomId(), singleroom);//to cancel the booking of a room using this map(from room id as a key).....
        }
        int singleroomID = singleroom.getRoomId(); 
        double storeTotalBillAmount = calculateThetotalbillOfARoomFromParticularDate(start, end, singleroomID);
        BookingDetails currentBooking = new BookingDetails(currentCustomer.getCustomer_id(),singleroom.getRoomId(),date1,date2,true,storeTotalBillAmount,singleroom.getNumberOfguests());
        List<BookingDetails> bookingDetailsList = customerToListOfBookingDetailsMap.get(currentCustomer);
        if(bookingDetailsList == null) 
        {
            bookingDetailsList = new ArrayList<>();
            customerToListOfBookingDetailsMap.put(currentCustomer, bookingDetailsList);
            customerRecord.addCustomer(currentCustomer.getCustomer_id(), currentCustomer);
        }
        bookingDetailsList.add(currentBooking);
        return currentBooking;
    }

    public List<BookingDetails> findBookingByCustomerId(int customerIdToBeSearched) throws InvalidCustomer
    {
        Customer customer = customerRecord.getCustomerIdToCustomerMap().get(customerIdToBeSearched);
   //     Customer customer = customerIdToCustomerMap.get(customerIdToBeSearched);
        if(customerToListOfBookingDetailsMap.containsKey(customer))
        {
            return customerToListOfBookingDetailsMap.get(customer);
        }
        else
        {
            throw new InvalidCustomer(" Invalid Customer ");
        }
    }
    public List<Date> findBookingByRoomId(int roomId) throws InvalidRoom
    {
        Room singleroom = roomIdToRoomMap.get(roomId);
        if(roomToListOfDatesMap.containsKey(singleroom))
        {
            return roomToListOfDatesMap.get(singleroom);
        }
        else
        {
            throw new InvalidRoom(" Room not present ");
        }
    }

    public boolean findDates(Date start,Date end,Date startDateOfMap,Date endDateOfMap)
    {
       return(startDateOfMap.compareTo(start) == 0) && (endDateOfMap.compareTo(end) == 0);
    }
    public boolean cancelRoomforASpecificRangeOfdates(int roomIDtocancelRoom, String start, String end, Customer currentCustomer) throws InvalidRoom, InvalidDateException, ParseException, InvalidCustomer
    {
        boolean allowedTocancel= false;
        Date date1 = SIMPLE_DATE_FORMAT.parse(start);
        System.out.println("Date : " + date1);
        Date date2 = SIMPLE_DATE_FORMAT.parse(end);
        Calendar currentDate  = GregorianCalendar.getInstance();
        currentDate.setTime(date1);
        Calendar endDate  = GregorianCalendar.getInstance();
        endDate.setTime(date2);
        endDate.add(Calendar.DATE, 1);//Increments 1 day(date) of endDate to satisfy for loop condition (<=)........
        Room singleroom = roomIdToRoomMap.get(roomIDtocancelRoom);
//        System.out.println(customerToListOfRoomMap);
        if(singleroom != null && roomToListOfDatesMap.containsKey(singleroom))
        {            
            if(customerToListOfBookingDetailsMap.containsKey(currentCustomer))
            {
                boolean isWrongDatePresent = false;
                List<BookingDetails> listOfBookings = customerToListOfBookingDetailsMap.get(currentCustomer);
                for(int j=0;j<listOfBookings.size();j++)
                {
                    boolean result = findDates(date1,date2,listOfBookings.get(j).getStartDate(),listOfBookings.get(j).getEndDate());                   
                    if(result)
                    {
                        isWrongDatePresent = false;
                        break;
                    }
                    else
                    {
                        isWrongDatePresent = true;
                    }
                }
                if(isWrongDatePresent)
                {
                    throw new InvalidDateException(" Invalid Date ");	
                }
            }                  
            else
            {
                throw new InvalidCustomer(" Invalid Customer ");
            } 
        }
        else
        {
           throw new InvalidRoom(" Room not present ");
        }
        currentDate.setTime(date1);
        endDate.setTime(date2);
        endDate.add(Calendar.DATE, 1);   
        Room singleroom1 = roomIdToRoomMap.get(roomIDtocancelRoom);
        for (Date date = currentDate.getTime(); currentDate.before(endDate); currentDate.add(Calendar.DATE, 1), date = currentDate.getTime()) 
        {        	 
            List<Room> roomList = dateToListOfRoomsMap.get(date);
            roomList.remove(singleroom1);  	  
            roomToListOfDatesMap.get(singleroom1).remove(date);
            allowedTocancel=true;
        }
        if(customerToListOfBookingDetailsMap.containsKey(currentCustomer))
        {
            List<BookingDetails> listOfBookingDetails = customerToListOfBookingDetailsMap.get(currentCustomer);
            for(int i=0;i<listOfBookingDetails.size();i++)
            {
                if((listOfBookingDetails.get(i).getStartDate().compareTo(date1) == 0) && (listOfBookingDetails.get(i).getEndDate().compareTo(date2) == 0) && (listOfBookingDetails.get(i).getRoom_Id() == roomIDtocancelRoom)) 
                {
                    listOfBookingDetails.remove(i);
                    break;
                }                 
            } 
        }                                                                                                                            
        if(customerToListOfBookingDetailsMap.get(currentCustomer).isEmpty())
        {
            customerRecord.removeCustomer(currentCustomer.getCustomer_id());
            customerToListOfBookingDetailsMap.remove(currentCustomer);
        }
        dateToListOfRoomsMap.entrySet().removeIf(ent -> ent.getValue().isEmpty());//To remove whole mapping if respective Key's value is empty........
        return allowedTocancel;
    }

    public boolean cancelRoomforARangeOfdatesUsingObject(BookingDetails currentBookingDetails) throws ParseException, InvalidDateException, InvalidRoom, InvalidCustomer
    {
        Customer customer1 = customerRecord.getCustomerIdToCustomerMap().get(currentBookingDetails.getCustomerId());
        
        System.out.println(customer1);

        String theStartDate1 = SIMPLE_DATE_FORMAT.format(currentBookingDetails.getStartDate());  
        String theLastDate1 = SIMPLE_DATE_FORMAT.format(currentBookingDetails.getEndDate());  
        
        boolean bookingsCancelled = cancelRoomforASpecificRangeOfdates(currentBookingDetails.getRoom_Id(),theStartDate1,theLastDate1,customer1);           
        currentBookingDetails.setStatus(bookingsCancelled);
        return bookingsCancelled;
    }

    public double calculateExtraAmountforGuests(double numberOfExtraGuests,double numberOfdates)
    {
        double totalGuestRoomRate = ((GUEST_ROOM_RATE/100) * TAX_RATE) + GUEST_ROOM_RATE;
        double finalRateOfGuestRoom = (totalGuestRoomRate * numberOfExtraGuests);
        finalRateOfGuestRoom = finalRateOfGuestRoom * numberOfdates;
        return finalRateOfGuestRoom;
    }

    public double calculateThetotalbillOfARoomFromParticularDate(String start, String end, int singleroomID)throws InvalidRoom, ParseException, InvalidDateException
    {
        Room singleroom = roomIdToRoomMap.get(singleroomID);
        if(singleroom == null)
        {
            throw new InvalidRoom(" Room not present ");
        }
        Date date1 = SIMPLE_DATE_FORMAT.parse(start);
        Date date2 = SIMPLE_DATE_FORMAT.parse(end);
        Calendar currentDate  = GregorianCalendar.getInstance();
        currentDate.setTime(date1);
        Calendar endDate  = GregorianCalendar.getInstance();
        endDate.setTime(date2);
        endDate.add(Calendar.DATE, 1);//Increments 1 day(date) of endDate to satisfy for loop condition (<=)........
        double totalBillAmount=0;   
        double extraRoomRateOnWeekendTotal = 0;
        int numberOfdatesTobeCountedtoCalculateBill = 0;
        boolean isDatePresent = false;
        for (Date date = currentDate.getTime(); currentDate.before(endDate); currentDate.add(Calendar.DATE, 1), date = currentDate.getTime()) 
        { 		
            if(roomToListOfDatesMap.get(singleroom).contains(date))
            {
            	isDatePresent = true;
                numberOfdatesTobeCountedtoCalculateBill++;
            }
            if(currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            {
                extraRoomRateOnWeekendTotal += ((EXTRA_ROOM_RATE_ON_WEEKEND/100) * TAX_RATE) + EXTRA_ROOM_RATE_ON_WEEKEND;
            }
        }
        if(!isDatePresent)
        {
            throw new InvalidDateException(" Invalid Date ");	
        }
        if(numberOfdatesTobeCountedtoCalculateBill > 0)
        {
            double roomPrice = singleroom.getRoomRate();
            double totalRoomCharges = ((roomPrice/100) * TAX_RATE) + roomPrice;
            double totalOfRoomChargesAndGuestRoomCharges = numberOfdatesTobeCountedtoCalculateBill * totalRoomCharges;
            if(singleroom.getNumberOfguests()>0)
            {
                double numberOfExtraGuests = singleroom.getNumberOfguests();
                totalOfRoomChargesAndGuestRoomCharges += calculateExtraAmountforGuests(numberOfExtraGuests,numberOfdatesTobeCountedtoCalculateBill);
            }
            totalBillAmount += (extraRoomRateOnWeekendTotal + totalOfRoomChargesAndGuestRoomCharges);
        }
        System.out.println(" total bill amount " + totalBillAmount);
        return totalBillAmount;	
    }

}
