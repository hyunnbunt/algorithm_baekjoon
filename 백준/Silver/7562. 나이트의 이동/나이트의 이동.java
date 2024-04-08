
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    /**
     최소 몇 번만에 목표 지점에 도달하는지 : bfs
     dfs로 찾을 수도 있지만, 최악의 경우 모든 노드의 최대 깊이까지 탐색해야 최소를 보장한다.
     깊이가 하나씩 깊어질 때마다 모두 탐색해서 목표 지점에 도달하는지 확인, 깊이를 출력한다.
     (5, 5) 기준으로 나이트 이동 -> x축과 y축은 절대값 1 또는 2이며, 양수 또는 음수
     나이트의 시작 위치에서 위 기준에 따라 8개의 이동 가능 위치를 queue에 추가.
     queue에서 나이트의 예상 이동 위치를 꺼내고, 목표 지점인지 확인, 맞으면 이동 횟수를 출력.
     bfs인데, 깊이를 알아야 한다? queue에 위치를 추가할 때 깊이를 함께 저장.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < cases; c ++) {
            int l = Integer.parseInt(br.readLine());
            int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] goal = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] nextLoc = new int[][]{{-1, 2}, {-2, 1}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[l][l];
            q.offer(new int[]{start[0], start[1], 0});
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                if (curr[0]==goal[0] && curr[1]==goal[1]) {
                    sb.append(curr[2]).append("\n");
                    break;
                }
                if (visited[curr[0]][curr[1]]) {
                    continue;
                }
                visited[curr[0]][curr[1]] = true;
                for (int[] next : nextLoc) {
                    int nextX = curr[0]+next[0];
                    int nextY = curr[1]+next[1];
                    if (nextX<0 || nextX>=l || nextY<0 || nextY>=l) {
                        continue;
                    }
                    q.offer(new int[]{nextX, nextY, curr[2] + 1});
                }
            }
        }
        System.out.println(sb);
    }
}
