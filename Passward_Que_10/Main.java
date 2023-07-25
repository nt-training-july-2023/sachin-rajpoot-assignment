package Passward_Que_10;

import java.util.Scanner;
import java.util.regex.Pattern;

class InvalidPasswordException extends Exception{
	public InvalidPasswordException(String errorMessage) {
		super(errorMessage);
	}
}
public class Main {

	public static boolean cintainsLetterAndNumber(String str) {
       
        Pattern letter = Pattern.compile(".*[a-zA-Z].*");
        Pattern digit = Pattern.compile(".*\\d.*");

    
        return letter.matcher(str).matches() && digit.matcher(str).matches();
    }
	public static void main(String[] args) throws InvalidPasswordException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter passward : ");
		String passward = sc.nextLine();
		
		if((passward.length() < 8) || (cintainsLetterAndNumber(passward)) == false) {
			throw new InvalidPasswordException("Enter valid passward");
		}
		
			System.out.println("Successfull");
		
	}

}
