import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 빨리 뽑을 수 있는 사람 먼저 줄을 서면 전체의 기다리는 시간 합이 줄어든다
 * 빠른 순서의 사람이 돈을 뽑는 시간의 가중치가 크다
 * 자신의 돈 뽑는 시간 * 자기 자신 포함 기다리는 사람 수 = 전체 그룹에 소요시키는 시간
 * P 최대 1000, 사람의 수 최대 1000 
 * sum의 최대값 : 1000 * (1000 + 999 + ... + 1) => 1000 * 1000 * 1001 / 2 => 5억 정도
 * int 커버 범위 21억(10자리), long은 2^64(19자리)
 * int만 사용해도 됨.
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] times = new int[n];
        for (int i = 0; i < n; i ++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times);
        int sum = 0;
        for (int i = 0; i < n; i ++) {
            sum += times[i] * (n - i);
        }
        System.out.println(sum);
    }
}