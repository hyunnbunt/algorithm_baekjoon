
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
    /**
     r, g, b로 구성된 2차원 배열과 r, b로 구성된 2차원 배열을 각각 dfs 한다.
     입력 받으면서 두 개의 배열을 채운다 : 하나의 배열에는 g를 r로 변환한다.
     n의 범위가 크지 않으므로, 각 색상 칸에 대응시킬 수 있는 2차원 배열을 활용한다.
     2차원 배열은 List<int[]>로 구성되어 있다. "neighbors"
     각 "neighbors"는 해당 위치에 인접하고(위, 아래, 왼, 오)
     색상이 같은 칸의 위치가 저장된다.
     이 List[][]를 활용해 같은 색상 영역을 dfs하고,
     새로운 dfs를 시작할 때마다 count를 증가시킨다.
     (독립된 dfs 탐색 영역은 문제 정의상 독립된 영역이다)
     이때 dfs의 시작점은 2차원 배열의 개별 칸을 순차적으로 선택하되,
     앞선 dfs에서 탐색되었는지 visited 2차원 배열로 확인한다.
     아직 탐색되지 않는 칸만 시작점이 될 수 있다.
     더 이상 dfs를 시작할 곳이 없으면 count를 출력한다.
     */
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
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
        int[][] neighbors = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
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
                    for (int[] neighbor : neighbors) {
                        int neighborCol = curr[0]+neighbor[0];
                        int neighborRow = curr[1]+neighbor[1];
                        if (neighborCol>=n || neighborCol<0 || neighborRow>=n || neighborRow<0) {
                            continue;
                        }
                        if (list[neighborCol][neighborRow] == list[curr[0]][curr[1]]) {
                            stack.push(new int[]{neighborCol, neighborRow});
                        }
                    }

                }
            }
        }
        return count;
    }
}
