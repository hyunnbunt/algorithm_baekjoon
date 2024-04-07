
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main{
    /**
     1) 산술평균 : sum / count -> 입력받으면서 sum계산 가능, count는 arr.length (입력, n)
     2) 모든 숫자 (순서 유지) 중 가운데 인덱스 -> array, 가운데 인덱스는 n + 1 / 2 (입력, n)
     3) Map에 개수 저장 후 가장 큰 것 -> 입력받으면서 map에 저장, getOrDefault (입력 * getOrDefault, nlogn)
     4) 최소, 최대 -> 입력 받으면서 저장
     가장 큰 것 3), nlogn
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        int min = 4001;
        int max = -4001;
        int maxCount = 0;
        int[] nums = new int[n];
        int[] popularity = new int[8001]; // i - 4000 : -4000, ..., 4000
        for (int i = 0; i < n; i ++) {
            int temp = Integer.parseInt(br.readLine());
            sum += temp;
            min = Math.min(min, temp);
            max = Math.max(max, temp);
            nums[i] = temp;
            popularity[temp + 4000] ++;
            if (popularity[temp + 4000] > maxCount) {
                maxCount = popularity[temp + 4000];
            }
        }
        Arrays.sort(nums);
        boolean first = false;
        int popular = -4001;
        for (int i = 0; i < popularity.length; i ++) {
            if (popularity[i] == maxCount) {
                popular = i - 4000;
                if (first) {
                    break;
                } else {
                    first = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Math.round((double) sum / n)).append("\n");
        sb.append(nums[(n + 1) / 2 - 1]).append("\n");
        sb.append(popular).append("\n");
        sb.append(max - min).append("\n");
        System.out.println(sb);
    }
}
