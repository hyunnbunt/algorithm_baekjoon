class Solution {
    public int solution(String s) {
        int answer = s.length();
        int count = 1;
        for(int len = 1; len <= s.length() / 2; len ++){
            StringBuilder result = new StringBuilder();
            String compareStr = s.substring(0, len); 
            for(int currStart = len; currStart <= s.length(); currStart += len){ 
                int currEnd = Math.min(s.length(), currStart + len);
                String curr = s.substring(currStart, currEnd);
                if(compareStr.equals(curr)){
                    count++;
                } else {
                    if(count >= 2){
                        result.append(count);
                    }
                    result.append(compareStr);
                    compareStr = curr;   
                    count = 1;
                }
            }
            result.append(compareStr);
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}