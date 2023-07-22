package Arrays;

public class AddMatrix {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] arr2 = {{1,2,3}, {4,5,6}, {7,8,9}};

        int[][] ans = new int[arr.length][arr.length];
        for(int i=0; i<arr.length;i++) {
            for(int j=0; j<arr.length; j++) {
                ans[i][j] = arr[i][j] + arr2[i][j];
            }
        }

        for(int i=0; i<arr.length;i++) {
            for(int j=0; j<arr.length; j++) {
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }


    }
}
