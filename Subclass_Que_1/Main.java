package Subclass_Que_1;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.SequenceInputStream;
import java.io.Serializable;

class OneClass implements Serializable {
	int id;
	String name;
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		FileInputStream fis = new FileInputStream("C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt");
//		int i=0;
//		while((i=fis.read()) != -1) {
//			System.out.print((char)i);
//		}
//		fis.close();

//		System.out.println();

		// used to read byte array
//		byte[] byteArray = {1,2,3,4};
//		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
//		while((i=bais.read()) != -1) {
//			System.out.print((byte)i);
//		}
//		bais.close();

//		 Sequence wise reading
		String file1 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt";
		String file2 = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in2.txt";
//		FileInputStream fis1 = new FileInputStream(file1);
//		FileInputStream fis2 = new FileInputStream(file2);
//		SequenceInputStream sis = new SequenceInputStream(fis1,fis2);
//		
//		int i=0;
//		while((i=sis.read()) != -1) {
//			System.out.print((char)i);
//		}
//		sis.close();

		// FilterInputStream
//		File f = new File(file1);
//		FileInputStream fis = new FileInputStream(f);
//		FilterInputStream flis = new BufferedInputStream(fis);
//		
//		int i=0;
//		while((i=flis.read()) != -1) {
//			System.out.print((char)i);
//		}

//		flis.close();
//		f.close();
//		fis.close();

		// BufferedInputStream
//		FileInputStream fis = new FileInputStream(file1);
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		int i=0;
//		while((i=bis.read()) != -1) {
//			System.out.print((char)i);
//		}
//		bis.close();
//		fis.close();

		String outFile = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\out.txt";

		try {

			OneClass obj = new OneClass();
			obj.id = 123;
			obj.name = "Goku";
			
			FileOutputStream fos = new FileOutputStream(outFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
			fos.close();
			

			FileInputStream fis = new FileInputStream(outFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			OneClass obj2 = (OneClass) ois.readObject();
			System.out.println(obj2.id + "   " + obj2.name);
			ois.close();
			fis.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
