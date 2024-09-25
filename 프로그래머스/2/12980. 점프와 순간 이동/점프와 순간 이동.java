import java.util.*;
public class Solution {
    public int solution(int n) {
        int ans = 0;
        while (n >= 2) {
            if (n % 2 == 1) {
                n--;
                ans++;
            }
            while (n % 2 == 0) {
                n /= 2;
            }
        }
        return ans + n;
    }
}