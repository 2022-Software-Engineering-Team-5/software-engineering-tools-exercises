package Room;

import javax.swing.JOptionPane;

import Person.*;

public class MemberRoom extends Room {
	
	public MemberRoom()
	{
		super();
	}
	
	public MemberRoom(int roomNumber, int capacity, int usageState, int assignedID, int usingPerson)
	{
		super(roomNumber, capacity, usageState, assignedID, usingPerson);
	}
	
	@Override
	public void printRoom() {
		System.out.println("<Member Room>");
		System.out.println("Room Number: " + this.getRoomNumber());
		System.out.println("Capacity: " + this.getCapacity());
		
		if(this.getUsed() == 1)
			System.out.println("Room State: Assigned");
		else if(this.getUsed() == 2)
			System.out.println("Room State: Used");
		else
			System.out.println("Room State: Empty");
	}
	
	@Override
	public void assignRoom(Person user) {
		System.out.println();

		if(user instanceof Member) {
			Member member = (Member)user;
			
			if(this.getUsed() == 0)
			{
				System.out.println(member.getID() + " assign Member Room");
				System.out.println("And Room number is " + this.getRoomNumber());
				System.out.println();
				this.setAssignedID(member.getID());
			}
			else if (this.getUsed() == 1) {
				System.out.println("Room " + this.getRoomNumber() + " is already Assigned");
			}
			else {
				System.out.println("Room " + this.getRoomNumber() + " is already Used");
			}
			
			this.setUsedAssign();
		}
	}
	
	public void roomUsing(int ID) {
		if(this.getUsed() == 2) {
			System.out.println("Already used room");
		}
		else if(this.getUsed() == 1) {
			System.out.println("Already assigned room");
		}
		else {
			System.out.println("User " + ID + " can use Member room");
			this.setUsingPerson(ID);
			this.setUsed(2);
			System.out.println("You can use the Room Right Now");
		}
		System.out.println();
	}
	
	@Override
	public void printRoomGUI() {
		String text, usageState;

		text = "<Member Room>\n"
				+ "Room Number: " + this.getRoomNumber() + "\n"
				+ "Capacity: " + this.getCapacity() + "\n";

		if(this.getUsed() == 1) {
			usageState = "Room State: Assigned";
		}
		else if(this.getUsed() == 2) {
			usageState = "Room State: Used";
		}
		else {
			usageState = "Room State: Empty";
		}

		JOptionPane.showMessageDialog(null, text + usageState,
				"Room Information", JOptionPane.DEFAULT_OPTION);
	}
	
	@Override
	public void assignRoomGUI(Person user) {
		String text;

		if(user instanceof Member) {
			Member member = (Member)user;

			if(this.getUsed() == 0) {
				this.setAssignedID(member.getID());
				
				text = member.getID() + " assign Member Room\n"
						+ "And Room number is " + this.getRoomNumber() + "\n";
			}
			else if (this.getUsed() == 1) {
				text = "Room " + this.getRoomNumber() + " is already Assigned";
			}
			else {
				text = "Room " + this.getRoomNumber() + " is already Used";
			}
			
			this.setUsedAssign();

			JOptionPane.showMessageDialog(null, text, "Room Notification", JOptionPane.DEFAULT_OPTION);
		}
	}
	
	public void roomUsingGUI(int ID) {
		String text;

		if(this.getUsed() == 2) {
			text = "Already used room";
		}
		else if(this.getUsed() == 1) {
			text = "Already assigned room";
		}
		else {
			this.setUsingPerson(ID);
			this.setUsed(2);
			text = "User " + ID + " can use Member Room\n"
					+ "You can use the Room Right Now";
		}

		JOptionPane.showMessageDialog(null, text, "Room Notification", JOptionPane.DEFAULT_OPTION);
	}
}