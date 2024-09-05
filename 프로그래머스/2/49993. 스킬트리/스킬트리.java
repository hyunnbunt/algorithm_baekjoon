import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        Set<Character> set = new HashSet<>();
        for (char c : skill.toCharArray()) {
            set.add(c);
        }
        int answer = 0;
        for (String st : skill_trees) {
            int curr = 0;
            boolean good_st = true;
            for (char s : st.toCharArray()) { // 현재 스킬
                if (!set.contains(s)) {
                    continue;
                }
                if (curr >= skill.length() || skill.charAt(curr) != s) { // 이미 모든 스킬 순서대로 나왔거나, 현재 스킬 순서 아닐 때
                    good_st = false;
                    break;
                } else {
                    curr++;
                }
            }
            if (good_st) {
                answer ++;
            }
        }
        return answer;
    }
}