package GUI;

import StudyRoom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberRegistration extends JFrame {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();

	public MemberRegistration(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;

		setTitle("Study Room");
		setSize(WIDTH, HEIGHT);

		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(196, 233, 242));
		contentPane.setLayout(new GridLayout(3, 0));

		JLabel title = new JLabel("Member Registration", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.BLACK);
		contentPane.add(title);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.BLACK);
		inputPanel.setLayout(new GridLayout(3, 0));

		// name
		JPanel namePanel = new JPanel();
		namePanel.setBackground(new Color(196, 233, 242));
		namePanel.setLayout(new FlowLayout());

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setForeground(Color.BLACK);
		namePanel.add(nameLabel);

		JTextField nameText = new JTextField(20);
		namePanel.add(nameText);

		inputPanel.add(namePanel);

		// ID
		JPanel IDPanel = new JPanel();
		IDPanel.setBackground(new Color(196, 233, 242));
		IDPanel.setLayout(new FlowLayout());

		JLabel IDLabel = new JLabel("ID");
		IDLabel.setForeground(Color.BLACK);
		IDPanel.add(IDLabel);

		JTextField IDText = new JTextField(20);
		IDPanel.add(IDText);

		inputPanel.add(IDPanel);

		// PW
		JPanel PWPanel = new JPanel();
		PWPanel.setBackground(new Color(196, 233, 242));
		PWPanel.setLayout(new FlowLayout());

		JLabel PWLabel = new JLabel("PW");
		PWLabel.setForeground(Color.BLACK);
		PWPanel.add(PWLabel);

		JPasswordField PWText = new JPasswordField(20);
		PWPanel.add(PWText);

		inputPanel.add(PWPanel);
		
		contentPane.add(inputPanel);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBackground(Color.WHITE);
		registerButton.setFont(new Font("Arial", Font.PLAIN, 30));
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				int ID = Integer.parseInt(IDText.getText());
				int PW = Integer.parseInt(PWText.getText());
				
				if(studyRoom.registerMember(name, ID, PW) == 1) {
					setVisible(false);
					new MemberGUI(studyRoom);
				}
				else {
					setVisible(false);
					new MemberRegistration(studyRoom);
				}
			}
		});
		
		contentPane.add(registerButton);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
}
