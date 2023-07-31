package Shape;

interface Shape {
	public abstract void area();
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape square = () -> {
			int side = 2;
			System.out.println("Area of square is -> " + (side * side));

		};

		Shape cricle = () -> {
			int radius = 2;
			System.out.println("Area of cricle is -> " + (3.14 * radius * radius));

		};

		Shape cube = () -> {
			int side = 2;
			System.out.println("Area of cube is -> " + (side * side));

		};

		Shape sphere = () -> {
			int radius = 2;
			System.out.println("Area of sphere is -> " + (4 * 3.14 * radius * radius));

		};

		Shape rectangle = () -> {
			int length = 2, width = 3;
			System.out.println("Area of rectangle is -> " + (length * width));

		};

		Shape cylinder = () -> {
			int height = 2, radius = 3;
			System.out.println("Area of cylinder is -> " + (3.14 * radius * radius * height));

		};

		square.area();
		sphere.area();
		rectangle.area();
		cylinder.area();
		cube.area();
		cricle.area();
	}

}
