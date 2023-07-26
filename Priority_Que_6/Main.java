package Priority_Que_6;
class Thread1 extends Thread{
	public Thread1(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	public void run() {
//		System.out.println(Thread.currentThread().getName()+" is running.");
		System.out.println("Adarsh");
	}
}
class Thread2 extends Thread{
	public Thread2(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	public void run() {
		System.out.println(Thread.currentThread().getName()+" is running.");
	}
}
class Thread3 extends Thread{
	public Thread3(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	public void run() {
		System.out.println(Thread.currentThread().getName()+" is running.");
	}
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread1 t1 = new  Thread1("one");
		Thread2 t2 = new  Thread2("two");
		Thread3 t3 = new  Thread3("three");
		
		t1.setPriority(10);
		t2.setPriority(1);
		t3.setPriority(1);
		
		t1.start(); t2.start(); t3.start();

	}

}
