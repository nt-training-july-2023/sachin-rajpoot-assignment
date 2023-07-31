package Subclass_Que_1;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedIODemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PipedInputStream pis = new PipedInputStream();
		PipedOutputStream pos = new PipedOutputStream();

		try {
			pos.connect(pis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread t1 = new Thread() {
			public void run() {
				try {
					for (int i = 11; i <= 20; i++) {

						pos.write(i);
						System.out.println("Piped Output Stream write -> " + i);
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					pos.close();
				}

				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		Thread t2 = new Thread() {
			public void run() {

				try {
					int i = 0;
					while ((i = pis.read()) != -1) {
						System.out.println("Piped Input Stream read -> " + i);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					pis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		t1.start();
		t2.start();
	}

}
