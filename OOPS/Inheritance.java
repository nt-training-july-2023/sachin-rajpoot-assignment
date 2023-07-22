package OOPS;

class Parent1 {
    void fun() {
        System.out.println("Parent 1");
    }
}

// SIngle Inheritance
class Child1 extends Parent1 {
    void fun() {
        System.out.println("Single Inheritance");
    }
}

// Multi-level inheritance
class GrandChild1 extends Child1 {
    void fun() {
        System.out.println("multi-level inheritance");
    }
}

// Hierarchical inheritance
class Parent2 {
    void fun() {
        System.out.println("Paremt 2");
    }
}

class Child2 extends Parent2 {
    void fun() {
        System.out.println("Child one of Parent 2");
    }
}

class Child3 extends Parent2 {
    void fun() {
        System.out.println("Child two of Parent 2");
    }
}


class Parent3 {
    void fun(){
        System.out.println("Parent 3");
    }
}

class Child4 extends Parent3 {
    void fun() {
        System.out.println("Single Child of Parent 3");
    }
}

class GrandChild2 extends Child4 {
    void fun() {
        System.out.println("Child one of Child 4");
    }
}

class GrandChild3 extends Child4 {
    void fun() {
        System.out.println("Child one of Child 4");
    }
}


// Multiple Inheritance

interface Parent4 {
    void fun();
}

interface Parent5 {
    void fun2();
}

class Child5 implements Parent4, Parent5{
   public void fun() {
        System.out.println("Child of Parent 4 and Parent 5");
    }

    public void fun2() {
        System.out.println("");
    }
}
public class Inheritance {
    public static void main(String[] args) {
        Parent1 p1 = new Parent1();
        p1.fun();

        Child1 ch1 = new Child1();
        ch1.fun();

        GrandChild1 gc1 = new GrandChild1();
        gc1.fun();

        Parent2 p2 = new Parent2();
        p2.fun();

        Child2 ch2 = new Child2();
        ch2.fun();

        Child3 ch3 = new Child3();
        ch3.fun();

        Parent3 p3 = new Parent3();
        p3.fun();

        Child4 ch4 = new Child4();
        ch4.fun();

        GrandChild2 gc2 = new GrandChild2();
        gc2.fun();

        GrandChild3 gc3 = new GrandChild3();
        gc3.fun();

        Child5 ch5 = new Child5();
        ch5.fun();

    }
}
