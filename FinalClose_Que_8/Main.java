package FinalClose_Que_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(System.in);
		try {
			System.out.println("Enter file name -> ");
			String file = s.next();
			File f = new File("C:\\Users\\Sachin\\eclipse-workspace\\"
					+ "ExceptionHandling\\src\\FinalClose_Que_8\\"+file);
			Scanner sc = new Scanner(f);
			
			
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			sc.close();
		} catch(Exception e) {
			throw new FileNotFoundException("Enter a valid file address");
		}
		finally {
			s.close();
;		}
		
		

	}

}
