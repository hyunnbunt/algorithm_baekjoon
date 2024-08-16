import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] cleared = new int[N+1]; // 인덱스 -> 값 : 스테이지 넘버 -> 깬 사람수
        int[] reached = new int[N+1]; // 인덱스 -> 값 : 스테이지 넘버 -> 도달한 사람수
        for (int s : stages) { // 사용자별 도달한 최고 스테이지 넘버
            for (int lower = 1; lower < s; lower++) { // 이전 스테이지까지는 모두 도달+깼다
                cleared[lower]++; 
                reached[lower]++;
            }
            if (s <= N) { // 현 스테이지가 N보다 크면 전부 다 깼다는 뜻. <-> 아니라면 현재 스테이지에서 멈췄다는 뜻
                reached[s]++;
            }
        }
        int[][] failureByStage = new int[N][3]; // 각 스테이지마다 {스테이지 넘버, 실패한 사람 수, 도달한 사람 수}
        for (int i = 0; i < N; i ++) {
            failureByStage[i] = new int[]{i+1, reached[i+1]-cleared[i+1], reached[i+1]};
        }
        Arrays.sort(failureByStage, (o1, o2) -> {
            double failureRate1 = 0;
            if (o1[2] > 0) {
                failureRate1 = (double) o1[1] / o1[2]; // int division => 몫을 리턴, 숫자를 하나라도 double로 바꾼 후 연산해야 함
            }
            double failureRate2 = 0;
            if (o2[2] > 0) {
                failureRate2 = (double) o2[1] / o2[2];
            }
            double subs = failureRate2 - failureRate1;
            if (subs == 0) {
                return o1[0] - o2[0];
            }
            return (subs > 0) ? 1 : -1;
        });
        int[] answer = new int[N];
        for (int i = 0; i < N; i ++) {
            answer[i] = failureByStage[i][0];
        }
        return answer;
    }
}