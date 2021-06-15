package GUI;

import Room.*;
import Person.*;
import StudyRoom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.BorderLayout;

public class RoomState extends JFrame {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	private LineBorder lineBorder = new LineBorder(Color.WHITE, 1, false);
	private int roomNumber = 0;

	public RoomState(StudyRoomInterface studyRoom, Person person, String type, String root) {
		ArrayList<Room> room = new ArrayList<Room>();
		room = studyRoom.getRoomList();

		setSize(WIDTH, HEIGHT);
		setTitle("Study Room");

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		JPanel roomPanel = new JPanel();
		roomPanel.setBackground(new Color(213, 240, 247));
		roomPanel.setLayout(new GridLayout(3, 10));
		roomPanel.setBorder(new EmptyBorder(2, 2, 2, 2));

		int count = 1;

		for(Room eachRoom : room) {
			if(eachRoom.getUsed() == 0) {
				// print the room number
				JLabel tempLabel = new JLabel(Integer.toString(eachRoom.getRoomNumber()), JLabel.CENTER);
				tempLabel.setFont(new Font("Arial", Font.PLAIN, 15));
				tempLabel.setForeground(Color.BLACK);
				tempLabel.setBorder(lineBorder);
				roomPanel.add(tempLabel);
			}
			else {
				// if the room used, do not print the room number
				JLabel tempLabel = new JLabel(" ");
				tempLabel.setBorder(lineBorder);
				roomPanel.add(tempLabel);
			}
			count++;
		}
		contentPane.add(roomPanel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setLayout(new FlowLayout());

		// to get the room number from user
		JLabel roomNumberLabel = new JLabel("Room #");
		roomNumberLabel.setForeground(Color.BLACK);
		inputPanel.add(roomNumberLabel);

		JTextField roomNumberText = new JTextField(5);
		inputPanel.add(roomNumberText);

		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String number = roomNumberText.getText();
				roomNumber = Integer.parseInt(number);

				if(type.equals("Reservation")) {
					setVisible(false);
					studyRoom.reservationRoom(person, roomNumber);
					new StudyRoomGUI(studyRoom);
				}
				else if(type.equals("Room Information")){
					setVisible(false);
					studyRoom.printRoomInformation(roomNumber);

					switch(root) {
					case "Member":
						new MemberChoice(studyRoom, person);
						break;

					case "Guest":
						new GuestGUI(studyRoom, person);
						break;
					}
				}
				else {
					setVisible(false);
					if(studyRoom.selectRoom(person, roomNumber) == 0) {
						new RoomState(studyRoom, person, " ", "Guest");
					}
					else {
						new StudyRoomGUI(studyRoom);
					}
				}
			}
		});

		inputPanel.add(enterButton);
		contentPane.add(inputPanel, BorderLayout.SOUTH);

		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);

		setVisible(true);
	}
}