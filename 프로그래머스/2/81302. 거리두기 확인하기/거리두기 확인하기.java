import java.util.*;
class Solution {
    static int rowLen;
    static int columnLen;
    static int[][] vector = {{1, 0}, {0, 1}, {-1, 0} , {0, -1}};
    static class Move {
        int r;
        int c;
        int d;
        Move(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < rowLen && c >= 0 && c < columnLen;
    }
    public static char getChar(String[] s, int r, int c) {
        return s[r].charAt(c);
    }
    public int[] solution(String[][] places) {
        int n = places.length;
        int[] answer = new int[n];
        Arrays.fill(answer, 1);
        rowLen = 5;
        columnLen = 5;
        for (int pIdx = 0; pIdx < n; pIdx++) {
            String[] place = places[pIdx];
            boolean[][] visited = new boolean[rowLen][columnLen];
            Queue<Move> q = new LinkedList<>();
            loop:
            for (int i = 0; i < rowLen; i ++) {
                for (int j = 0; j < columnLen; j ++) {
                    if (getChar(place, i, j) != 'P') { // 시작 지점, 사람 있는 곳에서부터
                        continue;
                    }
                    q.offer(new Move(i, j, 0));
                    visited[i][j] = true;
                    while (!q.isEmpty()) {
                        Move curr = q.poll();
                        if (getChar(place, curr.r, curr.c) == 'X' || curr.d > 2) { // 벽을 만났거나 거리가 2 넘음
                            continue;
                        }
                        if (getChar(place, curr.r, curr.c) == 'P' && curr.d > 0) { // 맨하튼 거리 2 이내에서 사람 발견
                            answer[pIdx] = 0;
                            System.out.println((pIdx+1) + "번째 대기실");
                            System.out.println(curr.r + ", " + curr.c + " -> " + "distance " + curr.d + " from " + i + ", " + j);
                            break loop;
                        } 
                        for (int[] v : vector) { // 다음 탐색 필요
                            int nextR = curr.r + v[0];
                            int nextC = curr.c + v[1];
                            if (!inRange(nextR, nextC) || visited[nextR][nextC]) { // 범위가 아니면 넘어간다
                                continue;
                            }
                            q.offer(new Move(nextR, nextC, curr.d + 1));
                        }
                    }
                }
            }
        }
        return answer;
    }
}