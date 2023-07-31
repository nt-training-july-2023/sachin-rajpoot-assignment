package StringManipulation;

interface InterfaceClass {
	public abstract void fun(String str);
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfaceClass ic = (str) -> {
			String str2 = "";
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o'
						|| str.charAt(i) == 'u') {
					str2 += '#';
				} else {
					str2 += str.charAt(i);
				}
			}
			System.out.println(str2);
		};

		ic.fun("Ninja");
	}

}
