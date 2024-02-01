
package com.nityaobject.roombooking;

public class Room {

	private int roomId;
	private double roomRate;
	private int numberOfguests;
	private static final int GUEST_LIMIT = 2;

	public Room(int roomId, double roomRate,int numberOfguests) throws GuestListException 
	{
		super();
		this.roomId = roomId;
		this.roomRate = roomRate;
		if(numberOfguests <= GUEST_LIMIT)
		{
			this.numberOfguests = numberOfguests;
		}
		else
		{
			throw new GuestListException("Guest limit exceeded for current room ");
		}
	}

	public int getNumberOfguests() {
		return numberOfguests;
	}

	public void setNumberOfguests(int numberOfguests) {
		this.numberOfguests = numberOfguests;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public double getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(double roomRate) {
		this.roomRate = roomRate;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfguests;
		result = prime * result + roomId;
		long temp;
		temp = Double.doubleToLongBits(roomRate);
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
		Room other = (Room) obj;
		if (numberOfguests != other.numberOfguests)
			return false;
		if (roomId != other.roomId)
			return false;
		if (Double.doubleToLongBits(roomRate) != Double.doubleToLongBits(other.roomRate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomRate=" + roomRate + ", numberOfguests=" + numberOfguests + "]";
	}

}

