package GUI;

import StudyRoom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * WindowDestroyer class for operation when the window is closed.
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 */
public class WindowDestroyer extends WindowAdapter {
	StudyRoomInterface studyRoom = new StudyRoom();
	
	public WindowDestroyer(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		studyRoom.finallyUpdateInformation(); // for saving the information to a file finally
		super.windowClosing(e);
	}
}
