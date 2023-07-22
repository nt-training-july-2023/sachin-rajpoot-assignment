package OOPS;

public class Polymorphism {

    void add() {
        System.out.println("parent class");
    }

    static int add(int a, int b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    static double add(double a, double b) {
        return a + b;
    }

    static float add(float a, float b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(add(1, 2));
        System.out.println(add(1, 2, 3));
        System.out.println(add(2, 34.5));
        System.out.println(add(234f, 786.4f));

    }
}

 class Child extends Polymorphism {
    void add() {
        System.out.println("child class");
    }
}

class ChkOverriding {
    public static void main(String[] args) {
        Polymorphism pm = new Polymorphism();
        Child ch = new Child();

        pm.add();
        ch.add();

        Polymorphism pm2 = new Child();
        pm2.add();

    }
}