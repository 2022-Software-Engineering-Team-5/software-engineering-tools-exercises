package GUI;

import StudyRoom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI: Window for determining study room system status
 * Manager can decide whether to open or close the study room system
 * 
 * @author Lee Hajeong
 * @version JDK 11.0.11
 */
public class OpenOrClose extends JFrame implements ActionListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();
	
	public OpenOrClose(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;
		
		setTitle("Study Room");
		setSize(WIDTH, HEIGHT);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(new GridLayout(2, 0));

		// label "Manager(Administrator) Access"
		JLabel title = new JLabel("Manager(Administrator) Access", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		title.setForeground(Color.WHITE);
		contentPane.add(title);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		buttonPanel.setLayout(new FlowLayout());
		
		// open button
		JButton openButton = new JButton("Open");
		openButton.setBackground(Color.WHITE);
		openButton.setForeground(Color.BLACK);
		openButton.addActionListener(this);
		buttonPanel.add(openButton);

		// closed button
		JButton closedButton = new JButton("Closed");
		closedButton.setBackground(Color.WHITE);
		closedButton.setForeground(Color.BLACK);
		closedButton.addActionListener(this);
		buttonPanel.add(closedButton);
		
		contentPane.add(buttonPanel);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Open")) {
			studyRoom.setOpen(true); // set open
			setVisible(false);
			new StudyRoomGUI(studyRoom);
		}
		else {
			studyRoom.setOpen(false); // set closed
			setVisible(false);
			new StudyRoomGUI(studyRoom);
		}
	}
}