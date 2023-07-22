package OOPS.InnerClasses;

public class RegularInnerClass {


    int x = 10;
    private int y = 20;
    protected int z = 30;
    public int a = 40;

    void fun() {
        System.out.println("parent class default method");
    }

   private void fun2() {
        System.out.println("parent class private method");
    }

   protected void fun3() {
        System.out.println("parent class protected method");
        
    }
    

    class ChildClass {   
        int b = 5;
         void fun4() {
            System.out.println(x);
            System.out.println(y);
            System.out.println(z);
            System.out.println(a);
            System.out.println("inner class method");
        }
    }
    public static void main(String[] args) {
        
        // ChildClass ch = new ChildClass();
        // ch.fun4();
    }
}

class ChkInnerClass {
    public static void main(String[] args) {
        
        RegularInnerClass rig = new RegularInnerClass();
        rig.fun();
        rig.fun3();

        
        RegularInnerClass.ChildClass ch = rig.new ChildClass();
        ch.fun4();

    }
}