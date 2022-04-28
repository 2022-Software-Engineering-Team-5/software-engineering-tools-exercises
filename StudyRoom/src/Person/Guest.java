package Person;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Guest class is a class with non-member information.
 * This class inherits the Person class.
 * 
 * @author Lee Hajeong
 * @version JDK 11.0.11
 * @see {@link Person}
 *
 */
public class Guest extends Person {
	private static int guestCount = 1000000;

    public Guest(){
        super();
    }

    public Guest(String nameInput, int IDInput){
        super(nameInput, IDInput);
    }

    public void setGuestID(int guestID) {
    	setID(guestID);
    }
    
    // create new guest
    public static Guest newGuest(){
        String inputName;
        int inputID;

        @SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
        
        System.out.println("\n\t===== Creating new guest account =====");
        System.out.print("-Enter your name: ");
        inputName = keyboard.nextLine();
        
        inputID = guestCount + 1;
        guestCount++;
        
        Guest tempGuestUser = new Guest(inputName, inputID);
        
        System.out.println("-" + tempGuestUser.getName() + ", Your temporary guest ID is: " + tempGuestUser.getID());

        return tempGuestUser;
    }
    
    // create new guest
    public static Guest newGuest(String name){
        int inputID;
        
        inputID = guestCount + 1;
        guestCount++;
        
        Guest tempGuestUser = new Guest(name, inputID);
        JOptionPane.showMessageDialog(null, tempGuestUser.getName() + ", Your temporary guest ID is: " + tempGuestUser.getID(), "Guest Login", JOptionPane.DEFAULT_OPTION);

        return tempGuestUser;
    }
}
