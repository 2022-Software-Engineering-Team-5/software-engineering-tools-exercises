package Room;

import Person.Person;
import javax.swing.JOptionPane;

/**
 * Room class is Super class.
 * 
 * @author Lee Hajeong
 * @version JDK 11.0.11
 * @see {@link Person}
 */
public class Room {
	private int roomNumber;
	private int capacity;
	private int usageState; // 0: not used, 1: reserved, 2: used
	private int assignedID = 0;
	private int usingPerson = 0;

	public Room()
	{
		roomNumber = 0;
		capacity = 0;
		usageState = 0;
		assignedID = 0;
		usingPerson = 0;
	}

	public Room(int roomNumber, int capacity, int usageState) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.usageState = usageState;
	}
	
	public Room(int roomNumber, int capacity, int usageState, int assignedID, int usingPerson)
	{
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.usageState = usageState;
		this.assignedID = assignedID;
		this.usingPerson = usingPerson;
	}
	
	public void setAssignedID (int ID)
	{
		this.assignedID = ID;
	}
	
	public void setUsedAssign()
	{
		this.usageState = 1;
	}
	
	public void setUsed(int usageState)
	{
		this.usageState = usageState;
	}
	
	public void setUsingPerson(int num)
	{
		this.usingPerson = num;
	}
	
	public void setRoom(int roomNumber, int capacity, int usageState)
	{
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.usageState = usageState;
	}

	public int getRoomNumber()
	{
		return roomNumber;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public int getUsed()
	{
		return usageState;
	}
	
	public int getAssignedID()
	{
		return assignedID;
	}
	
	public int getUsingPerson()
	{
		return usingPerson;
	}
	
	public void logoutRoom(int ID)
	{
		if(this.getUsingPerson() == ID) {
			System.out.println("User " + ID + " logout!");
			this.setUsingPerson(0);
			this.setUsed(0);
		}
		else if(this.getAssignedID() == ID) {
			System.out.println("User " + ID + " logout!");
			this.setAssignedID(0);
			this.setUsed(0);
		}
		else{
			System.out.println("User " + ID + " is not using this room!");
			System.out.println();
		}
	}
	
	public void logoutRoomGUI(int ID)
	{
		if(this.getUsingPerson() == ID) {
			JOptionPane.showMessageDialog(null, "User " + ID + " Logout!", "Logout Success", JOptionPane.DEFAULT_OPTION);
			this.setUsingPerson(0);
			this.setUsed(0);
		}
		else if(this.getAssignedID() == ID) {
			JOptionPane.showMessageDialog(null, "User " + ID + " Logout!", "Logout Success", JOptionPane.DEFAULT_OPTION);
			this.setAssignedID(0);
			this.setUsed(0);
		}
		else {
			JOptionPane.showMessageDialog(null, "User " + ID + " is not using the room!", "Logout Failed", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	public void assignRoom(Person user) {
		// default method -- can be empty
	}
	
	public void printRoom() {
		// default method -- can be empty
	}
	
	public void assignRoomGUI(Person user) {
		// default method -- can be empty
	}
	
	public void printRoomGUI() {
		// default method -- can be empty
	}
}