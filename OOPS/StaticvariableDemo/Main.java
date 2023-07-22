package StaticvariableDemo;


class ContainsStaticvar {
	// creating common shared variable among objects
	static int instanceStaticVar = 10;
	
	// creating common shared fixed constant variable among objects
	static final int staticFinalInstanceStaticVar = 10;
	
	
}
public class Main {

	
	public static void main(String[] args) {
		
		ContainsStaticvar obj1 = new ContainsStaticvar();
		ContainsStaticvar obj2 = new ContainsStaticvar();
		System.out.println("Accessing static var via object one -> "+obj1.instanceStaticVar);
		System.out.println("Accessing static var via object two -> "+obj2.instanceStaticVar);
		
		System.out.println("Changing value of static var via object one from -> "+obj1.instanceStaticVar + ""
				+ " to "+ ++obj1.instanceStaticVar);
		System.out.println("Value of static var via object two will also updated to -> "+obj2.instanceStaticVar);
		
		System.out.println("Accessing constant static var via object one -> "+obj1.staticFinalInstanceStaticVar);
		System.out.println("Accessing constant static var via object tw0 -> "+obj2.staticFinalInstanceStaticVar);
	}

}
