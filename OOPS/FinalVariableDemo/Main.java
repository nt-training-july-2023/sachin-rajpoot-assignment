package FinalVariableDemo;

public class Main {

	
	// to create fixed constant values
	final int empID = 123;
	
	// to pass as a parameter in method so it won't get changed by it
	void printStar(final String str) {
		System.out.println(str);
		System.out.println(empID);
	}
	public static void main(String[] args) {
		Main m = new Main();
		m.printStar("kaameeeHaaaaMeeeeeeHaaaaaaa......");

	}

}
