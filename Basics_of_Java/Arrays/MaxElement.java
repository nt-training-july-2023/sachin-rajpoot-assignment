package Arrays;

public class MaxElement {
    public static void main(String[] args) {
        int max = Integer.MIN_VALUE;
        int[] arr = {11,22,33,55,3,3,2,3,4,4,3,3,3,4,43};

        for(int x : arr) {
            if(x > max) max=x;
        }
        System.out.println(max);
    }
}
