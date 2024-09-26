class Solution {
    public static int gcd(int a, int b) {
        int r = a % b;
        if (r == 0) {
            return b;
        }
        return gcd(b, r);
    }
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    public int solution(int[] arr) {
        int answer = arr[0];
        for (int i = 1; i < arr.length; i ++) {
            answer = lcm(answer, arr[i]);
        }
        return answer;
    }
}