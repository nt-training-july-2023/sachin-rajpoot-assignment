package StaticBlockDemo;

public class Main {
//	static block is declared outside of any method and inside the class body
	static {
		System.out.println("Inside 1st static block");
	}
	
	static {
		System.out.println("Inside 2nd static block");
	}
	
	static {
		System.out.println("Inside 3rd static block");
	}
//	it is not possible to have a static block inside a method
//	static void fun() {
//		static {
//			System.out.println("Inside 3rd static block");
//		}
//	}
	public static void main(String[] args) {
		
//		cannot have a static block inside the main method
//		static {
//			System.out.println("Inside 1st static block");
//		}

		DemoClass1 obj1 = new DemoClass1();
		
		DemoClass2 obj2 = new DemoClass2();
		
	}

}
