package GUI;

import StudyRoom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI: for logout
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 */
public class LogoutGUI extends JFrame implements ActionListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();
	
	public LogoutGUI(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;
		setSize(WIDTH, HEIGHT);
		setTitle("Study Room");
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(104, 113, 215));
		contentPane.setLayout(new GridLayout(2, 0));
		
		JLabel title = new JLabel("Logout", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.WHITE);
		contentPane.add(title);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new FlowLayout());
		
		// member
		JButton member = new JButton("Member");
		member.setBackground(new Color(104, 113, 215));
		member.setForeground(Color.WHITE);
		member.setFont(new Font("Arial", Font.PLAIN, 15));
		member.addActionListener(this);
		buttonPanel.add(member);

		// guest
		JButton guest = new JButton("Guest");
		guest.setBackground(new Color(104, 113, 215));
		guest.setForeground(Color.WHITE);
		guest.setFont(new Font("Arial", Font.PLAIN, 15));
		guest.addActionListener(this);
		buttonPanel.add(guest);
		
		contentPane.add(buttonPanel);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Member")) {
			setVisible(false);
			new InputGUI(studyRoom, e.getActionCommand());
		}
		else {
			setVisible(false);
			new InputGUI(studyRoom, e.getActionCommand());
		}
	}
}