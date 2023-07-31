package Subclass_Que_1;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt");
		int i=0;
		while((i=fis.read()) != -1) {
			System.out.print((char)i);
		}
		fis.close();
	}

}
