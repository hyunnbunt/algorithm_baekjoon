import java.util.*;
class Solution {
    public static int n;
    public static int[][] d;
    public static int maxWin;
    public static String winDice;
    public int[] solution(int[][] dice) {
        // 주사위 조합 * 수의 합 리스트 => Map에 저장 후 keySet 이용해 모두 접근 => 2개 짝 만들어서 이분탐색
        // key 형태로는 String이 편할 듯
        n = dice.length;
        d = dice;
        maxWin = 0;
        winDice = null;
        List<String> selected = new ArrayList<>();
        select(selected, "", 0, n/2); // 모든 dice 조합을 selected에 저장
        System.out.println(selected.toString());
        Map<String, List<Integer>> map = new HashMap<>();
        for (String combi : selected) {
            updateSum(combi, map);
            System.out.println(combi + "-> " + map.getOrDefault(combi, new ArrayList<>()).size());
        }
        // 각 combi 마다 짝궁 찾기?
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < n; i ++) {
            all.append(i);
        }
        List<String> kList = new ArrayList<>(map.keySet());
        for (int i = 0; i < kList.size(); i ++) {
            for (int j = i+1; j < kList.size(); j ++) {
                String first = kList.get(i);
                String second = kList.get(j);
                char[] combi = (first+second).toCharArray();
                Arrays.sort(combi);
                String str = new String(combi);
                if (str.equals(all.toString())) {
                    binSrch(map, first, second);
                }
            }
        }
        int[] answer = new int[n/2];
        int ans = 0;
        for (char c : winDice.toCharArray()) {
            answer[ans] = c-'0'+1;
            ans++;
        }
        return answer;
    }
    public void binSrch(Map<String, List<Integer>> map, String first, String second) {
        int firstWin = 0, secondWin = 0;
        List<Integer> fList = map.get(first);
        List<Integer> sList = map.get(second);
        Collections.sort(fList);
        Collections.sort(sList);
        // 무승부 영역을 찾고 싶음! 시작 인덱스, 끝 인덱스
        for (int f : fList) {
            int left = 0;
            int right = sList.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (f > sList.get(mid)) { 
                    left = mid+1;
                } else { 
                    right = mid;
                }
            }
            firstWin += left;
        }
        for (int s : sList) {
            int left = 0;
            int right = fList.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (s > fList.get(mid)) { 
                    left = mid+1;
                } else { 
                    right = mid;
                }
            }
            secondWin += left;
        }
        if (firstWin > maxWin) {
            maxWin = firstWin;
            winDice = first;
        }
        if (secondWin > maxWin) {
            maxWin = secondWin;
            winDice = second;
        }
    }
    public void sum(int depth, String combi, List<Integer> sum, int prevSum) { // 지난 조합에 추가
        if (depth == n/2) { // 잎파리 prevSum을 sum에 추가하고 리턴
            sum.add(prevSum);
            return;
        }
        for (int num : d[combi.charAt(depth)-'0']) {
            sum(depth+1, combi, sum, prevSum+num);
        }
    }
    public void updateSum(String combi, Map<String, List<Integer>> map) {
        List<Integer> sum = map.getOrDefault(combi, new ArrayList<>());
        sum(0, combi, sum, 0);
        map.put(combi, sum);
    }
    public void select(List<String> selected, String str, int start, int k) { // 인덱스만 넣으면 됨
        // start에서부터 k개를 선택
        if (k == 0) { // 선택완료
            selected.add(str);
        }
        for (int i = start; i < n; i++) {
            select(selected, str+i, i+1, k-1);
        }
    }
}