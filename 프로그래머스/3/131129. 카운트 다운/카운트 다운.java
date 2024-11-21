class Solution {
    /** 
    target 숫자를 만들기 위해서 여러 가지 숫자 조합을 더하는 경우 모두 확인
    같은 숫자를 여러 번 방문하게 됨 => memoization 필요! DP로 풀이
    */
    public int[] solution(int target) {
        Round[] dp = new Round[target+1]; // target 이하 모든 숫자에 대해서 최적의 답을 저장
        sol(dp, target);
        return new int[]{dp[target].round, dp[target].sb};
    }
    public void sol(Round[] dp, int k) { // dp[k]를 메모한다
        if (isValid(k)) { // k가 최적의 조건 (1라운드 만에 점수 합) 가능하다면
            dp[k] = new Round(1, singleOrBool(k));
            return;
        }
        for (int p = 1; p <= 60; p ++) {
            if (p >= k || !isValid(p)) { // k보다 작으면서 한 번에 도달 가능한 점수 p를 찾기
                continue;
            }
            int togo = k-p; // p를 맞추고 남은 점수
            if (dp[togo] == null) { 
                sol(dp, togo); 
            }
            Round next = new Round(dp[togo].round+1, dp[togo].sb+singleOrBool(p));
            if (dp[k] == null || next.isBetterThan(dp[k])) { // dp 배열에 최소 라운드 수로 메모 되어있더라도 현재 새로운 경로가 싱글 또는 불이면 업데이트
                dp[k] = next;
            }
        }
    }
    public class Round {
        int round;
        int sb;
        Round(int r, int sb) {
            this.round = r;
            this.sb = sb;
        }
        boolean isBetterThan(Round r) {
            if (this.round == r.round) {
                return this.sb > r.sb;
            }
            return this.round < r.round;
        }
    }
    public boolean isValid(int p) {
        if (p>=1 && p<=20) {
            return true;
        }
        if (p == 50) {
            return true;
        }
        if ((p%2==0 && p/2 <= 20) || (p%3==0 && p/3 <=20)) {
            return true;
        }
        return false;
    }
    public int singleOrBool(int p) {
        if (p>=1 && p<=20) {
            return 1;
        }
        if (p == 50) {
            return 1;
        }
        return 0;
    }
}