package FinalBlankVarDemo;

// a final blank variable is a final instance variable 
// that is not initialized when declared.
public class Main {
	
	private final int finalBlankVar1;
	private static final int finalBlankVar2;
	private final int finalBlankVar3;
	
	public Main(int value) {
    // final blank variable initialized in the constructor
        this.finalBlankVar3 = value;
    }
	
    // Instance Initialization Block
    {
        finalBlankVar1 = 488;
    }
    
    // Static Block Initialization
    static {
    	finalBlankVar2 = 100;
    } 
    
    
	public static void main(String[] args) {
		Main m = new Main(1234);
		System.out.println(m.finalBlankVar1);
		System.out.println(m.finalBlankVar2);
		System.out.println(m.finalBlankVar3);
	}

}
