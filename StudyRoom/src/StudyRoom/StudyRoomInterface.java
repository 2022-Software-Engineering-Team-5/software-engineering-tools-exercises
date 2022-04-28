package StudyRoom;

import java.util.ArrayList;
import Person.*;
import Room.Room;

/**
 * StudyRoomInterface is a interface will be implemented in StudyRoom
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 * @see {@link StudyRoom}
 */
public interface StudyRoomInterface {
	public static final int NUMBER_OF_ROOM = 30;
	
	// associated opening the Study Room
	public void setOpen(boolean isOpen);
	public boolean getOpen();
	
	// for initialize
	public void initialSetting();
	public void initialManager();
	
	public void printRoomStudy();
	public void printRoomInformation();
	public ArrayList<Room> getRoomList();
	
	public ArrayList<Manager> getManagerList();
	public void registerMember();
	public Guest registerGuest();
	
	public Person memberLogin();
	public void memberCharge();
	public void updateMember();
	public void finallyUpdateInformation();
	
	public void reservationRoom(Person user);
	public void selectRoom(Person user);
	public void exitRoom(int type, int ID);
	
	// GUI
	public void printRoomInformation(int roomNumber);
	public void reservationRoom(Person user, int roomNumber);
	public int selectRoom(Person user, int roomNumber);
	public void exitRoom(String type, int ID);
	public Person memberLogin(int ID, int PW);
	public int memberCharge(int ID, int money);
	public int registerMember(String name, int ID, int PW);
	public Guest registerGuest(String name);
}