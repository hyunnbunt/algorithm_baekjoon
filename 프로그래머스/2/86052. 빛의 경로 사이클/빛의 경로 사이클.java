import java.util.*;
class Solution {
    public static boolean[][][] visited;
    public static int R;
    public static int C;
    public static int[][] vector = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // up, right, down, left
    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        visited = new boolean[R][C][4];
        List<Integer> lenList = new ArrayList<>();
        for (int r = 0; r < R; r ++) {
            for (int c = 0; c < C; c ++) {
                for (int d = 0; d < 4; d ++) { // 모든 칸에서 네 가지 방향 모두 시작
                    if (!visited[r][c][d]) {
                        lenList.add(findCycle(grid, r, c, d));
                    }
                }
            }
        }
        return lenList.stream().sorted().mapToInt(i->i).toArray();
    }
    public static int findCycle(String[] G, int r, int c, int d) {
        int count = 0;
        while (!visited[r][c][d]) {
            visited[r][c][d] = true;
            char ch = G[r].charAt(c);
            if (ch == 'L') {
                d = (d+3)%4;
            } else if (ch == 'R') {
                d = (d+1)%4;
            }
            r = (r + vector[d][0] + R)%R;
            c = (c + vector[d][1] + C)%C;
            count++;
        }
        return count;
    }
}