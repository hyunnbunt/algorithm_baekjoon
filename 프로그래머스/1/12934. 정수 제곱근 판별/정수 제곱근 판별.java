import java.util.*;
class Solution {
    public long solution(long n) {
        int sqrt = (int) Math.sqrt(n);
        long pow2OfSqrt = (long) Math.pow(sqrt, 2);
        if (pow2OfSqrt == n) {
            return n + (sqrt * 2) + 1;
        }
        return -1;
    }
}