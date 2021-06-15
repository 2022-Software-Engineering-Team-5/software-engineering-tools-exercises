package GUI;

import StudyRoom.*;
import Person.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberGUI extends JFrame implements ActionListener {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();

	public MemberGUI(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;
		
		setSize(WIDTH, HEIGHT);
		setTitle("Study Room");
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new GridLayout(2, 0));
		
		// label "Member"
		JLabel title = new JLabel("Member", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.BLACK);
		contentPane.add(title);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(255, 249, 227));
		
		// member button
		JButton member = new JButton("Member");
		member.setBackground(Color.WHITE);
		member.setForeground(Color.BLACK);
		member.addActionListener(this);
		buttonPanel.add(member);

		// member charge button
		JButton memberCharge = new JButton("Member Charge");
		memberCharge.setBackground(Color.WHITE);
		memberCharge.setForeground(Color.BLACK);
		memberCharge.addActionListener(this);
		buttonPanel.add(memberCharge);
		
		// member registration button
		JButton memberRegistration = new JButton("Member Registration");
		memberRegistration.setBackground(Color.WHITE);
		memberRegistration.setForeground(Color.BLACK);
		memberRegistration.addActionListener(this);
		buttonPanel.add(memberRegistration);
		
		contentPane.add(buttonPanel);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Member":
			setVisible(false);
			new MemberLogin(studyRoom);
			break;
			
		case "Member Charge":
			setVisible(false);
			new MemberCharge(studyRoom);
			break;
			
		case "Member Registration":
			setVisible(false);
			new MemberRegistration(studyRoom);
			break;
		}
	}
}
