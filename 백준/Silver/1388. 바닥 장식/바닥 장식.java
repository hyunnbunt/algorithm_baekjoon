
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    /**
     특정 판자에 연결된 모든 깊이를 dfs 탐색, 하나의 dfs 탐색마다 하나의 판자조각으로 count
     - 모양은 같은 행일 때 탐색 가능, ㅣ 모양은 같은 열일 때 탐색 가능
     임의의 판자조각의 위치에서 시작
     1) 해당 판자조각이 -인지 ㅣ인지 판단
     2) visited == false 라면 오른쪽 또는 아래 stack에 추가
     3) stack에서 꺼내면서 visited 업데이트
     4) 하나의 dfs 끝날 때마다 count ++
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        char[][] panels = new char[n][m];
        for (int col = 0; col < n; col ++) {
            String tempStr = br.readLine();
            for (int row = 0; row < m; row ++) {
                panels[col][row] = tempStr.charAt(row);
            }
        }
        int count = 0;
        boolean[][] visited = new boolean[n][m];
        Stack<int[]> stack = new Stack<>();
        for (int col = 0; col < n; col ++) {
            for (int row = 0; row < m; row ++) {
                if (visited[col][row]) {
                    continue;
                }
                count ++;
                stack.push(new int[]{col, row});
                while (!stack.isEmpty()) {
                    int[] curr = stack.pop();
                    if (visited[curr[0]][curr[1]]) {
                        continue;
                    }
                    visited[curr[0]][curr[1]] = true;
                    char currChar = panels[curr[0]][curr[1]];
                    if (currChar == '-') {
                        int nextRow = curr[1]+1;
                        if (nextRow < m && panels[curr[0]][nextRow] == currChar) {
                            stack.push(new int[]{curr[0], nextRow});
                        }
                    }
                    if (currChar == '|') {
                        int nextCol = curr[0]+1;
                        if (nextCol < n && panels[nextCol][curr[1]] == currChar) {
                            stack.push(new int[]{nextCol, curr[1]});
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
