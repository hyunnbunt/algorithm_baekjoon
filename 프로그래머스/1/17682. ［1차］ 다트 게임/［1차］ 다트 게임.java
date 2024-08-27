import java.util.*;
class Solution {
    public int solution(String dartResult) {
        List<String> points = parse(dartResult, "[D, S, T, *, #]");
        List<String> bonusAndOptions = parse(dartResult, "[0-9]");
        int[] answer = new int[points.size()];
        for (int i = 0; i < points.size(); i ++) {
            int point = Integer.parseInt(points.get(i));
            char bonus = bonusAndOptions.get(i).charAt(0);
            if (bonus == 'S') {
                answer[i] = point;
            }
            if (bonus == 'D') {
                answer[i] = (int) Math.pow(point, 2);
            }
            if (bonus == 'T') {
                answer[i] = (int) Math.pow(point, 3);
            }
            if (bonusAndOptions.get(i).length() > 1) { // 옵션 있다면
                char option = bonusAndOptions.get(i).charAt(1);
                if (option == '*') {
                    answer[i] *= 2;
                    if (i > 0) {
                        answer[i-1] *= 2;
                    }
                }
                if (option == '#') {
                    answer[i] *= -1;
                }
            }
        }
        return Arrays.stream(answer).sum();
    }
    static List<String> parse(String target, String regex) {
        String[] parsed = target.split(regex);
        List<String> parsedList = new ArrayList<>();
        for (String p : parsed) {
            if (p.length() == 0) {
                continue;
            }
            parsedList.add(p);
        }
        return parsedList;
    }
}