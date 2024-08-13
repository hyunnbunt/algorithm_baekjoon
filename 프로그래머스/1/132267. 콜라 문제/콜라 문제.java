class Solution {
    public int solution(int a, int b, int n) {
        int count = 0;
        while (n >= a) {
            int newBottle = (n / a) * b;
            count += newBottle;
            n = newBottle + (n % a);
        }
        return count;
    }
}