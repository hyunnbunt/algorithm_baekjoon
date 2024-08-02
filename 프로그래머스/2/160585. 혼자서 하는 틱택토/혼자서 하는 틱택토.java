import java.util.*;
class Solution {
    static int R;
    static int C;
    static String[] B;
    static int[][] vector = {{0, 1}, {1, 1}, {1, 0}, {-1, 1}};
    static class Cell {
        int r;
        int c;
        int num;
        Cell(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
    
    public static char readBoard(int r, int c) {
        return B[r].charAt(c);
    }
    
    public static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static int[] getNumOfTs() {
        int o = 0;
        int x = 0;
        for (int[] v : vector) {
            for (int r = 0; r < R; r ++) {
                for (int c = 0; c < C; c ++) {
                    if (r > 0 && c > 0) {
                        continue;
                    }
                    Stack<Cell> st = new Stack<>();
                    st.push(new Cell(r, c, 1));
                    char goal = readBoard(r, c);
                    while (!st.isEmpty()) {
                        Cell curr = st.pop();
                        if (curr.num == 3) {
                            if (goal == 'O') {
                                o ++;
                            }
                            if (goal == 'X') {
                                x ++;
                            }
                        }
                        int nextR = curr.r + v[0];
                        int nextC = curr.c + v[1];
                        if (inRange(nextR, nextC) && readBoard(nextR, nextC) == goal) {
                            st.push(new Cell(nextR, nextC, curr.num + 1));
                        }
                    }            
                }

            }
        }
        return new int[]{o, x};
    }
    
    public int solution(String[] board) {
        /** 
        o가 마지막으로 플레이하고나서 확인했을 경우, (o + x ==> 홀수) o = x + 1 이어야 한다.
        x가 마지막으로 플레이하고나서 확인했을 경우, (o + x ==> 짝수) o = x 이어야 한다.
        o가 마지막으로 플레이하고나서 확인했을 경우, o = x + 1 이면서 x 틱택토가 하나 있다면 무효, o 틱택토가 2개 이상이라면 무효
        x가 마지막으로 플레이하고나서 확인했을 경우, o = x 이면서 o 틱택토가 하나 있다면 무효, x 틱택토가 2개 이상이라면 무효
        틱택토를 알아내는 방법 -> method 만들고, o 틱택토 개수와 x 틱택토 개수를 정수 배열로 리턴할 것
        */
        int o = 0;
        int x = 0;
        R = board.length;
        C = board[0].length();
        B = board;
        for (int r = 0; r < R; r ++) {
            for (int c = 0; c < C; c ++) {
                char b = readBoard(r, c);
                if (b == 'O') {
                    o ++;
                }
                if (b == 'X') {
                    x ++;
                }
            }
        }
        int turn = (o + x) % 2;
        if (turn == 0 && o != x) { // x 플레이한 후
            return 0;
        }
        if (turn == 1 && o != x + 1) { // o 플레이한 후
            return 0;
        }
        int[] t = getNumOfTs();
        System.out.println(t[0] + ", " + t[1]);
        int oT = t[0];
        int xT = t[1];
        if (turn == 0) { // x 플레이한 후
            if (oT > 0) {
                return 0;
            }
        }
        if (turn == 1) {
            if (xT > 0) {
                return 0;
            }
        }
        return 1;
    }
}