import java.util.Stack;
class Solution {
    public int solution(String[][] board, int h, int w) {
        // 모든 경로를 탐색 : dfs, bfs 모두 가능 - dfs 풀이
        String targetColor = board[h][w];
        int len = board.length;
        boolean[][] visited = new boolean[len][len]; // square
        int[][] vector = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int count = 0;               
        for (int[] v : vector) {
            int newH = h + v[0];
            int newW = w + v[1];
            if (inRangeOfBoard(newH, newW, len) && board[newH][newW].equals(targetColor)) {
                count ++;
            }
        }
        return count;
    }
    public boolean inRangeOfBoard(int h, int w, int len) {
        if (h < 0 || w < 0 || h >= len || w >= len) {
            return false;
        }
        return true;
    }
}