package FinalClassDemo;


// cannot extends from final class
public class Main  {

	public static void main(String[] args) {
		
		FinalClassDemo1 fm = new FinalClassDemo1();
		System.out.println(fm.var1);
		System.out.println(FinalClassDemo1.var2);
		fm.fun1();
		System.out.println(FinalClassDemo1.var2);
		FinalClassDemo1.fun2();

	}

}
