class Solution {
    public int solution(String s) {
        int answer = 0;
        while (s.length()>0) { //6, 4, 2
            char start = s.charAt(0); //'b', 'n', 'n'
            int numOfStart = 1;
            int numOfOthers = 0;
            int i = 1;
            while (numOfStart!=numOfOthers && i < s.length()) {
                if (s.charAt(i)==start) { 
                    numOfStart++;
                } else { //'a', 'a', 'a'
                    numOfOthers++; // 1 (1, 1), 1 (1, 1), 1 (1, 1)
                }
                i++; // 2, 2, 2
            }
            if (i>=s.length()) {
                s = "";
            } else {
                s = s.substring(i); // 'nana', 'na'
            }
            answer++;
        }
        return answer;
    }
}