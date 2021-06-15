package GUI;

import StudyRoom.*;
import Person.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberChoice extends JFrame implements ActionListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();
	Person member = new Member();
	
	public MemberChoice(StudyRoomInterface studyRoom, Person member) {
		this.studyRoom = studyRoom;
		this.member = member;
		
		setSize(WIDTH, HEIGHT);
		setTitle("Study Room");

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new GridLayout(2, 0));
		
		// label "Member Choice"
		JLabel title = new JLabel("Member Choice", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.BLACK);
		contentPane.add(title);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(196, 233, 242));
		
		// enter button
		JButton enter = new JButton("Enter Now");
		enter.setBackground(Color.WHITE);
		enter.setForeground(Color.BLACK);
		enter.setFont(new Font("Arial", Font.PLAIN, 15));
		enter.addActionListener(this);
		buttonPanel.add(enter);

		// reservation button
		JButton reservation = new JButton("Reservation");
		reservation.setBackground(Color.WHITE);
		reservation.setForeground(Color.BLACK);
		reservation.setFont(new Font("Arial", Font.PLAIN, 15));
		reservation.addActionListener(this);
		buttonPanel.add(reservation);
		
		// room information button
		JButton roomInformation = new JButton("Room Information");
		roomInformation.setBackground(Color.WHITE);
		roomInformation.setForeground(Color.BLACK);
		roomInformation.setFont(new Font("Arial", Font.PLAIN, 15));
		roomInformation.addActionListener(this);
		buttonPanel.add(roomInformation);
		
		contentPane.add(buttonPanel);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Enter Now":
			setVisible(false);
			new RoomState(studyRoom, member, " ", "Member");
			break;
			
		case "Reservation":
			setVisible(false);
			new RoomState(studyRoom, member, e.getActionCommand(), "Member");
			break;
			
		case "Room Information":
			setVisible(false);
			new RoomState(studyRoom, member, e.getActionCommand(), "Member");
			break;
		}
	}
}