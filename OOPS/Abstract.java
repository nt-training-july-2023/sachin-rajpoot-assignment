package OOPS;

// Why Use Abstract Class ?

// to provide common method implementation to all 
// the subclasses or to provide default implementation
abstract class AbstractParent {
    void fun(){
        System.out.println("Inside Abstract Class");
    }
    
   abstract public void fun2(); // must be implemented on child class
}

class Child1OfAbstract extends AbstractParent {
   public void fun2() {
        System.out.println(" function 2 of Child of Abstract class");
    }

    void fun3() {
        super.fun();
    }

}

class Child2OfAbstract extends AbstractParent {
   public void fun2() {
        System.out.println(" function 2 of Child of Abstract class");
    }

    void fun3() {
        super.fun();
    }

}
public class Abstract {
    public static void main(String[] args) {
        Child1OfAbstract ch1 = new Child1OfAbstract();
        ch1.fun2(); ch1.fun3();

        Child2OfAbstract ch2 = new Child2OfAbstract();
        ch2.fun2(); ch1.fun3();
    }
}
