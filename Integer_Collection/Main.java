package Integer_Collection;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=1; i<=20; i++) al.add(i);
		System.out.println("elements in AL -> "+al);
		
		int length = al.size();
		int i=0;
		System.out.print("reverse order -> ");
		while(length != 0) {
			System.out.print(al.get(al.size()-1-i)+" ");
			length--; i++;
		}
		System.out.println();
		for(int i1=0; i1<al.size(); i1++) {
			if(al.get(i1) < 60)
				System.out.print(al.get(i1)+" ");
		}
		
	}

}
