package GUI;

import StudyRoom.*;
import Person.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuestGUI extends JFrame implements ActionListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();
	Person guest = new Guest();
	
	public GuestGUI(StudyRoomInterface studyRoom, Person guest) {
		this.studyRoom = studyRoom;
		this.guest = guest;
		
		setSize(WIDTH, HEIGHT);
		setTitle("Study Room");

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new GridLayout(2, 0));
		
		JLabel title = new JLabel("Guest Choice", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.BLACK);
		contentPane.add(title);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(196, 233, 242));
		
		// select room
		JButton selectRoom = new JButton("Select Room");
		selectRoom.setBackground(Color.WHITE);
		selectRoom.setForeground(Color.BLACK);
		selectRoom.addActionListener(this);
		buttonPanel.add(selectRoom);
		
		// room information
		JButton information = new JButton("Room Information");
		information.setBackground(Color.WHITE);
		information.setForeground(Color.BLACK);
		information.addActionListener(this);
		buttonPanel.add(information);
		
		contentPane.add(buttonPanel);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Select Room")) {
			setVisible(false);
			new RoomState(studyRoom, guest, " ", "Guest");
		}
		else {
			setVisible(false);
			new RoomState(studyRoom, guest, e.getActionCommand(), "Guest");
			System.out.println();
		}
	}
}
