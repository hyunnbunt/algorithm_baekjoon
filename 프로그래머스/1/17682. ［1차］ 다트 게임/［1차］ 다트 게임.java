import java.util.*;
class Solution {
    public int solution(String dartResult) {
        String[] bonusAndOption = dartResult.split("[0-9]");
        List<String> bList = new ArrayList<>();
        for (String b : bonusAndOption) {
            if (b.length() == 0) {
                continue;
            }
            bList.add(b);
        }
        String[] points = dartResult.split("[D, S, T, *, #]");
        List<Integer> pList = new ArrayList<>();
        for (String p : points) {
            if (p.length() == 0) {
                continue;
            }
            pList.add(Integer.parseInt(p));
        }
        int[] answer = new int[pList.size()];
        for (int i = 0; i < pList.size(); i ++) {
            answer[i] = pList.get(i);
            char bonus = bList.get(i).charAt(0);
            if (bonus == 'D') {
                answer[i] = (int) Math.pow(answer[i], 2);
            } else if (bonus == 'T') {
                answer[i] = (int) Math.pow(answer[i], 3);
            }
            // 옵션 있다면 적용
            if (bList.get(i).length() > 1) {
                char option = bList.get(i).charAt(1);
                if (option == '*') {
                    answer[i] *= 2;
                    if (i > 0) {
                        answer[i-1] *= 2;
                    }
                } else if (option == '#') {
                    answer[i] *= -1;
                }
            }
        }
        return Arrays.stream(answer).sum();
    }
}