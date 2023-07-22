package Arrays;

public class Average {
    public static void main(String[] args) {
        int[] arr = {22,33,5,5,645,6,5,64,5,54,5,5,45,4};
        int sum=0;
        for(int x : arr) {
            sum+=x;
        }
        System.out.println(sum/arr.length);
    }
}
