import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> edges = new ArrayList();
        for (int i = 0; i <= n; i ++) {
            edges.add(new ArrayList());
        }
        for (int[] e : edge) { // 양방향
            edges.get(e[0]).add(e[1]);
            edges.get(e[1]).add(e[0]);
        }
        Queue<int[]> q = new LinkedList<>(); // BFS
        q.offer(new int[]{1, 0});
        boolean[] visited = new boolean[n+1];
        Map<Integer, Integer> distNodes = new HashMap<>();
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (visited[curr[0]]) {
                continue;
            }
            visited[curr[0]] = true;
            distNodes.put(curr[1], distNodes.getOrDefault(curr[1], 0) + 1);
            for (int next : edges.get(curr[0])) {
                q.offer(new int[]{next, curr[1]+1});
            }
        }
        int maxDist = 0;
        for (int k : distNodes.keySet()) {
            maxDist = Math.max(k, maxDist);
        }
        return distNodes.get(maxDist);
    }
}