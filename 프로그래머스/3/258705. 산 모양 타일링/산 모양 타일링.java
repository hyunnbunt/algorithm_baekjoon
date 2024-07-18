class Solution {
    public int solution(int n, int[] tops) {
        int[] a = new int[n+1];
        int[] b = new int[n+1];
        a[0] = 1;
        b[0] = 1;
        for (int i = 1; i < n+1; i ++) {
            if (tops[i-1] == 1) {
                a[i] = (a[i-1] * 3 + b[i-1]) % 10007;
                b[i] = (a[i-1] * 2 + b[i-1]) % 10007 ;
            } else {
                a[i] = (a[i-1] * 2 + b[i-1]) % 10007;
                b[i] = (a[i-1] + b[i-1]) % 10007;
            }
        }
        return a[n];
    }
}