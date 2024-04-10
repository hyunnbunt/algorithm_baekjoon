import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int count;
    static int sum;
    static int[] nums;
    static int n;
    static int s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ns = br.readLine().split(" ");
        n = Integer.parseInt(ns[0]);
        s = Integer.parseInt(ns[1]);
        nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        sum = 0;
        count = 0;
        f(0);
        System.out.println(count);
    }
    public static void f(int k) {
        for (int i = k; i < n; i ++) {
            sum += nums[i];
            if (sum == s) {
                count ++;
            }
            f(i+1);
            sum -= nums[i];
        }
    }
}
