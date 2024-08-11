class Solution {
    public int solution(String s) {
        int answer = 0;
        int substringStart = 0;
        while (substringStart < s.length()) { //6, 4, 2
            char start = s.charAt(substringStart); //'b', 'n', 'n'
            int numOfStart = 1;
            int numOfOthers = 0;
            int i = substringStart+1;
            while (numOfStart!=numOfOthers && i < s.length()) {
                if (s.charAt(i)==start) { 
                    numOfStart++;
                } else { //'a', 'a', 'a'
                    numOfOthers++; // 1 (1, 1), 1 (1, 1), 1 (1, 1)
                }
                i++; // 2, 2, 2
            }
            if (i>=s.length()) {
                substringStart = s.length();
            } else {
                substringStart = i; // 'nana', 'na'
            }
            answer++;
        }
        return answer;
    }
}