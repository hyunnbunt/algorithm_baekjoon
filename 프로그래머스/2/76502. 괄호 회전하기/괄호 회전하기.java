import java.util.*;
class Solution {
    public int solution(String s) {
        // 최적화 : 홀수면 바로 0을 리턴
        if (s.length() % 2 == 1) {
            return 0;
        }
        ArrayList<Character> l = new ArrayList<>();
        for (int i = 0; i < s.length(); i ++) {
            l.add(s.charAt(i));
        }
        int count = 0;
        char[] left = new char[]{'(', '[', '{'};
        char[] right = new char[]{')', ']', '}'};
        for (int i = 0; i < s.length()-1; i ++) {
            Stack<Character> st = new Stack<>();
            boolean condition = true;
            loop:
           for (int lIdx = 0; lIdx < l.size(); lIdx++) {
                char curr = l.get(lIdx);
                for (int j = 0 ; j < left.length; j ++) {
                    if (left[j] == curr) { // 왼쪽 괄호? 스택에 넣을 것
                        st.push(curr);
                        break;
                    }
                    if(right[j] == curr) { // 오른쪽 괄호? 스택에서 뺀 것과 비교할 것
                        if (st.size() == 0 || left[j] != st.pop()) {
                            condition = false;
                        }
                        break;
                    }
                }
            }
            if (condition) {
                count ++;
            }
            l.add(l.remove(0));
        }
        return count;
    }
}