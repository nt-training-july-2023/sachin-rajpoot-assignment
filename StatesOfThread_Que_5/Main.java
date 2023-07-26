package StatesOfThread_Que_5;

class Thread1 extends Thread{
	public void run() {
		
		// running
		System.out.println("Thread state -> "+getState());
		
		for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		// TIMED_WAITING due to sleep
        System.out.println("Thread state -> TIMED_WAITING due to sleep -> " + getState());
        
     
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

         
        System.out.println("Thread state  -> " + getState());
	}
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread1 t1 = new Thread1();
		
		// new
		System.out.println("Thread state -> "+t1.getState());
		
		t1.start();
		
		try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // terminated
        System.out.println("Thread state -> " + t1.getState());
	}

}
