package StudyRoom;

import java.util.Scanner;
import Person.*;

/**
 * StudyRoomDemo class for executing the system (console-based UI)
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 * @see {@link StudyRoom}
 */
public class StudyRoomDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// using Interface
		StudyRoomInterface studyRoom = new StudyRoom();

		studyRoom.initialSetting();


		int choice = -1;
		do {
			Scanner integerKeyboard = new Scanner(System.in);

			// when closed
			if(!studyRoom.getOpen()) {
				System.out.println("\n************************************************************");
				System.out.printf("%18s [Study Room Closed]\n", " ");
				System.out.println("************************************************************\n");

				// initialize 'choice' to 0 so only managers can access
				choice = 0;
			}
			// when open
			else {
				System.out.println("\n************************************************************");
				System.out.printf("%18s [Study Room Open]\n", " ");
				System.out.print(" 0. Manager | 1. Member | 2. Guest | 3. Logout | 4. Quit: ");
				choice = integerKeyboard.nextInt();
				System.out.println("************************************************************\n");
			}


			switch(choice) {
			
			// Manager
			case 0:
				if(Manager.login(studyRoom.getManagerList()) != null) {
					System.out.print("[1] Open [2] Close: ");
					int setOpen = integerKeyboard.nextInt();

					if(setOpen == 1) { // Open
						studyRoom.setOpen(true);
					}
					else if(setOpen == 2) { // Closed
						studyRoom.setOpen(false);
					}
					else { // when an invalid number is entered, then exit manager mode
						System.out.println("You entered the wrong number!");
						choice = 0;
						break;
					}
				}
				else { // if not a registered manager, then exit manager mode
					System.out.println("Failure!");
					break;
				}
				break;


			// Member
			case 1:
				Person member = new Member();

				do {
					System.out.println("--------------------------------------------------------");
					System.out.print("1. Member | 2. Member Charge | 3. Member Registration: ");
					choice = integerKeyboard.nextInt();
					System.out.println("--------------------------------------------------------");
					
					if(choice == 1) {
						member = studyRoom.memberLogin();
					}
					else if (choice == 2) {
						studyRoom.memberCharge();
					}
					else if (choice == 3) {
						studyRoom.registerMember();
					}
				} while(choice != 1);

				
				do {
					System.out.println("-------------------------------------------------------");
					System.out.print("1. Enter Now | 2. Reservation | 3. Room Information: ");
					choice = integerKeyboard.nextInt();
					// System.out.println("---------------------------------");

					if(choice == 1) {
						// select the Room
						studyRoom.selectRoom(member);
					}
					else if(choice == 2){
						// reservation
						studyRoom.reservationRoom(member);
						System.out.println("Reservation Complete.");
					}
					else if(choice == 3) {
						// print the room information
						studyRoom.printRoomInformation();
						System.out.println();
					}
				} while(choice != 1 && choice != 2);

				System.out.println("Thank you for using our service :)");
				studyRoom.updateMember();
				break;


			// Guest
			case 2:
				Person guest = studyRoom.registerGuest();

				do {
					System.out.println("-----------------------------------------------------");
					System.out.print("1. Select Room | 2. Room Information: ");
					choice = integerKeyboard.nextInt();
					// System.out.println("---------------------------------");

					if(choice == 1) {
						// select the Room
						studyRoom.selectRoom(guest);
					}
					else if(choice == 2) {
						// print the room information
						studyRoom.printRoomInformation();
						System.out.println();
					}
				} while(choice != 1);

				System.out.println("Thank you for using our service :)");
				break;


			// Logout
			case 3:
				System.out.println("--------------------------------------");
				System.out.print("1. Member | 2. Guest: ");
				choice = integerKeyboard.nextInt();
				System.out.println("--------------------------------------");

				System.out.print("Enter your ID: ");
				int ID = integerKeyboard.nextInt();

				if(choice == 1) {
					studyRoom.exitRoom(choice, ID);
				}
				else if(choice == 2) {
					studyRoom.exitRoom(choice, ID);
				}
				else {
					System.out.println("Wrong Choice!");
					System.exit(0);
				}
				break;


			// Exit
			case 4:
				studyRoom.finallyUpdateInformation();
				System.out.println("                            EXIT                            ");
				System.exit(0);
				break;


			default:
				System.out.println("Wrong Choice!");
				System.out.println("Again!");
				break;
			}
		} while(true);
	}
}
