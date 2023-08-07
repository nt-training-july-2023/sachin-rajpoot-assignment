package Threshold_Map;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	private static int Threshold;

	static boolean Add(HashMap<Integer, String> map, int key, String value) {
		if (map.size() == Threshold - 1) {

			System.out.println("Threshold Reached, Can not add more elemnts");
			PrintFun(map);
			map.clear();
			return true;
		}

		map.put(key, value);
		return false;
	}

	static void PrintFun(HashMap<Integer, String> map) {
		System.out.println(map);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Threshold Integer value : ");
		int threshold = sc.nextInt();
		Main.Threshold = threshold;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		System.out.println(map.size());
		while (true) {
			System.out.println("Enter 1 to add and 2 to exit");
			int choice = sc.nextInt();
			boolean flag = true;
			switch (choice) {
			case 1: {
				System.out.println("Enter key and value to add");
				int key = sc.nextInt();
				sc.nextLine();
				String value = sc.next();
				if (Main.Add(map, key, value)) {
					flag = false;
				}
				break;
			}
			default:
				flag = false;

			}
			if (flag == false)
				break;
		}

	}

}
