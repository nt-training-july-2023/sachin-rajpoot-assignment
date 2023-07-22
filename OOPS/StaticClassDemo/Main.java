/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
package StaticClassDemo;

public class Main {
	int a = 1;
	static int b = 2;
	static private int c = 3;
	
	static class InnerClass {
		
		//accessing instance var of outer class
		void accessOuterVar() {
			// cannot access a bcoz its a non static var
//			System.out.println(a+" " +b+ " "+c);
			System.out.println(b+ " "+c);
		}
		
		static void staticMethod() {
			System.out.println("static method inside static class");
		}
	}
	
	void accessInnerClass() {
		// accessing non static method of static inner class just by creating a object
		// of inner class 
		// inside outer class
		Main.InnerClass in = new Main.InnerClass();
		in.accessOuterVar();
	}
	public static void main(String[] args) {

		// accessing static method of static inner class just by inner class name
		// inside outer class
		
		InnerClass.staticMethod();
	}
}
