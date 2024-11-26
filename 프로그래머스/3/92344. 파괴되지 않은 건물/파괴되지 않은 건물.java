import java.util.Arrays;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        // board 상태에서 시작, skill을 모두 순회하면서 범위 시작과 끝에 변화값을 마크할 것
        int[][] b = new int[board.length+1][board[0].length+1]; 
        for (int[] sk : skill) {
            int d = sk[0] == 1 ? -sk[5] : sk[5];
            // 행에 대해 시작 열, 끝 열에 표시 & 열에 대해 시작 행, 끝 행에 표시
            int r1 = sk[1], c1 = sk[2], r2 = sk[3], c2 = sk[4];
            b[r1][c1] += d;
            b[r1][c2+1] += -d;
            b[r2+1][c1] += -d;
            b[r2+1][c2+1] += d;
        }
        for (int i = 0; i < b.length; i ++) {
            for (int j = 1; j < b[0].length; j ++) { // 각 행마다 열 방향 (j)
                b[i][j] = b[i][j-1]+b[i][j];
            }
        }
        for (int j = 0; j < b[0].length; j ++) {
            for (int i = 1; i < b.length; i ++) { // 각 열마다 행 방향 (i)
                b[i][j] = b[i-1][j]+b[i][j];
            }
        }
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                if (board[i][j] + b[i][j] > 0) {
                    answer ++;
                }
            }
        }
        return answer;
    }
    /** 
     파괴되지 않은 건물의 개수?
     for 문으로 단순 구현하면 최악의 경우 시간 복잡도 : 1000 * 1000 * 250000
     => 범위에 대해 +, - 한꺼번에 구현을 반복할 때 누적합 활용
     */
}