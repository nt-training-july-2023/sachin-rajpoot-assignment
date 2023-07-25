package ReadFile_Que_4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Sachin\\eclipse-workspace\\" + "ExceptionHandling\\src\\ReadFile_Que_4\\Test.txt");
			int i = 0;
			while ((i = fis.read()) != -1) {
				System.out.print((char) i);
			}

			fis.close();

		} catch (Exception e) {

		}

	}

}
