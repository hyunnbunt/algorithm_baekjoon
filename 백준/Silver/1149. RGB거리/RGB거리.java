
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     * 각 집에서 각 색깔을 칠할 경우의 최저 비용을 저장한다.
     * 현재 색은 바로 전 집의 색깔의 영향을 받는다.
     * (g, r), (b, r) => 현재 r -> 둘 중 작은 것 저장
     * (g, b), (r, b) => 현재 b -> 둘 중 작은 것 저장
     * (b, g), (r, g) => 현재 g -> 둘 중 작은 것 저장
     * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] rgbCost = new int[n][3];
        for (int i = 0 ; i < n; i ++) {
            int[] curr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            if (i == 0) {
                rgbCost[0] = curr;
            } else {
                int[] prev = rgbCost[i-1];
                rgbCost[i][0] = Math.min(prev[1], prev[2])+curr[0];
                rgbCost[i][1] = Math.min(prev[0], prev[2])+curr[1];
                rgbCost[i][2] = Math.min(prev[0], prev[1])+curr[2];

            }
        }
        System.out.println(Arrays.stream(rgbCost[n-1]).min().orElseThrow());
    }
}
