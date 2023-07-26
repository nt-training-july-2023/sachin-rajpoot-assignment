package Exploring_Thread_Que_1;

class OneClass extends Thread {
	public void run() {
		int i = 2;
		while (true) {
			System.out.println("Extends from Thread Class " + i);
		}
	}
}

class TwoClass implements Runnable {
	public void run() {
		int i = 3;
		while (true)
			System.out.println("Implements Runnable interface " + i);
	}
}

public class Main {

	public static void main(String[] args) {

		// Using Anonymous class
		Thread t1 = new Thread() {
			public void run() {
				int i = 1;
				while (true) {
					System.out.println("Anonymous class -> " + i);
				}
			}
		};

		// Using regular class
		OneClass t2 = new OneClass();

		// using Interface
		TwoClass tw = new TwoClass();
		Thread t3 = new Thread(tw);

		// starting threads
//		t1.start();
//		t2.start();
//		t3.start();

		// create and name a thread using constructor
		Thread t4 = new Thread("Goku");
//		System.out.println("Name of thread 4 is -> " + t4.getName());

		// name and reference using constructor
		TwoClass th = new TwoClass();
		Thread t5 = new Thread(th, "Naruto");
//		System.out.println("Name of thread 5 is -> " + t5.getName());

		// Using sleep method here
		Thread t6 = new Thread() {
			public void run() {
				for (;;) {
					System.out.println("This Thread is waiting 2 second");
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		Thread t7 = new Thread() {
			public void run() {
				for (;;) {
					System.out.println("This Thread is waiting 3 second");
					try {
						sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

//		t7.start();
//		t6.start();

		// using join method
		Thread t8 = new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("First Thread " + i);

				}

			}
		};

		Thread t9 = new Thread() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Second Thread " + i);

				}
			}
		};

		// starting
//		t8.start();
//		try {
//			// using join 
//			t8.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		t9.start();

	}

}
