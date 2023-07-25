package String_Que_6;

import java.util.Scanner;

class IndexOutOfBoundsException extends Exception {
	public IndexOutOfBoundsException(String errorMessage) {
		super(errorMessage);
	}
}

public class Main {

	public static void main(String[] args) throws IndexOutOfBoundsException, NullPointerException {
		Scanner sc = new Scanner(System.in);
		
		String[] arr = {"aaa", "bbb", "ccc","ddd", null};
		
		System.out.println("Enter the index of the string you wanna see :");
		int indx = sc.nextInt();
		
		if(indx < 0 || indx >= arr.length) {
			throw new IndexOutOfBoundsException("Enter valid index");
		}
		else {
			if(arr[indx] == null) {
				throw new NullPointerException("Enter valid index");
			}
			else {
			System.out.println("String at index "+indx+" is "+arr[indx]);
			}
		}
		
		
}
}
