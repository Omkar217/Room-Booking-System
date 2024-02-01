
package com.nityaobject.roombooking;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingDetails 
{
   private int customerId; 
   private int room_Id; 
   private Date startDate;
   private Date endDate;
   private boolean status;
   private double totalAmountOfCustomer;
   private int extraGuestsOfCustomer;

   
  
  public BookingDetails() {
	
}

public BookingDetails(int customerId,int room_id, Date startDate, Date endDate,boolean status,double totalAmountOfCustomer,int extraGuestsOfCustomer)
  {
    this.customerId = customerId;
    this.room_Id = room_id;
    this.startDate = startDate;
    this.endDate  =  endDate;
    this.status = status; 
    this.totalAmountOfCustomer = totalAmountOfCustomer;
    this.extraGuestsOfCustomer = extraGuestsOfCustomer;
  }
   
      public int getRoom_Id() {
        return room_Id;
    }

    public void setRoom_Id(int room_Id) {
        this.room_Id = room_Id;
    }
   
   public Date getStartDate() {
        return startDate;
    }
   
    public Date getEndDate() {
        return endDate;
    }
    
    public double getTotalAmountOfCustomer() {
        return totalAmountOfCustomer;
    }

    public int getExtraGuestsOfCustomer() {
        return extraGuestsOfCustomer;
    }

    public void setExtraGuestsOfCustomer(int extraGuestsOfCustomer) {
        this.extraGuestsOfCustomer = extraGuestsOfCustomer;
    }
    
    public int getCustomerId(){
        return customerId;
    }
    
     public boolean isStatus() {
        return status;
    }
     
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void setCustomer(int customer_Id) {
        this.setCustomerId(customer_Id);
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return "BookingDetails{" + "customer_Id=" + customerId + ", room_Id=" + room_Id + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + isStatus() + ", totalAmountOfCustomer=" + getTotalAmountOfCustomer() + ", extraGuestsOfCustomer=" + extraGuestsOfCustomer + '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + extraGuestsOfCustomer;
		result = prime * result + room_Id;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(totalAmountOfCustomer);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingDetails other = (BookingDetails) obj;
		if (customerId != other.customerId)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (extraGuestsOfCustomer != other.extraGuestsOfCustomer)
			return false;
		if (room_Id != other.room_Id)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(totalAmountOfCustomer) != Double.doubleToLongBits(other.totalAmountOfCustomer))
			return false;
		return true;
	}
    
    
 }


  

