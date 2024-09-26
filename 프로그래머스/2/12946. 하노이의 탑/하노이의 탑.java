import java.util.*;
class Solution {
    public int[][] solution(int n) {
        List<int[]> list = new ArrayList<>();
        dfs(1, 3, 2, n, list);
        int[][] answer = new int[list.size()][2];
        for (int i = 0; i < list.size(); i ++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    public static void dfs(int from, int to, int empty, int n, List<int[]> list) {
        if (n == 1) {
            list.add(new int[]{from, to});
            return;
        }
        dfs(from, empty, to, n-1, list);
        list.add(new int[]{from, to}); // 제일 큰 원판을 to로 옮김
        dfs(empty, to, from, n-1, list);
    }
}