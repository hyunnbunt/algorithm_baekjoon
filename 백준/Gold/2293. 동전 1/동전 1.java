
import java.io.BufferedReader;
import java.io.*;
import java.util.*;

/**
 * 동전 종류를 하나씩만 늘린다.
 * 동전 종류가 하나 적을 때 가능한 경우의 수를 이용한다.
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nk = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = nk[0];
        int k = nk[1];
        int[] coins = new int[n];
        for (int i = 0 ;i < n;i ++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int[] ans = new int[k+1];
        int c = coins[0];
        for (int a = 0; a <= k; a ++) {
            if (a % c == 0) {
                ans[a] = 1;
            }
        }
        ans[0] = 1;
        for (int j = 1; j < n; j ++) { // 동전 종류마다
            int coin = coins[j];
            int[] curr = new int[k+1];
            for (int i = 0; i <= k; i ++) { // 1원~k원까지 각각 n가지 동전 조합의 경우의 수 구하기
                int a = 0;
                while (coin*a<=i) {
                    curr[i] += ans[i-(coin*a)];
                    a++;
                }
            }
            ans = curr;
        }
        System.out.println(ans[k]);
    }
}
