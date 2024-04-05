import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

/**
 *  절단기 높이 h 라고 할 때 h의 최대값
 *  n개의 나무 각각의 높이를 nh라고 할 때,
 *  모든 nh에 대해서 nh - h를 구한 총합 sum을 구하면
 *  상근이가 가지고 갈 수 있는 나무 길이가 된다
 *  이 길이는 m만 넘으면 되고, 불필요하게 많이 가져가지 않아야 하므로
 *  sum이 정확히 m이 될 때의 h가 정수가 아닐 때, 내림 한다. 
 *  -> 더 낮은 높이에서 나무를 절단해야 m 이상을 가져갈 수 있다.
 *  이때 h의 적정 높이는 바이너리 서치 한다.
 *  h는 항상 현재 확인하고 있는 범위의 중간값으로 설정하고, h가 기대값보다 크다면
 *  범위를 오른쪽으로 조정, 반대의 경우 범위를 왼쪽으로 조정. start, end를 조정한다.
 *  start와 end가 1 차이 날 때에는 둘 중에 하나가 최적의 정수 h라는 뜻이므로
 *  둘 중에 작은 start를 출력한다.
 *  sum => n 최대값 100만, nh 최대값 10억  => 100만 * 10억 = 16자리 (10진수)가 최대값 long은 19자리까지 커버 가능.
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
            if (start + 1 == end) {
                h = start;
                break;
            }
            h = (start + end) / 2;
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