
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;

/**
 * 아기 상어로부터 출발, bfs 하며 모든 칸에 대해 안전 거리를 업데이트한다.
 * 모든 아기 상어로부터 bfs 하며 기존 안전거리보다 현재 안전거리가 작다면 업데이트해준다.
 * 그 중 가장 큰 값을 출력한다.
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = nm[0];
        int m = nm[1];
        List<int[]> sharkLoc = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j ++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    sharkLoc.add(new int[]{i, j, 0});
                }
            }
        }
        int[][] safeDist = new int[n][m];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                safeDist[i][j] = n+m;
            }
        }
        int[][] nextMove = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        for (int[] shark : sharkLoc) {
            Queue<int[]> q = new LinkedList<>();
            q.add(shark);
            boolean[][] visited = new boolean[n][m];
            while (!q.isEmpty()) {
                int[] curr = q.poll();
                if (!visited[curr[0]][curr[1]]) {
                    visited[curr[0]][curr[1]] = true;
                    if (curr[2] < safeDist[curr[0]][curr[1]]) {
                        safeDist[curr[0]][curr[1]] = curr[2];
                    }
                    for (int[] next : nextMove) {
                        int nextCol = curr[0]+next[0];
                        int nextRow = curr[1]+next[1];
                        if (nextCol>=0&&nextCol<n&&nextRow>=0&&nextRow<m) {
                            q.add(new int[]{nextCol, nextRow, curr[2]+1});
                        }
                    }
                }
            }
        }
        int maxSafeDist = Integer.MIN_VALUE;
        for (int[] col : safeDist) {
            for (int row : col) {
                maxSafeDist = Math.max(maxSafeDist, row);
            }
        }
        System.out.println(maxSafeDist);
    }
}
