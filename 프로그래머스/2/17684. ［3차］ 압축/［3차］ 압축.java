import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i ++) {
            dict.put(alphabet.substring(i, i+1), i+1);
        }
        int start = 0;
        int end = start+1;
        List<Integer> answerList = new ArrayList<>();
        String prev = "";
        while (start < msg.length() && end <= msg.length()) {
            String key = msg.substring(start, end);
            if (!dict.containsKey(key)) { // 사전에 없는 단어 찾으면
                dict.put(key, dict.size()+1); // 현재 단어를 사전에 추가
                answerList.add(dict.get(prev)); // 지난 단어 색인 출력
                start = end-1;
                end = start+1;
            } else if (end == msg.length()) {
                answerList.add(dict.get(key)); // 마지막 단어가 사전에 있으면 그 단어 색인 출력
                break;
            } else {
                prev = key;
                end++;
            }
        }
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i ++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}