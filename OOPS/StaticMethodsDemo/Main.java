package StaticMethodsDemo;
// we can call static method just by class name 
// can be called only in static methods
public class Main {
	
	int a = 10;
	
	void printStars() {
		System.out.println("Inside printStars");
		// accessing static method inside non static method
		fun1();
	}
	static void fun1() {
		System.out.println("Inside fun 1");
	}
	public static void main(String[] args) {
		
		Main.fun1();
		
		DemoClass1.fun1();
		
// 		cannot access non static member of class
//		int b = a;
		
// 		cannot access non static method of class
//		printStars();
		
// 		non static method can access the static method
		Main m = new Main();
		m.printStars();

	}

}
