package StudyRoom;

import Room.*;
import Person.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class StudyRoom implements StudyRoomInterface {
	private boolean isOpen;

	private static ArrayList<Room> room = new ArrayList<Room>();
	private static ArrayList<Manager> manager = new ArrayList<Manager>();
	public static ArrayList<Member> member = new ArrayList<Member>();
	public static ArrayList<Guest> guest = new ArrayList<Guest>();

	/**
	 * constructor
	 */
	public StudyRoom() {
		Scanner inputStream = null;

		try {
			inputStream = new Scanner(new File("studyRoomState.txt"));
			isOpen = Boolean.parseBoolean(inputStream.nextLine());
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(inputStream != null) {
				inputStream.close();
			}
		}
	}

	/**
	 * set open state method
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;

		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(new FileOutputStream("studyRoomState.txt"));
			outputStream.println(isOpen);
		}
		catch (FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * get open state method
	 */
	public boolean getOpen() {
		return isOpen;
	}

	/**
	 * get manager list method
	 */
	public ArrayList<Manager> getManagerList() {
		return manager;
	}

	/**
	 * get room list method
	 */
	public ArrayList<Room> getRoomList() {
		return room;
	}

	/**
	 * read information from a file
	 * and initialize before program starts
	 */
	public void initialSetting() {
		Scanner inputStream = null;
		initialManager();
		initialMember();

		try {
			inputStream = new Scanner(new File("information.txt"));
			String line = inputStream.nextLine();

			for(int i = 0; i < NUMBER_OF_ROOM; i++) {
				line = inputStream.nextLine();
				String[] arr = line.split(" ");
				String roomType = arr[0];
				int roomNumber = Integer.parseInt(arr[1]);
				int capacity = Integer.parseInt(arr[2]);
				int usageState = Integer.parseInt(arr[3]);
				boolean option = Boolean.parseBoolean(arr[4]);
				int assignedID = Integer.parseInt(arr[5]);
				int usingID = Integer.parseInt(arr[6]);

				// member room type
				if(roomType.equals("Member")) {
					Room member = new MemberRoom(roomNumber, capacity, usageState, assignedID, usingID);
					room.add(member);
				}
				// single room type
				else if(roomType.equals("Single")) {
					Room single = new SingleRoom(roomNumber, capacity, usageState, assignedID, usingID);
					room.add(single);
				}
				// multi room type
				else if(roomType.equals("Multi")) {
					int[] assignedIDList = new int[capacity];
					int[] usingIDList = new int[capacity];

					for(int j = 0; j < capacity; j++) {
						assignedIDList[j] = Integer.parseInt(arr[5 + j]);
						usingIDList[j] = Integer.parseInt(arr[5 + capacity + j]);
					}

					Room multi = new MultiRoom(roomNumber, capacity, usageState, assignedIDList, usingIDList, option);
					room.add(multi);
				}
				// open room type
				else if(roomType.equals("Open")) {
					int[] assignedIDList = new int[capacity];
					int[] usingIDList = new int[capacity];

					for(int j = 0; j < capacity; j++) {
						assignedIDList[j] = Integer.parseInt(arr[5 + j]);
						usingIDList[j] = Integer.parseInt(arr[5 + capacity + j]);
					}

					Room open = new OpenRoom(roomNumber, capacity, usageState, assignedIDList, usingIDList, option);
					room.add(open);
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(inputStream != null) {
				inputStream.close();
			}
		}
	}

	/**
	 * output the current room state
	 */
	public void printRoomStudy() {
		int count = 1;

		System.out.print("------------------------------------------------------------");
		System.out.println("---------------------------------------------------");

		for(Room eachRoom: room) {
			if(count % 10 == 1) {
				if(eachRoom.getUsed() == 0) System.out.printf("| %4s %2d |", "Room", eachRoom.getRoomNumber());
				else System.out.printf("| %4s %2s |", " ", " "); // the room currently in use is marked null
			}
			else {
				if(eachRoom.getUsed() == 0) System.out.printf("  %4s %2d |", "Room", eachRoom.getRoomNumber());
				else System.out.printf("  %4s %2s |", " ", " "); // the room currently in use is marked null
			}

			if(count != 1 && count % 10 == 0) {
				System.out.println();
			}
			count++;
		}

		System.out.print("------------------------------------------------------------");
		System.out.println("---------------------------------------------------");
	}

	/**
	 * UI: print the room information selected method
	 */
	public void printRoomInformation() {
		printRoomStudy();

		System.out.print("Select: Room #");
		Scanner keyboard = new Scanner(System.in);
		int roomNumber = keyboard.nextInt();
		System.out.println();

		for(Room selectedRoom : room) {
			if(selectedRoom.getRoomNumber() == roomNumber) {
				selectedRoom.printRoom();
			}
		}
	}

	/**
	 * read manager information from a file
	 */
	public void initialManager() {
		Scanner inputStream = null;

		try {
			inputStream = new Scanner(new File("managerInformation.txt"));
			String line = inputStream.nextLine();

			while(inputStream.hasNextLine() != false) {
				line = inputStream.nextLine();
				String[] arr = line.split(" ");

				int managerID = Integer.parseInt(arr[0]);
				String managerName = arr[1];
				int managerKey = Integer.parseInt(arr[2]);

				Manager temp = new Manager(managerName, managerID, managerKey);
				manager.add(temp);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(inputStream != null) {
				inputStream.close();
			}
		}
	}

	/**
	 * read member information from a file
	 */
	public void initialMember() {
		Scanner inputStream = null;

		try {
			inputStream = new Scanner(new File("memberInformation.txt"));
			String line = null;

			if(inputStream.hasNextLine()) {
				line= inputStream.nextLine();
			}

			while(inputStream.hasNextLine() != false) {
				line = inputStream.nextLine();
				String[] arr = line.split(" ");

				String memberName = arr[0];
				int memberID = Integer.parseInt(arr[1]);
				int memberPW = Integer.parseInt(arr[2]);
				int memberWallet = Integer.parseInt(arr[3]);

				Member temp = new Member(memberName, memberID, memberPW, memberWallet);
				member.add(temp);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(inputStream != null) {
				inputStream.close();
			}
		}
	}

	/**
	 * update information in a file
	 */
	public void updateInformation() {
		PrintWriter outputStream = null;
		int count = 1;

		try {
			outputStream = new PrintWriter(new FileOutputStream("information.txt"));
			outputStream.println("[Room_Type] [Room_Number] [Capacity] [isUsed] [Multi Room: isBoard or Open Room: isMusic] [assignedID] [usingPersonID]");

			for(Room tempRoom : room) {
				if(count >= 1 && count <= 15) {
					outputStream.println("Member " + count + " 1 " + tempRoom.getUsed() + " false " + tempRoom.getAssignedID() + " " + tempRoom.getUsingPerson());
				}
				else if(count >= 16 && count <= 25) {
					outputStream.println("Single " + count + " 1 " + tempRoom.getUsed() + " false " + tempRoom.getAssignedID() + " " + tempRoom.getUsingPerson());
				}
				else if(count >= 26 && count <= 29) {
					MultiRoom multiRoom = (MultiRoom) tempRoom;

					int[] assignedIDList = multiRoom.getAssignedIDList();
					int[] usingIDList = multiRoom.getUsingPersonList();

					outputStream.print("Multi " + count + " 2 " + multiRoom.getUsed() + " true ");

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(assignedIDList[i] + " ");
					}

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(usingIDList[i] + " ");
					}
					outputStream.print("\n");
				}
				else {
					OpenRoom openRoom = (OpenRoom) tempRoom;

					int[] assignedIDList = openRoom.getAssignedIDList();
					int[] usingIDList = openRoom.getUsingPersonList();

					outputStream.print("Open " + count + " 5 " + openRoom.getUsed() + " true ");

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(assignedIDList[i] + " ");
					}

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(usingIDList[i] + " ");
					}
					outputStream.print("\n");

				}
				count++;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * finally update information in a file
	 */
	public void finallyUpdateInformation() {
		PrintWriter outputStream = null;
		int count = 1;

		try {
			outputStream = new PrintWriter(new FileOutputStream("information.txt"));
			outputStream.println("[Room_Type] [Room_Number] [Capacity] [isUsed] [Multi Room: isBoard or Open Room: isMusic] [assignedID] [usingPersonID]");

			for(Room tempRoom : room) {
				if(count >= 1 && count <= 15) {
					if(tempRoom.getUsingPerson() > 1000000) {
						outputStream.println("Member " + count + " 1 " + 0 + " false " + tempRoom.getAssignedID() + " " + 0);
					}
					else {
						outputStream.println("Member " + count + " 1 " + tempRoom.getUsed() + " false " + tempRoom.getAssignedID() + " " + tempRoom.getUsingPerson());
					}
				}
				else if(count >= 16 && count <= 25) {
					if(tempRoom.getUsingPerson() > 1000000) {
						outputStream.println("Single " + count + " 1 " + 0 + " false " + tempRoom.getAssignedID() + " " + 0);
					}
					else {
						outputStream.println("Single " + count + " 1 " + tempRoom.getUsed() + " false " + tempRoom.getAssignedID() + " " + tempRoom.getUsingPerson());
					}
				}
				else if(count >= 26 && count <= 29) {
					MultiRoom multiRoom = (MultiRoom) tempRoom;

					int[] assignedIDList = multiRoom.getAssignedIDList();
					int[] usingIDList = multiRoom.getUsingPersonList();

					for(int i = 0; i < usingIDList.length; i++) {
						if(usingIDList[i] > 1000000) {
							usingIDList[i] = 0;
							multiRoom.setUsed(0);
						}
					}

					outputStream.print("Multi " + count + " 2 " + multiRoom.getUsed() + " true ");

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(assignedIDList[i] + " ");
					}

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(usingIDList[i] + " ");
					}
					outputStream.print("\n");
				}
				else {
					OpenRoom openRoom = (OpenRoom) tempRoom;

					int[] assignedIDList = openRoom.getAssignedIDList();
					int[] usingIDList = openRoom.getUsingPersonList();

					for(int i = 0; i < usingIDList.length; i++) {
						if(usingIDList[i] > 1000000) {
							usingIDList[i] = 0;
							openRoom.setUsed(0);
						}
					}

					outputStream.print("Open " + count + " 5 " + openRoom.getUsed() + " true ");

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(assignedIDList[i] + " ");
					}

					for(int i = 0; i < tempRoom.getCapacity(); i++) {
						outputStream.print(usingIDList[i] + " ");
					}
					outputStream.print("\n");

				}
				count++;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * update member information in a file
	 */
	public void updateMember() {
		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(new FileOutputStream("memberInformation.txt"));
			outputStream.println("[Member Name] [Member ID] [Member PW] [Member Money]");

			for(Member member: member) {
				outputStream.println(member.getName() + " " + member.getID() + " " + member.getPW() + " " + member.getMoney());
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem with " + e);
			System.exit(0);
		}
		finally {
			if(outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * UI: member login method
	 */
	public Person memberLogin() {
		Person memberTemp = new Member();

		memberTemp = Member.login(member);
		return memberTemp;
	}

	/**
	 * UI: member charge method
	 */
	public void memberCharge() {
		Member.chargeWallet(member);
	}

	/**
	 * UI: member registration method
	 */
	public void registerMember() {
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Input numbers of members to create: ");
		int memberCount = keyboard.nextInt();
		System.out.println();

		for(int i = 0; i < memberCount; i++) {
			Member newMember;
			newMember = Member.newUser(member);
			member.add(newMember);
		}
	}

	/**
	 * UI: guest registration method
	 */
	public Guest registerGuest() {
		Scanner keyboard = new Scanner(System.in);
		Guest newGuest = null;

		System.out.print("Input numbers of guest to create: ");
		int guestCount = keyboard.nextInt();
		System.out.println();

		for(int i = 0; i < guestCount; i++) {
			newGuest = Guest.newGuest();

			guest.add(newGuest);
		}

		return newGuest;
	}

	/**
	 * UI: to check if the user is using another room or not
	 * @param user
	 * @return if the user is using another room, then true
	 */
	public boolean checkUsing(Person user) {
		for(Room tempRoom : room) {

			if(tempRoom instanceof MultiRoom) {
				MultiRoom multiRoom = (MultiRoom) tempRoom;

				int[] assignedIDList = multiRoom.getAssignedIDList();
				int[] usingIDList = multiRoom.getUsingPersonList();

				for(int i = 0; i < tempRoom.getCapacity(); i++) {
					if(assignedIDList[i] == user.getID()) {
						System.out.println("Already using other room");
						return false;
					}

					if(usingIDList[i] == user.getID()) {
						System.out.println("Already using other room");
						return false;
					}
				}
			}
			else if(tempRoom instanceof OpenRoom) {
				OpenRoom openRoom = (OpenRoom) tempRoom;

				int[] assignedIDList = openRoom.getAssignedIDList();
				int[] usingIDList = openRoom.getUsingPersonList();

				for(int i = 0; i < tempRoom.getCapacity(); i++) {
					if(assignedIDList[i] == user.getID()) {
						System.out.println("Already using other room");
						return false;
					}

					if(usingIDList[i] == user.getID()) {
						System.out.println("Already using other room");
						return false;
					}
				}
			}
			else {
				if(tempRoom.getAssignedID() == user.getID()) {
					System.out.println("Already using other room");
					return false;
				}
				else if(tempRoom.getUsingPerson() == user.getID()) {
					System.out.println("Already using other room");
					return false;
				}
			}
		}
		return true;
	}


	/**
	 * UI: room reservation method
	 */
	public void reservationRoom(Person user) {
		int roomNumber;
		Scanner keyboard = new Scanner(System.in);

		if(!checkUsing(user)) {
			return;
		}

		printRoomStudy();

		System.out.print("Reservation: Room #");
		roomNumber = keyboard.nextInt();
		System.out.println();

		if(!(user instanceof Member)) {
			System.out.println("Not Accessible");
			System.exit(0);
		}

		for(Room eachRoom: room) {
			if(eachRoom.getRoomNumber() == roomNumber) {
				eachRoom.assignRoom(user); // polymorphism implementation
			}
		}
		updateInformation();
	}

	/**
	 * UI: room selection (i.e. using immediately) method
	 */
	public void selectRoom(Person user) {
		if(!checkUsing(user)) {
			return;
		}

		printRoomStudy();

		int roomNumber;
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Select: Room #");
		roomNumber = keyboard.nextInt();
		System.out.println();

		for(Room eachRoom: room) {
			if(eachRoom.getRoomNumber() == roomNumber) {
				if(eachRoom instanceof MemberRoom) {

					if(user instanceof Member) {
						MemberRoom memberRoom = (MemberRoom) eachRoom;
						memberRoom.roomUsing(user.getID());
					}
					else {
						System.out.println("You cannot use Member Room");
						selectRoom(user);
					}
				}
				if(eachRoom instanceof SingleRoom) {
					SingleRoom singleRoom = (SingleRoom) eachRoom;
					singleRoom.roomUsing(user.getID());
				}
				if(eachRoom instanceof MultiRoom) {
					MultiRoom multiRoom = (MultiRoom) eachRoom;
					multiRoom.roomUsing(user.getID());
				}
				if(eachRoom instanceof OpenRoom) {
					OpenRoom openRoom = (OpenRoom) eachRoom;
					openRoom.roomUsing(user.getID());
				}
			}
		}
		updateInformation();
	}

	/**
	 * UI: logout method
	 */
	public void exitRoom(int type, int ID) {
		int stop = 0;

		if(type == 1) {
			for(Member tempMember : member) {
				if(ID == tempMember.getID()) {
					for(Room tempRoom : room) {

						if(tempRoom instanceof MultiRoom) {
							MultiRoom multiRoom = (MultiRoom) tempRoom;

							int[] assignedIDList = multiRoom.getAssignedIDList();
							int[] usingIDList = multiRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == assignedIDList[i] || ID == usingIDList[i]) {
									tempRoom.logoutRoom(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else if(tempRoom instanceof OpenRoom) {
							OpenRoom openRoom = (OpenRoom) tempRoom;

							int[] assignedIDList = openRoom.getAssignedIDList();
							int[] usingIDList = openRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == assignedIDList[i] || ID == usingIDList[i]) {
									tempRoom.logoutRoom(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else {
							if(ID == tempRoom.getUsingPerson() || ID == tempRoom.getAssignedID()) {
								tempRoom.logoutRoom(ID);
								stop = 1;
								break;
							}
						}
					}
				}
				if(stop == 1) {
					break;
				}
			}
		}
		else {
			for(Guest tempGuest : guest) {
				if(ID == tempGuest.getID()) {
					for(Room tempRoom : room) {

						if(tempRoom instanceof MultiRoom) {
							MultiRoom multiRoom = (MultiRoom) tempRoom;
							int[] usingIDList = multiRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == usingIDList[i]) {
									tempRoom.logoutRoom(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else if(tempRoom instanceof OpenRoom) {
							OpenRoom openRoom = (OpenRoom) tempRoom;
							int[] usingIDList = openRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == usingIDList[i]) {
									tempRoom.logoutRoom(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else {
							if(ID == tempRoom.getUsingPerson()) {
								tempRoom.logoutRoom(ID);
								stop = 1;
								break;
							}
						}
					}
				}
				if(stop == 1) {
					break;
				}
			}
		}
		updateInformation();
	}


	/**
	 * GUI: print the room information selected method
	 */
	public void printRoomInformation(int roomNumber) {
		for(Room selectedRoom : room) {
			if(selectedRoom.getRoomNumber() == roomNumber) {
				selectedRoom.printRoomGUI();
			}
		}
	}

	/**
	 * GUI: member login method
	 */
	public Person memberLogin(int ID, int PW) {
		Person memberTemp = new Member();

		memberTemp = Member.login(member, ID, PW);
		return memberTemp;
	}

	/**
	 * GUI: member charge method
	 */
	public int memberCharge(int ID, int money) {
		if(Member.chargeWallet(member, ID, money) == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}

	/**
	 * GUI: member registration method
	 */
	public int registerMember(String name, int ID, int PW) {
		Member newMember;
		newMember = Member.newUser(member, name, ID, PW);

		if(newMember != null) {
			member.add(newMember);
			return 1;
		}
		else {
			return 0;
		}
	}

	/**
	 * GUI: guest registration method
	 */
	public Guest registerGuest(String name) {
		Guest newGuest = null;
		newGuest = Guest.newGuest(name);
		guest.add(newGuest);

		return newGuest;
	}

	/**
	 * UI: to check if the user is using another room or not
	 * @param user
	 * @return if the user is using another room, then true
	 */
	public boolean checkUsingGUI(Person user) {
		for(Room tempRoom : room) {

			if(tempRoom instanceof MultiRoom) {
				MultiRoom multiRoom = (MultiRoom) tempRoom;

				int[] assignedIDList = multiRoom.getAssignedIDList();
				int[] usingIDList = multiRoom.getUsingPersonList();

				for(int i = 0; i < tempRoom.getCapacity(); i++) {
					if(assignedIDList[i] == user.getID()) {
						JOptionPane.showMessageDialog(null, "Already using other room", "Notification", JOptionPane.DEFAULT_OPTION);
						return false;
					}

					if(usingIDList[i] == user.getID()) {
						JOptionPane.showMessageDialog(null, "Already using other room", "Notification", JOptionPane.DEFAULT_OPTION);
						return false;
					}
				}
			}
			else if(tempRoom instanceof OpenRoom) {
				OpenRoom openRoom = (OpenRoom) tempRoom;

				int[] assignedIDList = openRoom.getAssignedIDList();
				int[] usingIDList = openRoom.getUsingPersonList();

				for(int i = 0; i < tempRoom.getCapacity(); i++) {
					if(assignedIDList[i] == user.getID()) {
						JOptionPane.showMessageDialog(null, "Already using other room", "Notification", JOptionPane.DEFAULT_OPTION);
						return false;
					}

					if(usingIDList[i] == user.getID()) {
						JOptionPane.showMessageDialog(null, "Already using other room", "Notification", JOptionPane.DEFAULT_OPTION);
						return false;
					}
				}
			}
			else {
				if(tempRoom.getAssignedID() == user.getID()) {
					JOptionPane.showMessageDialog(null, "Already using other room", "Notification", JOptionPane.DEFAULT_OPTION);
					return false;
				}
				else if(tempRoom.getUsingPerson() == user.getID()) {
					JOptionPane.showMessageDialog(null, "Already using other room", "Notification", JOptionPane.DEFAULT_OPTION);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * GUI: room reservation method
	 */
	public void reservationRoom(Person user, int roomNumber) {
		if(!checkUsingGUI(user)) {
			return;
		}

		if(!(user instanceof Member)) {
			JOptionPane.showMessageDialog(null, "Not Accessible", "Error", JOptionPane.DEFAULT_OPTION);
			System.exit(0);
		}

		for(Room eachRoom: room) {
			if(eachRoom.getRoomNumber() == roomNumber) {
				eachRoom.assignRoomGUI(user); // polymorphism implementation
			}
		}
		updateInformation();
	}

	/**
	 * GUI: room selection (i.e. using immediately) method
	 */
	public int selectRoom(Person user, int roomNumber) {
		if(!checkUsingGUI(user)) {
			return 1;
		}

		for(Room eachRoom: room) {
			if(eachRoom.getRoomNumber() == roomNumber) {
				if(eachRoom instanceof MemberRoom) {
					if(user instanceof Member) {
						MemberRoom memberRoom = (MemberRoom) eachRoom;
						memberRoom.roomUsingGUI(user.getID());
					}
					else {
						JOptionPane.showMessageDialog(null, "You cannot use Member Room", "Error", JOptionPane.DEFAULT_OPTION);
						return 0;
					}
				}
				if(eachRoom instanceof SingleRoom) {
					SingleRoom singleRoom = (SingleRoom) eachRoom;
					singleRoom.roomUsingGUI(user.getID());
				}
				if(eachRoom instanceof MultiRoom) {
					MultiRoom multiRoom = (MultiRoom) eachRoom;
					multiRoom.roomUsingGUI(user.getID());
				}
				if(eachRoom instanceof OpenRoom) {
					OpenRoom openRoom = (OpenRoom) eachRoom;
					openRoom.roomUsingGUI(user.getID());
				}
			}
		}
		updateInformation();
		return 1;
	}

	/**
	 * GUI: logout method
	 */
	public void exitRoom(String type, int ID) {
		int stop = 0;

		if(type.equals("Member")) {
			for(Member tempMember : member) {
				if(ID == tempMember.getID()) {
					for(Room tempRoom : room) {

						if(tempRoom instanceof MultiRoom) {
							MultiRoom multiRoom = (MultiRoom) tempRoom;

							int[] assignedIDList = multiRoom.getAssignedIDList();
							int[] usingIDList = multiRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == assignedIDList[i] || ID == usingIDList[i]) {
									tempRoom.logoutRoomGUI(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else if(tempRoom instanceof OpenRoom) {
							OpenRoom openRoom = (OpenRoom) tempRoom;

							int[] assignedIDList = openRoom.getAssignedIDList();
							int[] usingIDList = openRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == assignedIDList[i] || ID == usingIDList[i]) {
									tempRoom.logoutRoomGUI(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else {
							if(ID == tempRoom.getUsingPerson() || ID == tempRoom.getAssignedID()) {
								tempRoom.logoutRoomGUI(ID);
								stop = 1;
								break;
							}
						}
					}
				}
				if(stop == 1) {
					break;
				}
			}
		}
		else {
			for(Guest tempGuest : guest) {
				if(ID == tempGuest.getID()) {
					for(Room tempRoom : room) {

						if(tempRoom instanceof MultiRoom) {
							MultiRoom multiRoom = (MultiRoom) tempRoom;
							int[] usingIDList = multiRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == usingIDList[i]) {
									tempRoom.logoutRoomGUI(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else if(tempRoom instanceof OpenRoom) {
							OpenRoom openRoom = (OpenRoom) tempRoom;
							int[] usingIDList = openRoom.getUsingPersonList();

							for(int i = 0; i < tempRoom.getCapacity(); i++) {
								if(ID == usingIDList[i]) {
									tempRoom.logoutRoomGUI(ID);
									stop = 1;
									break;
								}
							}

							if(stop == 1) break;
						}
						else {
							if(ID == tempRoom.getUsingPerson()) {
								tempRoom.logoutRoomGUI(ID);
								stop = 1;
								break;
							}
						}
					}
				}
				
				if(stop == 1) {
					break;
				}
			}
		}
		updateInformation();
	}
}