package ThreeProgram_Que_3;

class Fibonacci extends Thread {
	int num;

	Fibonacci(int num) {
		this.num = num;
	}

	public void run() {
		int x = 0;
		int y = 1;
		for (int i = 2; i <= num; i++) {
			int z = x + y;
			System.out.println("Fabo -> " + z);
			x = y;
			y = z;
		}
	}
}

class Sum extends Thread {

	int[] arr;

	Sum(int[] arr) {
		this.arr = arr;
	}

	public void run() {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		System.out.println("Sum is -> " + sum);
	}
}

class Reverse extends Thread {
	int[] arr;

	Reverse(int[] arr) {
		this.arr = arr;
	}

	public void run() {
		System.out.print("Givenlist is  -> ");
		for (int x : arr)
			System.out.println(x + " ");
		for (int i = 0; i < arr.length / 2; i++) {
			int tmp = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = tmp;
		}
		System.out.print("Reversed list is -> ");
		for (int x : arr)
			System.out.println(x + " ");
	}
}

class Display extends Thread {
	int num;

	Display(int num) {
		this.num = num;
	}

	public void run() {
		for (int i = 0; i < num; i++)
			System.out.println("Display ->" + i);
	}
}

public class Main {

	public static void main(String[] args) {

		Fibonacci f = new Fibonacci(10);
		int[] arr = { 1, 2, 3, 4, 5, };
		Sum s = new Sum(arr);
		Reverse r = new Reverse(arr);
		Display d = new Display(10);

		d.setPriority(10);
		r.setPriority(9);
		s.setPriority(8);

		d.start();
		r.start();
		s.start();
		f.start();

	}

}
