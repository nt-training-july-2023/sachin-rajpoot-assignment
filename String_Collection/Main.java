package String_Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedHashSet<String> al = new LinkedHashSet<>();
		for (int i = 1; i <= 20; i++) {
			al.add("" + i);
		}
		System.out.println(al);

		// adding an list
		ArrayList<String> al2 = new ArrayList<String>();
		al2.add("1");
		al2.add("3");
		al2.add("77");
		al2.add("86");
		al.addAll(al2);

		System.out.println(al);

		// removing
		al.remove(0);

		System.out.println(al);

		// removing an list
		al.removeAll(al2);

		System.out.println(al);

		ArrayList<String> al3 = new ArrayList<String>();
		al3.add("1");
		al3.add("2");
		al3.add("4");

		// retaning a list
		al.addAll(al3);
		System.out.println(al);
		al.retainAll(al3);
		System.out.println(al);

		// size
		System.out.println(al.size());

		// deleting all elements
		al3.clear();
		System.out.println(al3.size());

		// check if element exists in list
		System.out.println(al.contains(3));

		// checking is list contains an list
		System.out.println(al.containsAll(al3));

		// iterator
		Iterator<String> it = al.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
		}

		System.out.println();

		// converting to array
		Object[] arr = al.toArray();
		for (Object object : arr) {
			System.out.print(object);
		}

		// checking if empty
		System.out.println(al.isEmpty());

		// hash code
		System.out.println(al.hashCode());

		System.out.println(al.equals(al2));

		al.forEach(el -> System.out.print(el + "*"));
		System.out.println(al);

		al.add("3");
		al.add("5");
		System.out.println(al);

		al.parallelStream().filter(e -> e == "2").forEach(e -> System.out.print(e));
		System.out.println();
		al.stream().filter(e -> e != "2").forEach(e -> System.out.print(e));
		System.out.println();
		al.stream().map(e -> e + "**").forEach(e -> System.out.print(e));
	}

}
