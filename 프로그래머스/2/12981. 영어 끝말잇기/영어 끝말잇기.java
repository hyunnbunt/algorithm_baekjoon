import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        Set<String> set = new HashSet<>();
        char prevEnd = words[0].charAt(words[0].length()-1);
        set.add(words[0]);
        for (int i = 1; i < words.length; i ++) {
            String word = words[i];
            if (set.contains(word) || prevEnd != word.charAt(0)) {
                return new int[]{ (i % n) + 1, (i / n) + 1};
            }
            set.add(word);
            prevEnd = word.charAt(word.length()-1);
        }
        return answer;
    }
}