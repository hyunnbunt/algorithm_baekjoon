
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    /**
     최단거리 구하기 : bfs
     조건 - 하나의 벽을 부술 수 있다.
     주어진 행렬에서 다음 이동할 위치는 col +1 또는 row +1이다.
     이때, 다음 위치의 값이 0일 때 또는
     다음 위치의 값이 1이면서 벽 부수기 기회가 남아있을 때 이동 가능하다.
     위 조건을 만족하지 않으면 더 이상 이동하지 못한다.
     조건을 만족하는 경우 이동하면서, 현재 위치가 N*M의 위치인지 확인한다.
     이동 가능한 다음 위치를 queue에 넣고, 꺼내되 queue에 넣을 객체에는
     해당 위치 정보와 벽 부수기 기회 사용 여부, 지금까지 거리를 저장한다.
     queue에서 순차적으로 객체를 꺼내면서 다음 위치 정보를 알아낼 수 있다.
     no! bfs이지만, bfs는 최단 경로 찾는 방법이, 매 순간 최선의 선택 하는 것임
     하지만 벽을 사용해서라도 짧은 길을 가는 게 최선인지, 벽을 뚫지 않고 돌아 가는 게 최선인지는 판단 못 함. 그냥 무조건 현재 부터 다음 노드까지 최단 거리만 고려할 뿐.
     그러다보면 더 이상 벽을 뚫지 못해 아예 목표 지점에 도달하지 못 하는 경우가 생긴다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = nm[0];
        int m = nm[1];
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i ++) {
            String tempStr = br.readLine();
            for (int j = 0; j < m; j ++) {
                board[i][j] = tempStr.charAt(j)-'0';
            }
        }
        Queue<Cell> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        q.add(new Cell(0, 0, true, 1)); //시작하는 칸 포함해서 센다, 1부터 시작
        int[][] nextMove = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            Cell curr = q.poll();
            if (curr.col==n-1&&curr.row==m-1) {
                System.out.println(curr.distance);
                return;
            }
            if ((curr.chance&&!visited[curr.col][curr.row][1])||(!curr.chance&&!visited[curr.col][curr.row][0])) {
                if (curr.chance) {
                    visited[curr.col][curr.row][1] = true;
                } else {
                    visited[curr.col][curr.row][0] = true;
                }
                for (int[] next : nextMove) {
                    int nextCol = curr.col+next[0];
                    int nextRow = curr.row+next[1];
                    if (nextCol<n&&nextCol>=0&&nextRow<m&&nextRow>=0) {
                        if (board[nextCol][nextRow]==0) {
                            q.add(new Cell(nextCol, nextRow, curr.chance, curr.distance+1));
                        } else if(curr.chance) {
                            q.add(new Cell(nextCol, nextRow, false, curr.distance+1));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
    public static class Cell {
        int col;
        int row;
        boolean chance;
        int distance;

        public Cell(int col, int row, boolean chance, int distance) {
            this.col = col;
            this.row = row;
            this.chance = chance;
            this.distance = distance;
        }
    }
}
