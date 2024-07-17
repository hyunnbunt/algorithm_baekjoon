import java.lang.Math;
import java.util.*;
class Solution {
    static boolean[] visited;
    static int[] answer;
    public int[] solution(int[][] edges) {
        answer = new int[4];
        // 2차원 배열에 edges 정리
        int max = 0;
        for (int[] edge : edges) {
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        System.out.println(max);
        List<List<Integer>> graph = new ArrayList<>(max+1);
        for (int i = 0; i <= max; i ++) {
            graph.add(new ArrayList<>());
        }
        int[] edgesFrom = new int[max+1];
        int[] edgesTo = new int[max+1];
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            edgesFrom[edge[0]] ++;
            edgesTo[edge[1]] ++;
        }
        int root = 0;
        for (int i = 1; i <= max; i ++) {
            if (edgesFrom[i] >= 2 && edgesTo[i] == 0) {
                root = i;
                answer[0] = i;
                break;
            }
        }
        visited = new boolean[max+1];
        for (int start : graph.get(root)) {
            shape(start, graph, edgesFrom);
        }
        return answer;
    }
    public void shape(int curr, List<List<Integer>> graph, int[] edgesFrom) {
        // answer[1] : donut, answer[2] : stick, answer[3] : eight
        if (visited[curr]) {
            answer[1] ++;
            return;
        }
        if (edgesFrom[curr] == 2) {
            answer[3] ++;
            return;
        }
        if (edgesFrom[curr] == 0) {
            answer[2] ++;
            return;
        }
        visited[curr] = true;
        int next = graph.get(curr).get(0);
        shape(next, graph, edgesFrom);
    }
}