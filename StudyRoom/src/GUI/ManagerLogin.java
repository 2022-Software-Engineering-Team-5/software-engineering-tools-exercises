package GUI;

import StudyRoom.*;
import Person.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerLogin extends JFrame {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();

	public ManagerLogin(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;
		
		setTitle("Study Room");
		setSize(WIDTH, HEIGHT);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(new GridLayout(3, 0));

		// label "Manager Login"
		JLabel title = new JLabel("Manager Login", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.WHITE);
		contentPane.add(title, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.BLACK);
		inputPanel.setLayout(new GridLayout(2, 0));

		// ID
		JPanel IDPanel = new JPanel();
		IDPanel.setBackground(Color.BLACK);
		IDPanel.setLayout(new FlowLayout());

		JLabel IDLabel = new JLabel("ID", JLabel.CENTER);
		IDLabel.setForeground(Color.WHITE);
		IDPanel.add(IDLabel);

		JTextField IDText = new JTextField(10);
		IDPanel.add(IDText);

		inputPanel.add(IDPanel);

		// key
		JPanel keyPanel = new JPanel();
		keyPanel.setBackground(Color.BLACK);
		keyPanel.setLayout(new FlowLayout());

		JLabel keyLabel = new JLabel("Key", JLabel.CENTER);
		keyLabel.setForeground(Color.WHITE);
		keyPanel.add(keyLabel);

		JPasswordField keyText = new JPasswordField(10);
		keyPanel.add(keyText);

		inputPanel.add(keyPanel);
		contentPane.add(inputPanel);

		JButton loginButton = new JButton("Login");
		loginButton.setBackground(Color.WHITE);
		loginButton.setFont(new Font("Arial", Font.PLAIN, 30));
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(IDText.getText());
				int key = Integer.parseInt(keyText.getText());
				
				if(Manager.login(studyRoom.getManagerList(), ID, key) != null) {
					setVisible(false);
					new OpenOrClose(studyRoom);
				}
				else {
					setVisible(false);
					new ManagerLogin(studyRoom);
				}

			}
		});
		
		contentPane.add(loginButton);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
}
