package Rectangle_Que_3;

import java.util.Scanner;

class InvalidDimensionException extends Exception {
	public InvalidDimensionException(String errorMessage) {
		super(errorMessage);
	}
}

class Rectangle {
	int length, width;
	
	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}
	
	void area() {
		System.out.println("Area of rectangle is : "+(length*width));
	}
	
}

public class Main {

	public static void main(String[] args) throws InvalidDimensionException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter length and width :");
		int length = sc.nextInt();
		int width = sc.nextInt();
		
		if(length <= 0 || width <= 0) {
			throw new InvalidDimensionException("Enter valid values of length and width.");
		}
		else {
			Rectangle r = new Rectangle(length, width);
			r.area();
		}

	}

}
