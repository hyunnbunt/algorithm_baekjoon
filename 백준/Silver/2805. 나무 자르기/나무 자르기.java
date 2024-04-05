import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

/**
 *  절단기 높이 h 라고 할 때 h의 최대값
 *  h는 범위 내에서 점점 높아지고,
 *  이때 주어진 조건을 만족하지 않는 h까지 도달하면
 *  바로 직전의 h를 출력해야 한다.
 *  n개의 나무 각각의 높이를 nh라고 할 때,
 *  nh - h가 상근이가 얻게 되는 나무의 길이 일부가 되고
 *  모든 n개의 나무를 모두 순회하며 nh - h의 총합을 구하면
 *  상근이가 가지고 가는 나무 길이의 총합이 된다
 *  이 길이는 m만 넘으면 되고, 불필요하게 많이 가져가지 않을 것.
 *  절단기 h에 대해 n개의 나무 각각에 대해 nh - h만큼을 구하고
 *  그 총합이 m을 넘으면 바로 h를 증가시키지 않고 출력한다.
 *  이때 h의 적정 높이는 바이너리 서치 한다.
 *  h는 항상 start와 end의 중간값이며, h가 기대값보다 크다면
 *  낮춰야하므로, end = h가 된다.
 *  반례 :
 *  5 2
 *  1 1 0 0 0
 *  -> 0
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = 0;
        int end = Arrays.stream(trees).max().orElseThrow();
        int h = 0;
        while (start < end) {
            h = (start + end) / 2;
            if (start + 1 == end) {
                break;
            }
            long sum = 0;
            for (int i = 0; i < n; i ++) {
                int nh = trees[i] - h;
                if (nh > 0) {
                    sum += nh;
                }
            }
            if (sum == m) {
                break;
            } else if (sum < m) {
                end = h;
            } else {
                start = h;
            }
        }
        System.out.println(h);

    }
}