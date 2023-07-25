package Even_Que_9;

import java.util.Scanner;

class NotEvenNumberException extends Exception {
	public NotEvenNumberException(String errorMessage) {
		super(errorMessage);
	}
}

public class Main {

	public static void main(String[] args) throws NotEvenNumberException {

		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		int num = 0;
		try {
			num = Integer.parseInt(str);
			if (num % 2 != 0) {
				throw new Exception();
			} else {
				System.out.println("an even number");
			}
		} catch (NumberFormatException n) {
			throw new NumberFormatException("Enter a valid number ");
		} catch (Exception e) {
			throw new NotEvenNumberException("Not an even number");
		}

	}

}
