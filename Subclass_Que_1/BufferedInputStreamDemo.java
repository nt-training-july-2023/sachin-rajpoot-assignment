package Subclass_Que_1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file1 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt";
		String file2 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in2.txt";
		FileInputStream fis = new FileInputStream(file1);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int i=0;
		while((i=bis.read()) != -1) {
			System.out.print((char)i);
		}
		bis.close();
		fis.close();
	}

}
