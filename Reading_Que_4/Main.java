package Reading_Que_4;

class Thread1 extends Thread{
	
	String message;
	Thread1(String message){
		this.message=message;
	}
	public void run() {
		while(true)
		System.out.println(Thread.currentThread().getName()+" is reading "+message);
	}
}

class Thread2 extends Thread{
	
	String message;
	Thread2(String message){
		this.message=message;
	}
	public void run() {
		while(true)
		System.out.println(Thread.currentThread().getName()+" is reading "+message);
	}
}
public class Main {

	public static void main(String[] args) {
		String message = "Shared Message";
		Thread1 t1 = new Thread1(message);
		Thread2 t2 = new Thread2(message);
//		Thread1 t2 = new Thread1(message);
		
		t1.start(); t2.start();
		
	}

}
