package Subclass_Que_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
