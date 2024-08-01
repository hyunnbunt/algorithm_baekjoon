// import java.util.*;
// class Solution {
//     static int N;
//     static int M;
//     static String[] B;
//     static int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//     class Cell {
//         int r;
//         int c;
//         int move;
//         public Cell(int r, int c) {
//             this.r = r;
//             this.c = c;
//             this.move = 0;
//         }
//         public Cell(int r, int c, int move) {
//             this.r = r;
//             this.c = c;
//             this.move = move;
//         }
//     }
//     public static char readBoard(int r, int c) {
//         return B[r].charAt(c);
//     }
//     public int solution(String[] board) {
//         // BFS
//         B = board;
//         N = board.length;
//         M = board[0].length();
//         Queue<Cell> q = new LinkedList<>();
//         for (int i = 0; i < N; i ++) {
//             for (int j = 0; j < M; j ++) {
//                 // don't copy the entire board, write a static method to make this more convenient
//                 if (readBoard(i, j) == 'R') {
//                     q.offer(new Cell(i, j));
//                 }
//             }
//         }
//         boolean[][] visited = new boolean[N][M];
//         while (!q.isEmpty()) {
//             Cell curr = q.poll();
//             if (!visited[curr.r][curr.c]) {
//                 visited[curr.r][curr.c] = true;
//                 // after making vector static, loop through them instead
//                 for (int[] v : vector) {
//                     Cell next = move(curr, v);
//                     if (next.r == curr.r && next.c == curr.c) {
//                         continue;
//                     }
//                     next.move++;
//                     if (readBoard(next.r, next.c) == 'R') {
//                         return next.move;
//                     }
//                     q.offer(next);
//                 }
//             }
            
          
//                 // check visited right after popping, not before adding
//                 // you might process a queue item twice if you only check before adding
//         }
//         return -1;
//     }
//     public boolean inRange(int r, int c) {
//         return r >= 0 && r < N && c >= 0 && c < M;
//     }
//     public Cell move(Cell curr, int[] v) {
//         // make this static
//         int r = curr.r+v[0];
//         int c = curr.c+v[1];
//         while (inRange(r, c) && readBoard(r, c) != 'D') {
//             r += v[0];
//             c += v[1];
//         }
//         return new Cell(r-v[0], c-v[1], curr.move+1);
//     }
// }
import java.util.*;
class Solution {
    static int N;
    static int M;
    public int solution(String[] board) {
        // BFS
        Queue<int[]> q = new LinkedList<>();
        N = board.length;
        M = board[0].length();
        int[][] b = new int[N][M];
        int[] start = new int[3]; // queue에 넣을 정보
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                char c = board[i].charAt(j);
                if (c == 'D') b[i][j] = -1; 
                if (c == 'G') b[i][j] = 1; 
                if (c == 'R') {
                    start[0] = i; start[1] = j; start[2] = 0;
                }
            }
        }
        q.offer(start);
        boolean[][] visited = new boolean[N][M];
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (visited[curr[0]][curr[1]]) {
                continue;
            }
            visited[curr[0]][curr[1]] = true;
            int nextDist = curr[2]+1;
            for (int i = 0; i < 4; i ++) { // 네 방향으로 모두 확인
                int[] next = move(b, curr, i);
                next[2] = nextDist;
                if (next[0] == curr[0] && next[1] == curr[1]) continue; // 이동 못 했다면 다음 방향으로
                
                if (b[next[0]][next[1]] == 1) { // 목표에 도달했다면 거리를 리턴
                    return next[2];
                }
                q.offer(next);
            }
        }
        return -1;
    }
    public boolean inRange(int[] curr) {
        return curr[0] >= 0 && curr[0] < N && curr[1] >= 0 && curr[1] < M;
    }
    public int[] move(int[][] board, int[] curr, int dir) {
        int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[] v = vector[dir];
        while (true) {
            int[] next = new int[3];
            next[0] = curr[0] + v[0];
            next[1] = curr[1] + v[1];
            if (!inRange(next)) break;
            int nextMove = board[next[0]][next[1]];
            if (nextMove == -1) break;
            curr = next;
        }
        return curr;
    }
}