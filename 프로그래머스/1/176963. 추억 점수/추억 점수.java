import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int n = name.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0 ; i < n; i ++) {
            map.put(name[i], yearning[i]);
        }
        int[] answer = new int[photo.length];
        for (int j = 0; j < photo.length; j ++) {
            int point = 0;
            for (String p : photo[j]) {
                point += map.getOrDefault(p, 0);
            }
            answer[j] = point;
        }
        return answer;
    }
}