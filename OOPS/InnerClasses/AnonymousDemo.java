package OOPS.InnerClasses;

interface AnonymousInterfacDemo {
    void fun();
}

class AnonymousClassDemo {
    void fun(){   };
}

class ChkOverride{
    void fun(){
        System.out.println("ChkOverride");
    }
}
public class AnonymousDemo {
    public static void main(String[] args) {
        
        // Here Anonymous class is used to override
        // Interface method 
        AnonymousInterfacDemo an = new AnonymousInterfacDemo() {
           public void fun(){
                System.out.println("Anonymous Inner class");
            }

            public void fun(int x){
                System.out.println("Anonymous"+ x+ " Inner class");
            }
        };
        an.fun();

        // Here I can override method of class
        AnonymousClassDemo acd = new AnonymousClassDemo(){
            public void fun() {
                System.out.println("ccccccccccc");
            }
        };
        acd.fun();
        // an.fun(2); 
        // instead of creating a new class we've overriddden
        // the method while creating object of interface
      

        ChkOverride chk = new ChkOverride(){
            void fun(){
                System.out.println("Over");
            }
        };
        chk.fun();
    }
}

