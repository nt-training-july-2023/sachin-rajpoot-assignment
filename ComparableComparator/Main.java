package ComparableComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Person implements Comparable<Person>{
	
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age=age;
	}
	
	
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.age - o.age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
}

class NameCompare implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}
	
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Person> al = new ArrayList<Person>();
		
		
		al.add(new Person("Naruto", 11));
		al.add(new Person("Goku", 19));
		al.add(new Person("Sasuke", 13));
		al.add(new Person("Light", 1));
		
		System.out.println("Sort by age");
		Collections.sort(al);
		for(Person p : al) {
			System.out.println(p.getName()+"   "+p.getAge());
		}
		
		System.out.println("Sort by name");
		NameCompare nc = new NameCompare();
		Collections.sort(al, nc);
		for(Person p : al) {
			System.out.println(p.getName()+"   "+p.getAge());
		}

	}

}
