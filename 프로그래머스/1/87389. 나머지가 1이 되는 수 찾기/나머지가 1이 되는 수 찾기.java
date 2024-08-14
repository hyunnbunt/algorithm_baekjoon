class Solution {
    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i ++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public int solution(int n) {
        /**  n-1이 소수라면 리턴
        아니라면, n-1 약수 중 1이 아닌 가장 작은 숫자가 x
        소수인 것을 어떻게 알지?
        소수 : 약수가 1과 자기 자신 뿐
        숫자 n에 대해, 2부터 sqrt(n)까지 순회하며
        n을 나누어 떨어지는 수가 있는지 확인하고 없다면 소수
        소수 여부를 알아내는 간단한 수식이 있었던 것 같은데... 기억 안 남.
        */
        if (isPrime(n - 1)) {
            return n - 1;
        } 
        for (int i = 2; i <= Math.sqrt(n-1); i ++) {
            if ((n - 1) % i == 0) {
                return i;
            }
        }
        return -1;
    }
}