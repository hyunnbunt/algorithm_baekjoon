import java.util.*;
class Solution {
    public int[] solution(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        int len = s.length();
        int[] answer = new int[len];
        for (int i = 0; i < len; i ++) {
            char curr = s.charAt(i);
            if (charMap.containsKey(curr)) {
                answer[i] = i-charMap.get(curr);
            } else {
                answer[i] = -1;
            }
            charMap.put(curr, i);
        }
        return answer;
    }
}