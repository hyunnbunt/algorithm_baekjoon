import java.util.*;

class Solution {
    static int numOfRow;
    static int numOfCol;
    static int[][] vector;
    public int solution(int[][] land) {
        numOfRow = land.length;
        numOfCol = land[0].length;
        vector = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[numOfRow][numOfCol];
        int[] colWithPet = new int[numOfCol];
        for (int r = 0; r < numOfRow; r ++) {
            for (int c = 0; c < numOfCol; c ++) {
                if (!visited[r][c] && land[r][c] == 1) {
                    Set<Integer> tempCols = new HashSet<>();
                    int tempAmount = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{r, c});
                    visited[r][c] = true;
                    while (!stack.isEmpty()) {
                        int[] curr = stack.pop();
                        tempAmount ++;
                        tempCols.add(curr[1]);
                        for (int[] v : vector) {
                            int newR = curr[0] + v[0];
                            int newC = curr[1] + v[1];
                            if (inRangeOfLand(newR, newC) && !visited[newR][newC] && land[newR][newC] == 1)  {
                              stack.push(new int[]{newR, newC});
                                visited[newR][newC] = true;
                            }
                        }
                    }
                    for (int col : tempCols) {
                        colWithPet[col] += tempAmount;
                    }
                }
            }
        }
        int max = 0;
        for (int p : colWithPet) {
            max = Math.max(max, p);
        }
        return max;
    }

    public boolean inRangeOfLand(int r , int c) {
        if (r >= numOfRow || c >= numOfCol || r < 0 || c < 0) {
            return false;
        }
        return true;
    }

}