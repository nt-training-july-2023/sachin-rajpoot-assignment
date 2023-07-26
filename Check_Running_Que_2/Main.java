package Check_Running_Que_2;

class thread1 implements Runnable {
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

class thread2 implements Runnable {
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class thread3 implements Runnable {
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class Main {

	public static void main(String[] args) {

		thread1 t1 = new thread1();
		thread2 t2 = new thread2();
		thread3 t3 = new thread3();

		Thread td1 = new Thread(t1, "Thread 1");
		Thread td2 = new Thread(t2, "Thread 2");
		Thread td3 = new Thread(t3, "Thread 3");

		td1.start();

		td2.start();

		td3.start();
	}

}
