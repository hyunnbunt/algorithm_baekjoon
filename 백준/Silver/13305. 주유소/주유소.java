
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dist = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[][] cityPrice = new int[n-1][2];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n-1; i ++) {
            cityPrice[i] = new int[]{i, Integer.parseInt(st.nextToken())};
        }
        st.nextToken();
        boolean[] marked = new boolean[dist.length];
        long[] ans = new long[dist.length];
        Arrays.sort(cityPrice, Comparator.comparingInt(a -> a[1]));
        for (int[] cp : cityPrice) {
            int city = cp[0];
            int price = cp[1];
            int curr = city;
            while (curr<marked.length &&!marked[curr]) {
                ans[curr] += (long) price * dist[curr];
                marked[curr] = true;
                curr ++;
            }
        }
        System.out.println(Arrays.stream(ans).sum());
    }
}
