package GUI;

import StudyRoom.*;
import Person.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberLogin extends JFrame {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();

	public MemberLogin(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;

		setTitle("Study Room");
		setSize(WIDTH, HEIGHT);

		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(196, 233, 242));
		contentPane.setLayout(new GridLayout(3, 0));

		// label "Member Login"
		JLabel title = new JLabel("Member Login", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.BLACK);
		contentPane.add(title);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setLayout(new GridLayout(2, 0));

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
		
		// login button
		JButton loginButton = new JButton("Login");
		loginButton.setBackground(Color.WHITE);
		loginButton.setFont(new Font("Arial", Font.PLAIN, 30));
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(IDText.getText());
				int PW = Integer.parseInt(PWText.getText());
				
				Person member = new Member();
				member = studyRoom.memberLogin(ID, PW);
				
				if(member != null) { // if member login succeed
					setVisible(false);
					new MemberChoice(studyRoom, member);
				}
				else { // if member login failed
					setVisible(false);
					new MemberLogin(studyRoom);
				}
			}
		});
		
		contentPane.add(loginButton);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
}
