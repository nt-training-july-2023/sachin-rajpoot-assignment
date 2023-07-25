/**
 * This program finds the area of different
 * shapes and overrides the area method of 
 * parent class
 * 
 * 
 * @author Sachin
 * @version 1.0
 * @since 24-07-2023
 * 
 */


package Area_Que_1;

import java.util.Scanner;

class InvalidInputException extends Exception {  
    public InvalidInputException(String errorMessage) {  
    super(errorMessage);  
    }  
}  

abstract class Shapes{
	/**
	 * This method will be overriden by its child classes
	 * 
	 * @param  a is the first parameter of area method
	 */
	void area(int a) throws InvalidInputException {
		
		System.out.println("Shape Class Method");
		throw new InvalidInputException("values can not be equal or smaller than 0");
	}
	/**
	 * This method will be overriden by its child classes
	 * 
	 * @param a is the first parameter of area method
	 * @param b is the second parameter of area method
	 */
	void area(int a, int b) throws InvalidInputException {
		throw new InvalidInputException("values can not be equal or smaller than 0");
	}
	
}

class Rectangle extends Shapes {
	/**
	 * This method calculates area of rectangle
	 * 
	 * @param l is the 1st parameter for the length
	 * @param w is the 1st parameter for the width
	 * @throws InvalidInputException 
	 */
	@Override
	void area(int l, int w) throws InvalidInputException  {
		
		if(l <= 0 || w <= 0) {
			throw new InvalidInputException("Length or Width can not be equal or smaller than 0");	
		}
		System.out.println("Area of Rectangle is -> "+(l*w));
	}
}

class Circle extends Shapes {
	/**
	 * This method calculates area of circle
	 * 
	 * @param r is the only parameter for the radius
	 * @throws InvalidInputException
	 */
	@Override
	void area(int r) throws InvalidInputException {
		
		if(r <= 0) 
			throw new InvalidInputException("Radius can not be smaller or equal to 0");
		System.out.println("Area of Cricle is -> "+(3.14*r*r));
		
	}
}

class Triangle extends Shapes {
	/**
	 * This method calculates area of triangle
	 * 
	 * @param b is the first parameter for the base 
	 * @param h is the second parameter for the height
	 * 
	 * @throws InvalidInputException
	 */
	@Override
	void area(int b, int h)throws InvalidInputException {
		if(b <= 0 || h <= 0) {
			throw new InvalidInputException("Base or Height can not be equal or smaller than 0");	
		}
		System.out.println("Area of Triangle is -> "+((b*h)/2));
	}
}

public class Main {

	/**
	 * This is the main method which makes use of area method.
	 * 
	 */
	public static void main(String[] args) {
		
		Rectangle r = new Rectangle();
		Circle c = new Circle(); 
		Triangle t = new Triangle(); 
		try {
			r.area(0, 3);
			c.area(2);
			t.area(2, 3);
		}
		catch(InvalidInputException e){
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
