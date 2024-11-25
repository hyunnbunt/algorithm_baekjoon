import java.util.Arrays;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0; 
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }
        if (alp >= maxAlp && cop >= maxCop) { 
            return 0;
        } 
        // 위 조건문 하나만 만족하는 경우 dp[alp][cop] = 0; 구문에서 에러가 발생.
        alp = Math.min(alp, maxAlp); // alp는 maxAlp보다 클 수 없음
        cop = Math.min(cop, maxCop); // cop는 maxCop보다 클 수 없음
        int[][] dp = new int[maxAlp+2][maxCop+2]; 
        for (int[] dpArr : dp) {
            Arrays.fill(dpArr, Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;
        // 1씩 올리는 경우 & 각 문제를 풀었을 경우
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                dp[a+1][c] = Math.min(dp[a+1][c], dp[a][c]+1);
                dp[a][c+1] = Math.min(dp[a][c+1], dp[a][c]+1);
                for (int[] p : problems) {
                    if (a >= p[0] && c >= p[1]) {
                        int nextA = Math.min(a+p[2], maxAlp);
                        int nextC = Math.min(c+p[3], maxCop);
                        dp[nextA][nextC] = Math.min(dp[nextA][nextC], dp[a][c]+p[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}