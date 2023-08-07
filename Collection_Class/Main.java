package Collection_Class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(3);
		al.add(4);
		al.add(3);
		System.out.println(al);

		Collections.addAll(al, 4, 5, 6, 7, 8, 9);
		System.out.println(al);

		int index = Collections.binarySearch(al, 3);
		System.out.println(index);

		Collections.sort(al);
		System.out.println(al);

		List<Integer> al2 = new ArrayList<Integer>();
		al.add(1);
		al.add(3);
		al.add(4);
		al.add(3);

		Collections.copy(al, al2);
		System.out.println(al);

		System.out.println(Collections.disjoint(al, al2));

		System.out.println(Collections.max(al));
		System.out.println(Collections.min(al));
		System.out.println(Collections.replaceAll(al, 1, 10));
		System.out.println(al);
		Collections.rotate(al, 2);
		System.out.println(al);

	}

}
