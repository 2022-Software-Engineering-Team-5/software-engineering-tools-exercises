package Person;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Manager extends Person {
    private int managerKey;

    public Manager() {
    	super();
    	managerKey = 0000;
    }
    
    public Manager(String nameInput, int IDInput, int keyInput) {
        super(nameInput, IDInput);
        managerKey = keyInput;
    }

    // get manager key
    public int getKey(){
        return managerKey;
    }
    
    // manager login
    @SuppressWarnings("resource")
	public static Person login(ArrayList<Manager> testManager) {
    	int IDInput;
    	int KeyInput;
    	Person manager = new Manager();
        System.out.println("\t===== Manager Login =====");

        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Enter ID: ");
        IDInput = keyboard.nextInt();
        
        System.out.print("Enter Key: ");
        KeyInput = keyboard.nextInt();

        while(findID(testManager, IDInput, KeyInput) == null) {
    		System.out.println("\nID or PW mismatch");
    		
    		System.out.print("Enter ID: ");
        	IDInput = keyboard.nextInt();
        	
        	System.out.print("Enter Key: ");
        	KeyInput = keyboard.nextInt();
    	}
        return manager;	
    }
    
    // manager login
    @SuppressWarnings("resource")
	public static Person login(ArrayList<Manager> testManager, int inputID, int inputKey) {
    	Person manager = new Manager();
    	
        while(findID(testManager, inputID, inputKey) == null) {
    		JOptionPane.showMessageDialog(null, "ID or PW Mismatch", "Login Failed", JOptionPane.DEFAULT_OPTION);
    		return null;
    	}
        return manager;	
    }
    
    public static Person findID(ArrayList<Manager> testManager, int inputID, int inputKey) {
    	for(int i = 0; i < testManager.size(); i++) {
    		if(inputID == testManager.get(i).getID() && inputKey == testManager.get(i).managerKey) {
    			return testManager.get(i);
    		}
    	}
		return null;
    }
}