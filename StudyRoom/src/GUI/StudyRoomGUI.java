package GUI;

import StudyRoom.*;
import Person.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI: Main window
 * Button: Manager, Member, Guest, Logout, Exit
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 */
public class StudyRoomGUI extends JFrame implements ActionListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	
	private Container contentPane = getContentPane();
	private StudyRoomInterface studyRoom = new StudyRoom();
	
	public StudyRoomGUI(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;

		setTitle("Study Room");
		setSize(WIDTH, HEIGHT);

		// when closed
		if(!studyRoom.getOpen()) {
			contentPane.setBackground(Color.BLACK);
			contentPane.setLayout(new BorderLayout());

			// label "Study Room"
			JLabel title = new JLabel("Study Room", JLabel.CENTER);
			title.setFont(new Font("Arial", Font.BOLD, 30));
			title.setForeground(Color.WHITE);
			contentPane.add(title, BorderLayout.CENTER);

			// open button
			JButton openButton = new JButton("Open");
			openButton.setBackground(Color.WHITE);
			openButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					new ManagerLogin(studyRoom);
				}
			});
			contentPane.add(openButton, BorderLayout.SOUTH);
			setVisible(true);
		}

		// when opened
		if(studyRoom.getOpen()) {
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(new GridLayout(2, 0));

			// label "Study Room"
			JLabel title = new JLabel("Study Room", JLabel.CENTER);
			title.setFont(new Font("Arial", Font.BOLD, 30));
			title.setForeground(Color.BLACK);
			contentPane.add(title, BorderLayout.NORTH);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.WHITE);
			buttonPanel.setLayout(new FlowLayout());

			// manager button
			JButton manager = new JButton("Manager");
			manager.setBackground(Color.BLACK);
			manager.setForeground(Color.WHITE);
			manager.addActionListener(this);

			// member button
			JButton member = new JButton("Member");
			member.setBackground(Color.BLACK);
			member.setForeground(Color.WHITE);
			member.addActionListener(this);

			// guest button
			JButton guest = new JButton("Guest");
			guest.setBackground(Color.BLACK);
			guest.setForeground(Color.WHITE);
			guest.addActionListener(this);

			// logout button
			JButton logout = new JButton("Logout");
			logout.setBackground(Color.BLACK);
			logout.setForeground(Color.WHITE);
			logout.addActionListener(this);

			// logout button
			JButton exit = new JButton("Exit");
			exit.setBackground(Color.BLACK);
			exit.setForeground(Color.WHITE);
			exit.addActionListener(this);
			
			buttonPanel.add(manager);
			buttonPanel.add(member);
			buttonPanel.add(guest);
			buttonPanel.add(logout);
			buttonPanel.add(exit);

			contentPane.add(buttonPanel, BorderLayout.CENTER);
			
			WindowDestroyer wh = new WindowDestroyer(studyRoom);
			addWindowListener(wh);
			
			setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		
		case "Manager":
			setVisible(false);
			new ManagerLogin(studyRoom);
			break;

		case "Member":
			setVisible(false);
			new MemberGUI(studyRoom);
			break;

		case "Guest":
			setVisible(false);
			new GuestLogin(studyRoom);
			break;

		case "Logout":
			setVisible(false);
			new LogoutGUI(studyRoom);
			break;

		case "Exit":
			studyRoom.finallyUpdateInformation();
			setVisible(false);
			System.exit(0);
			break;
		}
	}
}