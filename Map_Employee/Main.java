package Map_Employee;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 1; i <= 10; i++) {
			map.put(i * 100, "A" + i);
		}
		System.out.println(map);
		System.out.print("keys are -> ");
		map.forEach((e1, e2) -> System.out.print(e1 + " "));

		System.out.println();

		System.out.print("values are -> ");
		map.forEach((e1, e2) -> System.out.print(e2 + " "));

	}

}
