import java.util.*;
class Solution {
    public static class Aplicant {
        char lang;
        char jobtitle;
        char experience;
        char food;
        Aplicant(char lang, char jobtitle, char experience, char food) {
            this.lang = lang;
            this.jobtitle = jobtitle;
            this.experience = experience;
            this.food = food;
        }
        Aplicant(String[] str) {
            this.lang = str[0].charAt(0);
            this.jobtitle = str[1].charAt(0);
            this.experience = str[2].charAt(0);
            this.food = str[3].charAt(0);
        }
        @Override
        public int hashCode() {
            return this.lang + this.jobtitle + this.experience + this.food;
        }
        @Override
        public boolean equals(Object o) {
            Aplicant ap = (Aplicant) o;
            return (ap.lang == this.lang) && 
                (ap.jobtitle == this.jobtitle) && 
                (ap.experience == this.experience) && 
                (ap.food == this.food);
        }
        @Override
        public String toString() {
            return this.lang + ", " + this.jobtitle + ", " + this.experience + ", " + this.food;
        }
    }
    public static List<Aplicant> generateAllMatch(String[] s) {
        char[] chars = new char[]{s[0].charAt(0),  s[1].charAt(0), s[2].charAt(0), s[3].charAt(0)};
        List<Aplicant> allMatch = new ArrayList<>();
        // 0개만 매칭
        allMatch.add(new Aplicant('-', '-', '-', '-'));
        // 한 개만 매칭
        allMatch.add(new Aplicant(chars[0], '-', '-', '-'));
        allMatch.add(new Aplicant('-', chars[1], '-', '-'));
        allMatch.add(new Aplicant('-', '-', chars[2], '-'));
        allMatch.add(new Aplicant('-', '-', '-', chars[3]));
        // 두 개만 매칭
        allMatch.add(new Aplicant(chars[0], chars[1], '-', '-'));
        allMatch.add(new Aplicant(chars[0], '-', chars[2], '-'));
        allMatch.add(new Aplicant(chars[0], '-', '-', chars[3]));
        allMatch.add(new Aplicant('-', chars[1], chars[2], '-'));
        allMatch.add(new Aplicant('-', chars[1], '-', chars[3]));
        allMatch.add(new Aplicant('-', '-', chars[2], chars[3]));
        // 세 개만 매칭
        allMatch.add(new Aplicant(chars[0], chars[1], chars[2], '-'));
        allMatch.add(new Aplicant(chars[0], chars[1], '-', chars[3]));
        allMatch.add(new Aplicant(chars[0], '-', chars[2], chars[3]));
        allMatch.add(new Aplicant('-', chars[1], chars[2], chars[3]));
        // 네 개 매칭
        allMatch.add(new Aplicant(chars[0], chars[1], chars[2], chars[3]));
        return allMatch;
    }
    static int getNumOfOverMinimumPoint(int minimumPoint, List<Integer> points) {
        // minimumPoint보다 큰 + 가장 작은 값의 인덱스 찾기
        int start = 0; // 첫 인덱스
        int end = points.size() - 1; // 마지막 인덱스
        if (minimumPoint > points.get(end)) { // 탐색 필요없음
            return 0;
        }
        while (start <= end) { // start, end 모두 고려.
            int mid = (start + end) / 2; 
            int point = points.get(mid);
            if (point >= minimumPoint) { // 점수가 조건 충족. (중복 값 존재하므로 같다고 끝내면 안 됨) 내려보기.
                end = mid-1;
            } else { // 점수가 조건 충족 안 함. mid보다 큰 값 필요
                start = mid+1;
            }
        }
        return points.size() - start;
    }
    public int[] solution(String[] info, String[] query) {
        Map<Aplicant, List<Integer>> aplMap = new HashMap<>();
        for (String s : info) {
            String[] parsed = s.split(" ");
            int point = Integer.parseInt(parsed[4]);
            for (Aplicant apl : generateAllMatch(parsed)) {
                if (!aplMap.containsKey(apl)) {
                    aplMap.put(apl, new ArrayList<>());
                }
                aplMap.get(apl).add(point);
                // System.out.println(apl.toString() + " -> " + aplMap.get(apl).size());
            }
        }
        for (Aplicant key : aplMap.keySet()) {
            Collections.sort(aplMap.get(key));
        }
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i ++) {
            String[] parsed = query[i].replace(" and ", " ").split(" ");
            int point = Integer.parseInt(parsed[4]);
            Aplicant target = new Aplicant(parsed);
            if (aplMap.containsKey(target)) {
                answer[i] = getNumOfOverMinimumPoint(point, aplMap.get(target));
            }
        }
        return answer;
    }
}