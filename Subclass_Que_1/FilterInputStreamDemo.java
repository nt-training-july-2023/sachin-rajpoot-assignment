package Subclass_Que_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
public class FilterInputStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file1 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt";
		String file2 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in2.txt";
		File f = new File(file1);
		FileInputStream fis = new FileInputStream(f);
		FilterInputStream flis = new BufferedInputStream(fis);

		int i = 0;
		while ((i = flis.read()) != -1) {
			System.out.print((char) i);
		}

		flis.close();
		
		fis.close();
	}

}
