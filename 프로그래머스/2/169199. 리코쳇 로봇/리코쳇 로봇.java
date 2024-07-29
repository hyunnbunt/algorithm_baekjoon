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
            visited[curr[0]][curr[1]] = true;
            int nextDist = curr[2]+1;
            for (int i = 0; i < 4; i ++) { // 네 방향으로 모두 확인
                int[] next = move(b, curr, i);
                next[2] = nextDist;
                if (next[0] == curr[0] && next[1] == curr[1]) continue; // 이동 못 했다면 다음 방향으로
                
                if (b[next[0]][next[1]] == 1) { // 목표에 도달했다면 거리를 리턴
                    return next[2];
                }
                if (visited[next[0]][next[1]]) continue;
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