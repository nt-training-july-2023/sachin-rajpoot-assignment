package String;

public class DiffEquals {
    public static void main(String[] args) {
        String s1 = "aa";
        String s2 = "aa";
        String s3 = new String("aa");

        System.out.println(s1==s3);
        System.out.println(s1.equals(s2));
        System.out.println(s3.equals(s1));
    }
} 
