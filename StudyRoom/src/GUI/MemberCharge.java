package GUI;

import StudyRoom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI: Window for charging money (member)
 * Input ID and Money (1000 won).
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 */
public class MemberCharge extends JFrame {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();
	
	public MemberCharge(StudyRoomInterface studyRoom) {
		this.studyRoom = studyRoom;

		setTitle("Study Room");
		setSize(WIDTH, HEIGHT);

		Container contentPane = getContentPane();
		// contentPane.setBackground(new Color(196, 233, 242));
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new GridLayout(3, 0));
		
		JLabel title = new JLabel("Member Charge", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setForeground(Color.BLACK);
		contentPane.add(title);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setLayout(new GridLayout(2, 0));
		
		// ID
		JPanel IDPanel = new JPanel();
		IDPanel.setBackground(Color.WHITE);
		IDPanel.setLayout(new FlowLayout());
		
		JLabel IDLabel = new JLabel("ID", JLabel.LEFT);
		IDLabel.setForeground(Color.BLACK);
		IDPanel.add(IDLabel);

		JTextField IDText = new JTextField(20);
		IDPanel.add(IDText);
		inputPanel.add(IDPanel);
		
		// money
		JPanel moneyPanel = new JPanel();
		moneyPanel.setBackground(Color.WHITE);
		moneyPanel.setLayout(new FlowLayout());
		
		JLabel moneyLabel = new JLabel("Money (1000 won)", JLabel.LEFT);
		moneyLabel.setForeground(Color.BLACK);
		moneyPanel.add(moneyLabel);

		JTextField moneyText = new JTextField(20);
		moneyPanel.add(moneyText);
		inputPanel.add(moneyPanel);
		
		JButton enterButton = new JButton("Enter");
		enterButton.setBackground(new Color(196, 233, 242));
		enterButton.setFont(new Font("Arial", Font.PLAIN, 30));
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(IDText.getText());
				int money = Integer.parseInt(moneyText.getText());
				
				if(studyRoom.memberCharge(ID, money) == 1) {
					setVisible(false);
					new MemberGUI(studyRoom);
				}
				else {
					setVisible(false);
					new MemberCharge(studyRoom);
				}
			}
		});
		contentPane.add(inputPanel);
		contentPane.add(enterButton);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
}
