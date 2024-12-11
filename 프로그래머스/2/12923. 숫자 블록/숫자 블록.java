class Solution {
    public int[] solution(long begin, long end) {
        // begin~end 구간 가장 큰 약수
        int len = (int) (end-begin+1);
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) { // begin+i의 약수 -> 배열[i]의 원소
            answer[i] = getTileNumber(begin+i);
        }
        return answer;
    }
    public int getTileNumber(long n) {
        if (n == 1) {
            return 0;
        }
        int max = 1;
        for (int i = 2; i <= Math.sqrt(n); i ++) {
            if (n % i == 0) {
                int j = (int) n / i;
                if (j <= 10000000) {
                    return j;
                } else {
                    max = Math.max(i, max);
                }
            }
        }
        return max;
    }
}