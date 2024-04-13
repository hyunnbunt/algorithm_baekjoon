import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/**
 * 역순으로 정렬된 상태에서 가장 많은 원소를 선택?
 * 입력된 순서가 유지된 상태로, 수열을 골라야 한다.
 * 그러면 기존에 처리한 답을 가지고 활용해서 n이 커질 때 해결해보자.
 * 1) 첫 원소를 마주쳤을 때 : 가능한 감소수열 길이 최대는 1
 * 2) 두 번째 원소를 마주쳤을 때
 *      - 첫 번째 원소보다 크다 : 감소 수열에 추가 못 함
 *      - 첫 번쨰 원소보다 작다 : 감소 수열에 넣거나 안 넣는 두 가지 경우가 있다.
 *          넣으면 뒤에 숫자들은 현재 숫자보다 작아야 되고,
 *          안 넣으면 뒤의 숫자들은 첫 번째 원소보다만 작으면 된다.
 *          그러면, 두 가지 경우를 나눠서 계산?
 *          예를 들어서 수열이 100, 10, 99, 98, ..., 11, 1 이라면
 *          10을 선택하지 않는 것이 훨씬 더 긴 감소수열을 찾는 법이 된다.
 *          첫 번째 원소에 대응하는 정답 : 1;
 *          두 번째 원소에 대응하는 정답 : 첫 번째 원소보다 두 번째 원소가 작을 경우, 선택하거나 안 함.
 *          다 확인하고 가장 긴 것을 찾기?
 * DP 방법으로 풀이
 * 1) 감소한다
 * 2) 기존의 순서를 유지한다
 * 3) 특정 숫자가 선택되는지 여부에 따라 최대 수열의 길이가 달라진다.
 * 4) 입력 수열에서 0~특정 숫자 인덱스까지의 수열에 해당하는 정답을 구해놓는다
 * 5) 현재까지 정답 중에서 최대값 a
 * 6) 현재까지 정답 중에서 현재 숫자보다 큰 숫자가 선택되었을 때의 최대값 b
 * 7) a와 b+1 중 큰 것을 출력한다
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j < i; j ++) {
                if (arr[j]>arr[i]) {
                    ans[i] = Math.max(ans[i], ans[j] + 1); // 가장 긴 수열에 현재 숫자 추가 가능할 때
                }
            }
        }
//        for (int a : ans) {
//            System.out.print(a + " ");
//        }
//        System.out.println();
        int m = Arrays.stream(ans).max().orElse(0);
        System.out.println(m);
    }
}
