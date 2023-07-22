package OOPS.InnerClasses;

// Here we are extending from outer class of static inner class and accessing it
// via child of outer class
class chkChildClassAccess extends StaticInnerClass {
    void chkingAccess() {
        System.out.println("Inside child of outer class method");
        StaticInnerClass.InnerClass in = new StaticInnerClass.InnerClass();
        in.fun();
        StaticInnerClass.InnerClass.fun2();
    }
}

// Here we are extending from static inner class and accessing it
class ChildOfStaticInnerClass extends StaticInnerClass.InnerClass {
    void fun() {
        super.fun();
    }
}

// Here we are accessing static inner class via third unrelated class
class UnrelatedClass {
    void accessStaticInnerClass() {
        System.out.println("Inside unrelated third class method");
        StaticInnerClass.InnerClass in = new StaticInnerClass.InnerClass();
        in.fun();
        StaticInnerClass.InnerClass.fun2();

    }
}

public class StaticInnerClass {
    int a = 1;
    private static int b = 2;

    public void fun() {
        System.out.println(a + " inside static outer class");
    }

    static class InnerClass {
        int a = 2;

        void fun() {
            System.out.println(a + " inside static inner class");
            System.out.println(b + " inside static inner class");
        }

        static {
            System.out.println("hhhhhhhhhh");
        }

        static void fun2() {
            System.out.println("static method of static inner class");
        }
    }

    public static void main(String[] args) {
        InnerClass in = new InnerClass();
        in.fun();

        StaticInnerClass.InnerClass.fun2();

        chkChildClassAccess ch = new chkChildClassAccess();
        ch.chkingAccess();

        UnrelatedClass un = new UnrelatedClass();
        un.accessStaticInnerClass();
    }
}
