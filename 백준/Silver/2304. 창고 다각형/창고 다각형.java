import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int b = 0; // broadth
        int[] ctp = new int[n]; // closest taller pillars
        Arrays.fill(ctp, -1);
        int[][] pillars = new int[n][2];
        for (int i = 0; i < n; i ++) {
            pillars[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(pillars, Comparator.comparingInt(p -> p[0]));
        for (int i = 0; i < n; i ++) {
            if (i == 0) {
                continue;
            }
            //1) left = i-1, left 기둥이 i 기둥보다 높거나 같으면, left을 ctp[i]에 저장
            //2) left 기둥이 i 기둥보다 낮을 경우 아래와 같이 나뉜다
            // 2-1) ctp[left] == -1일 경우 => ctp[i] = -1
            // 2-2) ctp[left] != -1일 경우 => ctp[left]을 새로운 left로 설정, 1부터 반복
            int left = i - 1;
            while (left >= 0) {
                if (pillars[left][1] >= pillars[i][1]) {
                    ctp[i] = left;
                    break;
                } else if (ctp[left] == -1) {
                    ctp[i] = -1;
                    break;
                } else {
                    left = ctp[left];
                }
            }
        }
        int i = n-1;
        while (true) {
            // i 기둥 위치 시작점 +1부터 ctp[i] 기둥 위치 시작점까지 너비 * i 기둥 높이 -> i = ctp[i]
            // ctp[i] == -1이라면, 1 * i기둥 높이 더한 후 다음 수행
            // i 왼쪽의 모든 기둥 중에서 가장 높은 기둥 높이를 찾기
            // 가장 높은 기둥 tp의 위치 시작점부터 i 기둥 시작점까지 너비 * tp 높이 -> i = tp
            b += pillars[i][1];
            if (i == 0) {
                break;
            }
            int left = ctp[i];
            if (left != -1) {
                b += (pillars[i][0] - pillars[left][0]-1) * pillars[i][1];
                i = left;
            } else {
                int max = 0;
                int lt = -1; // 왼편 가장 높은 기둥의 인덱스
                for (int j = i-1; j >= 0; j --) {
                    int tempHeight = pillars[j][1];
                    if (tempHeight >= max) {
                        max = tempHeight;
                        lt = j;
                    }
                }
                b += (pillars[i][0]-pillars[lt][0]-1) * max;
                i = lt;
            }
        }
        System.out.println(b);
    }
}
