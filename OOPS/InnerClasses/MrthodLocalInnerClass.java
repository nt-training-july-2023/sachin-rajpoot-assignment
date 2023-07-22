package OOPS.InnerClasses;

// SO if we a inner class which can access the instance variables and methods of outer class 
// at the same time we can make the MLI class not accessable by making
// the method in which in its contained private.
public class MrthodLocalInnerClass {

    int x = 10;
    private int y = 20;
    protected int z = 30;
    public int a = 40;
    static int xx = 99;

    void fun() {
        System.out.println("parent class default method");
    }

    private void fun2() {
        System.out.println("parent 11111111111111 class private method");
    }

    protected void fun3() {
        System.out.println("parent class protected method");

    }

    private void methodContainsClass() {
        int b = 10;

        class ClassInsideMethod {
            // int x = 10;
            // private int y = 20;
            // protected int z = 30;
            // public int a = 40;

            void fun() {
                // can access all instance variables and methods
                System.out.println("ClassInsideMethod 1111 class default method");
                System.out.println(x);
                System.out.println(y);
                System.out.println(z);
                System.out.println(a);
                System.out.println(xx);
            }

            protected void fun3() {
                System.out.println("ClassInsideMethod class protected method");

            }
        }

        ClassInsideMethod cim = new ClassInsideMethod();
        cim.fun();
        // cim.fun2();
        cim.fun3();
    }

    public static void main(String[] args) {
        MrthodLocalInnerClass mlc = new MrthodLocalInnerClass();
        mlc.methodContainsClass();
    }
}

class ChildOfMethodLocalInnerClass extends MrthodLocalInnerClass {

    void fun() {
        // super.methodContainsClass(); // Child can not access the Method Local Inner
        // Class
        // bcoz it is private
    }

    public static void main(String[] args) {
        ChildOfMethodLocalInnerClass c = new ChildOfMethodLocalInnerClass();
        c.fun();
        System.out.println(MrthodLocalInnerClass.xx);

    }
}
