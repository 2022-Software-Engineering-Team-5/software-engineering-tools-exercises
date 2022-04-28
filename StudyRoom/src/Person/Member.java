package Person;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Member class is a class with member information.
 * This class inherits Person class.
 * 
 * @author Lee Hajeong
 * @version JDK 11.0.11
 * @see {@link Person}
 */
public class Member extends Person {
	private int memberPW;
	private int memberWallet;

	public Member(){
		super();
		memberPW = 0;
		memberWallet = 0;
	}

	public Member(String nameInput, int IDinput, int PWinput, int moneyInput){
		super(nameInput, IDinput);
		memberPW = PWinput;
		memberWallet = moneyInput;
	}

	public void setInfo(int memberID, int memberPW) {
		setID(memberID);
		this.memberPW = memberPW;
	}

	// get PW
	public int getPW(){
		return memberPW;
	}

	// get balance
	public int getMoney() {
		return memberWallet;
	}

	// create new member
	@SuppressWarnings("resource")
	public static Member newUser(ArrayList<Member> testMember){
		String inputName;
		int inputID;
		int inputPW;

		Scanner keyboard = new Scanner(System.in);

		System.out.println("\n\t===== Creating new member account =====");

		System.out.print("-Enter your name: ");
		inputName = keyboard.next();

		System.out.print("-Enter your ID, ID should be an unique integer number: ");
		inputID = keyboard.nextInt();

		// check for duplicate ID
		while(findID(testMember, inputID) != null) {
			System.out.print("-ID already exists. Enter new ID: ");
			inputID = keyboard.nextInt();
		}

		System.out.print("-Enter your password, Password should be longer than 8 digits: ");
		inputPW = keyboard.nextInt();

		//check for PW length
		while((int)(Math.log10(inputPW) + 1) < 8){
			System.out.print("-Error: Password should have 8 letters or more, Enter new password: ");
			inputPW = keyboard.nextInt();
		}
		System.out.println();
		Member tempRegUser = new Member(inputName, inputID, inputPW, 0);
		return tempRegUser;
	}

	// create new member
	@SuppressWarnings("resource")
	public static Member newUser(ArrayList<Member> testMember, String inputName, int inputID, int inputPW){

		// check for duplicate ID
		while(findID(testMember, inputID) != null) {
			JOptionPane.showMessageDialog(null, "ID already exists. Enter new ID", "Duplicated ID", JOptionPane.DEFAULT_OPTION);
			return null;
		}

		//check for PW length
		while((int)(Math.log10(inputPW) + 1) < 8){
			JOptionPane.showMessageDialog(null, "Error: Password should have 8 letters or more, Enter new password", "PW Length", JOptionPane.DEFAULT_OPTION);
			return null;
		}

		Member tempRegUser = new Member(inputName, inputID, inputPW, 0);
		return tempRegUser;
	}

	@SuppressWarnings("resource")
	public static void chargeWallet(ArrayList<Member> testMember) {
		int IDInput;
		int moneyInput;

		Scanner keyboard = new Scanner(System.in);

		System.out.println("\n\t===== Charging balance =====");

		System.out.print("-Enter ID to charge: ");
		IDInput = keyboard.nextInt();

		while(findID(testMember, IDInput) == null) {
			System.out.print("-ID not found. Enter ID to charge: ");
			IDInput = keyboard.nextInt();
		}

		System.out.print("-Insert bill, in 1000 won increment: ");
		moneyInput = keyboard.nextInt();

		for(int i = 0; i < testMember.size(); i++) {
			if(IDInput == testMember.get(i).getID()) {
				testMember.get(i).memberWallet = moneyInput + testMember.get(i).memberWallet;
				System.out.println("-Charging Complete. " + testMember.get(i).getName() + ", Your balance is now " + testMember.get(i).memberWallet + " won.\n");
			}
		}
	}

	public static int chargeWallet(ArrayList<Member> testMember, int inputID, int inputMoney) {

		while(findID(testMember, inputID) == null) {
			JOptionPane.showMessageDialog(null, "ID not found. Enter ID to charge", "ID Failed", JOptionPane.DEFAULT_OPTION);
			return 0;
		}

		for(int i = 0; i < testMember.size(); i++) {
			if(inputID == testMember.get(i).getID()) {
				testMember.get(i).memberWallet = inputMoney + testMember.get(i).memberWallet;
				JOptionPane.showMessageDialog(null, "Charging Complete.", "Charge", JOptionPane.DEFAULT_OPTION);
				JOptionPane.showMessageDialog(null, testMember.get(i).getName() + ", Your balance is now " + testMember.get(i).memberWallet + " won", "Charge", JOptionPane.DEFAULT_OPTION);
				return 1;
			}
		}

		return 1;
	}

	// registered user login
	@SuppressWarnings("resource")
	public static Person login(ArrayList<Member> testMember) {
		int IDInput;
		int PWInput;
		Person member = new Member();
		System.out.println("\n\t===== Member Login =====");

		Scanner keyboard = new Scanner(System.in);

		System.out.print("-Enter ID: ");
		IDInput = keyboard.nextInt();
		System.out.print("-Enter password: ");
		PWInput = keyboard.nextInt();

		member = findID(testMember, IDInput, PWInput);

		while(member == null){
			System.out.println("-ID or PW mismatch");
			System.out.print("-Enter ID: ");
			IDInput = keyboard.nextInt();
			System.out.print("-Enter password: ");
			PWInput = keyboard.nextInt();

			member = findID(testMember, IDInput, PWInput);
		}

		return member;
	}

	// registered user login
	@SuppressWarnings("resource")
	public static Person login(ArrayList<Member> testMember, int inputID, int inputPW) {
		Person member = new Member();
		member = findID(testMember, inputID, inputPW);

		if(member == null) {
			JOptionPane.showMessageDialog(null, "ID or PW mismatch", "Login Failed", JOptionPane.DEFAULT_OPTION);
			return null;
		}

		return member;
	}

	// check for existence of user ID
	public static Person findID(ArrayList<Member> testMember, int inputID) {
		for(int i = 0; i < testMember.size(); i++) {
			if(inputID == testMember.get(i).getID()) {
				return testMember.get(i);
			}
		}
		return null;
	}

	// overloading findID method, adding PW match
	public static Person findID(ArrayList<Member> testMember, int inputID, int inputPW) {
		for(int i = 0; i < testMember.size(); i++) {
			if(inputID == testMember.get(i).getID() && inputPW == testMember.get(i).memberPW) {
				return testMember.get(i);
			}
		}
		return null;
	}
}