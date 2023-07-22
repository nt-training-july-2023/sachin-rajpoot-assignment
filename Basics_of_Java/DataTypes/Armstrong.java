package DataTypes;

import java.util.Scanner;

public class Armstrong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int tmp=n;
        int digits = 0;
        while(tmp != 0) {
            digits++; 
            tmp/=10;
        }

        tmp=n;
        int sum=0;
        while(tmp != 0) {
            int rem = tmp%10;
            sum += Math.pow(rem, digits);
            tmp/=10;
        }

        System.out.println(sum == n);
    }
}
