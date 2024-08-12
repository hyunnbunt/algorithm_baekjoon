import java.util.Arrays;
import java.lang.Math;
class Solution {
    public int solution(int number, int limit, int power) {
        // 범위 내 약수의 개수? 에라토스테네스의 체
        int count = 0;
        for (int i = 1; i <= number; i ++) {
            int divider = 0;
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    divider += 2;
                }
                if (j == Math.sqrt(i)) {
                    divider --;
                } 
            }
            if (divider <= limit) {
                count += divider;
            } else {
                count += power;
            }
        }
        return count;
    }
}