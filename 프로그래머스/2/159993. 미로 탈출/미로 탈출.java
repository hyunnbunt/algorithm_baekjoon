import java.util.*;
class Solution {
    static class Cell {
        int r;
        int c;
        int dist;
        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.dist = 0;
        }
        public Cell(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    static int R;
    static int C;
    static String[] M;
    static int[][] vector = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public static char readMap(int r, int c) {
        return M[r].charAt(c);
    }
    
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
    
    public static int getDist(char start, char goal) {
        int answer = 0;
        Queue<Cell> q = new LinkedList<>();
        for (int r = 0; r < R; r ++) {
            for (int c = 0; c < C; c ++) {
                if (readMap(r, c) == start) {
                    q.offer(new Cell(r, c));
                }
            }
        }
        boolean[][] visited = new boolean[R][C];
        boolean reached = false;
        while (!q.isEmpty()) {
            Cell curr = q.poll();
            if (readMap(curr.r, curr.c) == goal) {
                answer += curr.dist;
                reached = true;
                break;
            }
            if (visited[curr.r][curr.c]) {
                continue;
            }
            visited[curr.r][curr.c] = true;
            for (int[] v : vector) {
                int nextR = curr.r + v[0];
                int nextC = curr.c + v[1];
                if (inRange(nextR, nextC) && readMap(nextR, nextC) != 'X') {
                    q.offer(new Cell(nextR, nextC, curr.dist + 1));
                }
            }
        }
        if (!reached) {
            return -1;
        }
        return answer;
    }
    
    public int solution(String[] maps) {
        M = maps;
        R = maps.length;
        C = maps[0].length();
        int s_l = getDist('S', 'L');
        if (s_l == -1) {
            return -1;
        }
        int l_e = getDist('L', 'E');
        if (l_e == -1) {
            return -1;
        }
        return s_l + l_e;
    }
}