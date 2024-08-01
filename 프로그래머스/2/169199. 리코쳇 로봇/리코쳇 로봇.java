import java.util.*;
class Solution {
    static int N;
    static int M;
    static String[] B;
    static int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    class Cell {
        int r;
        int c;
        int move;
        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.move = 0;
        }
        public Cell(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }
    public static char readBoard(int r, int c) {
        return B[r].charAt(c);
    }
    public int solution(String[] board) {
        // BFS
        B = board;
        N = board.length;
        M = board[0].length();
        Queue<Cell> q = new LinkedList<>();
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                // don't copy the entire board, write a static method to make this more convenient
                if (readBoard(i, j) == 'R') {
                    q.offer(new Cell(i, j));
                }
            }
        }
        boolean[][] visited = new boolean[N][M];
        while (!q.isEmpty()) {
            Cell curr = q.poll();
            if (!visited[curr.r][curr.c]) {
                visited[curr.r][curr.c] = true;
                // after making vector static, loop through them instead
                for (int[] v : vector) {
                    Cell next = move(curr, v);
                    if (next.r == curr.r && next.c == curr.c) {
                        continue;
                    }
                    next.move++;
                    if (readBoard(next.r, next.c) == 'R') {
                        return next.move;
                    }
                    q.offer(next);
                }
            }
            
          
                // check visited right after popping, not before adding
                // you might process a queue item twice if you only check before adding
        }
        return -1;
    }
    public boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    public Cell move(Cell curr, int[] v) {
        // make this static
        int r = curr.r+v[0];
        int c = curr.c+v[1];
        while (inRange(r, c) && readBoard(r, c) != 'D') {
            r += v[0];
            c += v[1];
        }
        return new Cell(r-v[0], c-v[1], curr.move+1);
    }
}