package OOPS.InnerClasses;

interface Shape{
    void draw();
}
public class OverridingAnonymousClass {
    public static void main(String[] args) {
        
        Shape circle = new Shape() {
            public void draw(){
                System.out.println("drawing circle");
            }
        };

        Shape triangle = new Shape() {
            public void draw(){
                System.out.println("drawing triangle");
            }
        };

        Shape square = new Shape() {
            public void draw(){
                System.out.println("drawing square");
            }
        };

        circle.draw();
        triangle.draw();
        square.draw();
    }
}
