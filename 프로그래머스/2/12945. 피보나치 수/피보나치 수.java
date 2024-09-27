class Solution {
    public long solution(int n) {
        int[] fibs = new int[n+1];
        fibs[0] = 0;
        fibs[1] = 1;
        int divider = 1234567;
        for (int i = 2; i <= n; i ++) {
            fibs[i] = (int) (fibs[i-1] + fibs[i-2]) % divider;
        }
        return fibs[n];
    }
}