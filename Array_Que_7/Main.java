package Array_Que_7;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IndexOutOfBoundsException, NullPointerException {

		Scanner sc = new Scanner(System.in);

//		int[] arr = { 1, 2, 3, 4 };

		Integer[] intArray = { 1, 2, 3, 4, 5 };
		intArray[0] = null;

		System.out.println("Enter the index of the array element you wanna see :");
		int indx = sc.nextInt();

		if (indx < 0 || indx >= intArray.length) {
			throw new IndexOutOfBoundsException("Enter valid index");
		} else {
			if (intArray[indx] == null) {
				throw new NullPointerException("Enter valid index");
			} else {
				System.out.println("Element at index " + indx + " is " + intArray[indx]);
			}

		}

	}

}
