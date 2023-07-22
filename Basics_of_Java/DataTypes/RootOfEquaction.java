package DataTypes;
import java.util.Scanner;

public class RootOfEquaction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        double sq = Math.sqrt((b*b) - (4*a*c));

        double positiveValue = (-b + sq)/ (2*a);
        double negetiveValue = (-b - sq)/ (2*a);

        System.out.println(positiveValue);
        System.out.println(negetiveValue);
        

    }
}
