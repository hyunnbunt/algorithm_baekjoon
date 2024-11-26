import java.util.Arrays;
class Solution {
    public static int R;
    public static int C;
    public static final int[][] vector = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        R = board.length;
        C = board[0].length;
        return sol(board, aloc, bloc);
    }
    public boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
    public int sol(int[][] B, int[] a, int[] b) {
        int aLose = -1; // 늘리기
        int aWin = R * C; // 줄이기
        boolean canMove = false;
        for (int[] v : vector) {
            int[] next = new int[]{a[0]+v[0], a[1]+v[1]};
            if (inRange(next[0], next[1]) && B[next[0]][next[1]] == 1) { 
                canMove = true;
                B[a[0]][a[1]] = 0; 
                int laterTurn = sol(B, b, next); // sol 함수를 호출 (b <-> a)
                B[a[0]][a[1]] = 1;
                if (laterTurn % 2 != 0) { // a가 진 경우(이후 턴이 홀수 == 현재 포함 짝수 턴) 중 가장 큰 턴
                    aLose = Math.max(laterTurn, aLose);
                } else {
                    aWin = Math.min(laterTurn, aWin); // a가 이긴 경우 중 가장 작은 턴
                }
            }
        }
        if (!canMove) {
            return 0;
        }
        if (Arrays.equals(a, b)) {
            return 1;
        }
        // 이기는 경우가 존재한다면?
        if (aWin < R * C) {
            return aWin+1;
        }
        return aLose+1;
    }
    /** 
    a가 이기는 경우 : 이후 턴이 홀수일 때
    a가 지는 경우 : 이후 턴이 짝수일 때
    - 모든 경우의 수를 따졌을 때 a가 지는 경우와 이기는 경우 둘 다 발생할 경우, 지는 경우에는 실수한 것으로 간주하고 이기는 경우만 고려한다
    - 지는 경우에는 최대한 턴을 오래 끌어야 함 / 이기는 경우에는 최대한 턴을 빠르게 끝내야 함
    - 현재 탐색하고 있는 노드에서 다음 노드를 모두 탐색하고 돌아왔을 때 그 카운트 값으로 b가 이겼는지 판단 가능 (홀, 짝)
    -> 이때 b가 이기는 경우만 있다면(a가 무조건 진다면) : a는 최대한 턴을 오래 끌었던, 즉 카운트가 가장 큰 값만 고려한다
    -> 이때 b가 지는 경우만 있다면(a가 무조건 이긴다면) : a는 최대한 턴을 짧게 끝냈던, 즉 카운트가 가장 작은 값만 고려한다
    -> b가 이기거나 질 수 있다면(a가 이기거나 질 수 있다면) : a가 이기는 경우만 고려, 위 조건 그대로 적용
    */
}