import java.lang.Math;
class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        for (long i=l; i<=r; i++) {
            answer += getNumAt(n,i-1);
        }
        return answer;
    }
    public static int getNumAt(int n, long i) {
        if (n==0) {
            return 1;   
        }
        long k = (long) Math.pow(5, n-1);
        if (i>=k*2&&i<k*3) {
            return 0;
        }
        return getNumAt(n-1, i%k);
    }
}