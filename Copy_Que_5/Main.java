package Copy_Que_5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter file name which you want to copy -> ");
		String file1 = sc.nextLine();

		System.out.println("Enter file name while you want to paste -> ");
		String file2 = sc.nextLine();
		File f1 = new File("C:\\Users\\Sachin\\eclipse-workspace\\ExceptionHandling\\src\\Copy_Que_5\\" + file1);
		File f2 = new File("C:\\Users\\Sachin\\eclipse-workspace\\ExceptionHandling\\src\\Copy_Que_5\\" + file2);
		try {
			FileInputStream fis = new FileInputStream(f1);

			int i = 0;
			String str = "";
			while ((i = fis.read()) != -1) {
				str += (char) i;
			}
			System.out.println(str);
			fis.close();
			FileWriter fw = new FileWriter(f2);

			fw.write(str);
			fw.close();
		} catch (Exception e) {

			throw new FileNotFoundException("enter valid file names");
		}
	}

}
