import java.util.*;
class Solution {
    public static int h;
    public static int w;
    public int solution(int m, int n, String[] board) {
        h = m;
        w = n;
        char[][] charBoard = new char[h][w];
        for (int i = 0; i < h; i ++) {
            charBoard[i] = board[i].toCharArray();
        }
        int count = 0;
        while (true) {
            List<int[]> squares = new ArrayList<>();
            boolean[][] visited = new boolean[h][w];
            for (int c = 0; c < w; c ++) {
                for (int r = 0; r < h; r ++) {
                    if (!visited[r][c] && charBoard[r][c] != 'X' && isSquare(r, c, charBoard)) {
                        squares.add(new int[]{r, c});
                        visited[r][c] = true;
                        System.out.println("r, c: " + r + ", " + c);
                    }
                }
            }
            int temp = countAndRemove(squares, charBoard);
            System.out.println("temp: " + temp);
            if (temp == 0) {
                break;
            }
            count += temp;
            shakeBoard(charBoard);
        }
        return count;
    }
    public boolean isSquare(int r, int c, char[][] charBoard) {
        int[][] vector = new int[][]{{0, 1}, {1, 0}, {1, 1}};
        char ch = charBoard[r][c];
        for (int[] v : vector) {
            int nextR = r + v[0];
            int nextC = c + v[1];
            if (!inRange(nextR, nextC) || charBoard[nextR][nextC] != ch) {
                return false;
            }
        }
        return true;
    }
    public boolean inRange(int r, int c) {
        if (c >= w || r >= h) {
            return false;
        }
        return true;
    }
    public int countAndRemove(List<int[]> squares, char[][] charBoard) {
        boolean[][] counted = new boolean[h][w];
        int count = 0;
        int[][] vector = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        for (int[] sq : squares) {
            for (int[] v : vector) {
                int r = sq[0] + v[0];
                int c = sq[1] + v[1];
                if (!counted[r][c]) {
                    counted[r][c] = true;
                    count ++;
                    charBoard[r][c] = 'X';
                }
            }
        }
        return count;
    }
    public void shakeBoard(char[][] charBoard) {
        for (int c = 0; c < w; c ++) {
            int count = 0;
            for (int r = h-1; r >= 0; r --) {
                if (charBoard[r][c] == 'X') {
                    count ++;
                } else if (count > 0) {
                    charBoard[r+count][c] = charBoard[r][c];
                    charBoard[r][c] = 'X';
                }
            }
            System.out.println("column(" + c + ") count: " + count);
        }

    }
}