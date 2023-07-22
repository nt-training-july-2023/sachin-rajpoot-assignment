package OOPS;
interface b1 {
    void fun();
}

interface b2 {
    void fun2();
}

public class MultipleInheritance implements b1, b2 {

    public void fun() {
        System.out.println("Overridden method of Parent one");
    }

    public void fun2() {
         System.out.println("Overridden method of Parent two");
    }
    public static void main(String[] args) {
        MultipleInheritance mi = new MultipleInheritance();
        mi.fun();
        mi.fun2();
    }
}
