import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        List<Integer> list = new ArrayList<>();
        long div = 1;
        for (int i = 1; i <= n; i ++) {
            list.add(i);
            div *= i;
        }
        k--;
        int[] answer = new int[n];
        int i = 0;
        while (n > 0) {
            div /= n;
            answer[i++] = list.get((int) (k / div));
            list.remove((int) (k / div));
            k %= div;
            n--;
        }
        return answer;
    }
}