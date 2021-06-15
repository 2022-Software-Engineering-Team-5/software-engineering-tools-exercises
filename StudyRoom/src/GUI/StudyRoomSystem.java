package GUI;

import StudyRoom.*;

public class StudyRoomSystem {
	public static void main(String[] args) {
		StudyRoomInterface studyRoom = new StudyRoom();
		studyRoom.initialSetting();
		
		StudyRoomGUI gui = new StudyRoomGUI(studyRoom);
	}
}