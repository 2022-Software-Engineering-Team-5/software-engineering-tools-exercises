package GUI;

import StudyRoom.*;
import Person.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuestLogin extends JFrame {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();

	public GuestLogin(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;

		setTitle("Study Room");
		setSize(WIDTH, HEIGHT);

		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(196, 233, 242));
		contentPane.setLayout(new GridLayout(2, 0));

		JLabel title = new JLabel("Guest", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.BLACK);
		contentPane.add(title);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(new Color(196, 233, 242));
		inputPanel.setLayout(new FlowLayout());
		
		JLabel nameLabel = new JLabel("Name", JLabel.CENTER);
		nameLabel.setForeground(Color.BLACK);
		inputPanel.add(nameLabel);
		
		JTextField nameText = new JTextField(20);
		inputPanel.add(nameText);
		
		JButton enterButton = new JButton("Enter");
		enterButton.setBackground(Color.WHITE);
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				Person guest = studyRoom.registerGuest(name);
				
				setVisible(false);
				new GuestGUI(studyRoom, guest);
			}
		});
		
		inputPanel.add(enterButton);
		contentPane.add(inputPanel);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
}
