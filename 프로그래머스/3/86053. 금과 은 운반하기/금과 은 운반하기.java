class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long l = 0;
        long r = 4 * (long) Math.pow(10, 14);
        int c = g.length;
        while (l <= r) {
            long T = (l + r) / 2; // 걸리는 시간 -> 이 안에 운반 가능하면 시간 줄이기, 운반 불가능하면 시간 늘리기
            long G = 0;
            long S = 0;
            long W = 0;
            for (int i = 0; i < c; i ++) {
                if (T < t[i]) {
                    continue;
                }
                long maxW = w[i] + ((T - t[i]) / (t[i] * 2)) * w[i]; // T시간 안에 옮길 수 있는 중량
                G += Math.min(g[i], maxW);
                S += Math.min(s[i], maxW);
                W += Math.min(g[i] + s[i], maxW);
            }
            
            if (G >= a && S >= b && W >=(a + b)) { // 운반 가능, 최소값인지 모르지 시간 줄이기
                r = T - 1;
            } else { // 운반 불가능, 시간 늘리기
                l = T + 1;
            }
        }
        return l;
    }
}