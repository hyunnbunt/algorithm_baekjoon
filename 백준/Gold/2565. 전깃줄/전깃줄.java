
import java.io.*;
import java.util.*;

public class Main {
    /**
     * 가장 긴 증가하는 부분수열 찾기
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i ++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(arr, Comparator.comparingInt(a->a[0]));
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = 0; i < n; i ++) {
            int curr = arr[i][1];
            for (int j = 0; j < i; j ++) {
                int prev = arr[j][1];
                if (prev<curr) {
                    ans[i] = Math.max(ans[i], ans[j]+1);
                }
            }
        }
        int maxLen = Arrays.stream(ans).max().orElseThrow();
        System.out.println(n-maxLen);
    }
}
