package Subclass_Que_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

public class SequenceInputStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file1 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt";
		String file2 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in2.txt";
		FileInputStream fis1 = new FileInputStream(file1);
		FileInputStream fis2 = new FileInputStream(file2);
		SequenceInputStream sis = new SequenceInputStream(fis1,fis2);
		
		int i=0;
		while((i=sis.read()) != -1) {
			System.out.print((char)i);
		}
		sis.close();
	}

}
