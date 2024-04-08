import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     m명에 대한 최소한의 입국심사 시간?
     m명이 n개의 입국심사대에 줄을 설 수 있다.
     모든 사람이 입국심사를 끝내는 총 시간은
     각 심사대별로 걸리는 시간 중 최대값이다.
     m명을 심사하는 데에 걸리는 최소의 시간을 h라고 가정한다면,
     모든 입국 심사 카운터는 h 시간 안에 최대한 많은 사람을 심사할 것이다.
     h 시간 안에 각 입국 심사 카운터의 심사 시간을 고려, 각각 몇 명의 사람을 심사 가능한지 계산할 것.
     모든 카운터의 심사 가능한 모든 사람의 숫자 총합이 m 이상인 최소값인 적절한 h를 찾을 것.
     h를 이분탐색한다 -> 숫자 총합이 m보다 크다면 left 값 조절, 작다면 right값 조절
     사람 수의 총합이 m과 같거나, left + 1 == right라면 이분 탐색을 중지.
     전자의 경우, h를 출력하고, left + 1 == right라면 h는 정수값이 아니므로, 조건을 만족하는 h는 right가 된다.
     (시간이 좀 더 걸리더라도 m 명의 사람을 모두 심사해야 하므로 h값을 올려 조정한다)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        long m = Integer.parseInt(nm[1]);
        long[] counters = new long[n];
        for (int i = 0; i < n; i ++) {
            counters[i] = Long.parseLong(br.readLine());
        }
        long left = 0;
        long right = Arrays.stream(counters).min().orElseThrow()* m;
        long result = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long people = 0;
            for (long c : counters) {
                people += mid / c;
            }
            if (people >= m) {
                result = mid;
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
}
