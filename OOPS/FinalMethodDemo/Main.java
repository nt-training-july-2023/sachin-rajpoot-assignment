package FinalMethodDemo;

public class Main  extends FinalMethodDemo1 {

	 // calling final and regular method
	void fun3() {
		super.fun1();
		super.fun2();
	}
	
	// can not override final 
//		void fun1() {
//			
//		}
//	it is not possible to override a non-static method
//	of the parent class and make it 
//	static in the child class 
//	static void fun2() {
//		System.out.println("overriden method ");
//	}
	
	 void fun2() {
	System.out.println("overriden method ");
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FinalMethodDemo1  fm = new FinalMethodDemo1();
		fm.fun1();
		
		Main m = new Main();
		m.fun3();
		m.fun2();
	}

}
