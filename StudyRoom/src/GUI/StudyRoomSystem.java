package GUI;

import StudyRoom.*;

/**
 * StudyRoomSystem class for executing the system (GUI)
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 */
public class StudyRoomSystem {
	public static void main(String[] args) {
		StudyRoomInterface studyRoom = new StudyRoom();
		studyRoom.initialSetting();
		
		StudyRoomGUI gui = new StudyRoomGUI(studyRoom);
	}
}