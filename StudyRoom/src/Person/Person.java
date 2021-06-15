package Person;

public class Person {
	private String name;
	private int ID;

	public Person() {
	    name = null;
	}

	public Person(String nameInput) {
		name = nameInput;
	}
	
	public Person(int IDInput) {
		ID = IDInput;
	}
	
	public Person(String nameInput, int IDInput) {
		name = nameInput;
		ID = IDInput;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
}
