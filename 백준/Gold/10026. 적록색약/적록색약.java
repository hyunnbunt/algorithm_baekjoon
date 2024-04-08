import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
    static int n;
    static int[][] neighbors;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        neighbors = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        char[][] rgb = new char[n][n];
        char[][] rb = new char[n][n];
        for (int col = 0; col < n; col ++) {
            String tempStr = br.readLine();
            for (int row = 0; row < n; row ++) {
                char tempChar = tempStr.charAt(row);
                rgb[col][row] = tempChar;
                if (tempChar == 'G') {
                    tempChar = 'R';
                }
                rb[col][row] = tempChar;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dfs(rgb))
            .append(" ")
            .append(dfs(rb));
        System.out.println(sb);
    }
    public static int dfs(char[][] list) {
        List<int[]>[][] dfsList = new List[n][n];
        for (int col = 0; col < n; col ++) {
            for (int row = 0; row < n; row ++) {
                char curr = list[col][row];
                for (int[] neighbor : neighbors) {
                    int neighborCol = col+neighbor[0];
                    int neighborRow = row+neighbor[1];
                    if (neighborCol>=n || neighborCol<0 || neighborRow>=n || neighborRow<0) {
                        continue;
                    }
                    if (list[neighborCol][neighborRow] == curr) {
                        if (dfsList[col][row] == null) {
                            dfsList[col][row] = new LinkedList<>();
                        }
                        dfsList[col][row].add(new int[]{neighborCol, neighborRow});
                    }
                }
            }
        }
        Stack<int[]> stack = new Stack<>();
        boolean[][] visited = new boolean[n][n];
        int count = 0;
        for (int col = 0; col < n; col ++) {
            for (int row = 0; row < n; row ++) {
                if (visited[col][row]) {
                    continue;
                }
                stack.push(new int[]{col, row});
                count ++;
                while (!stack.isEmpty()) {
                    int[] curr = stack.pop();
                    if (visited[curr[0]][curr[1]]) {
                        continue;
                    }
                    visited[curr[0]][curr[1]] = true;
                    if (dfsList[curr[0]][curr[1]] == null) {
                        continue;
                    }
                    for (int[] next : dfsList[curr[0]][curr[1]]) {
                        stack.push(next);
                    }
                }
            }
        }
        return count;
    }
}
