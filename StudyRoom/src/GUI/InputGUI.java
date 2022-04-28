package GUI;

import StudyRoom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI: for input
 * 
 * @author Nam Seonwoo
 * @version JDK 17.0.2
 */
public class InputGUI extends JFrame {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	StudyRoomInterface studyRoom = new StudyRoom();
	
	public InputGUI(StudyRoomInterface studyRoom, String type) {
		this.studyRoom = studyRoom;
		
		setSize(WIDTH, HEIGHT);
		setTitle("Study Room");
		
		Container contentPane = getContentPane();
		contentPane.setBackground(new Color(104, 113, 215));
		contentPane.setLayout(new GridLayout(2, 0));
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(new Color(104, 113, 215));
		inputPanel.setLayout(new FlowLayout());
		
		JLabel typeLabel = new JLabel(type, JLabel.CENTER);
		typeLabel.setFont(new Font("Arial", Font.BOLD, 30));
		typeLabel.setForeground(Color.WHITE);
		contentPane.add(typeLabel);
		
		JLabel IDLabel = new JLabel("ID");
		IDLabel.setFont(new Font("Arial", Font.BOLD, 20));
		IDLabel.setForeground(Color.WHITE);
		inputPanel.add(IDLabel);
		
		JTextField IDText = new JTextField(8);
		inputPanel.add(IDText);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(IDText.getText());
				
				if(type.equals("Member")) {
					studyRoom.exitRoom("Member", ID);
					setVisible(false);
					new StudyRoomGUI(studyRoom);
				}
				else {
					studyRoom.exitRoom("Guest", ID);
					setVisible(false);
					new StudyRoomGUI(studyRoom);
				}
			}
		});
		inputPanel.add(enterButton);
		contentPane.add(inputPanel);
		
		WindowDestroyer wh = new WindowDestroyer(studyRoom);
		addWindowListener(wh);
		
		setVisible(true);
	}
}