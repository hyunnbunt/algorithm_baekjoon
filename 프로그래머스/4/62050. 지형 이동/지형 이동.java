import java.util.*;
class Solution {
    static int rowLen;
    static int columnLen;
    static int[][] vector;
    public int solution(int[][] land, int height) {
        // 임의의 지점에서부터 탐색할 것 - DFS 완전탐색, 방문 배열 업데이트
        rowLen = land.length;
        columnLen = land[0].length;
        vector = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visited = new boolean[rowLen][columnLen]; // 한 그룹 탐색할 때 활용
        PriorityQueue<Cell> pq = new PriorityQueue<>((o1, o2) -> o1.ladderCost - o2.ladderCost);
        int costSum = 0;
        Stack<Cell> st = new Stack<>();
        Cell start = new Cell(0, 0, land[0][0]);
        while (start != null) {
            // System.out.println("Searching start from " + start);
            st.push(start);
            visited[start.row][start.column] = true; // stack에 넣을 때 방문 처리 (이동 가능한 경우에만 방문 처리)
            while (!st.isEmpty()) {
                Cell curr = st.pop();
                // System.out.println("curr -> " + curr);
                for (int[] v : vector) {
                    int nextRow = curr.row + v[0];
                    int nextColumn = curr.column + v[1];
                    if (inRange(nextRow, nextColumn) && !visited[nextRow][nextColumn]) { // 탐색 가능한 범위 확인
                        int nextHeight = land[nextRow][nextColumn];
                        int diff = Math.abs(curr.height - nextHeight);
                        Cell next = new Cell(nextRow, nextColumn, nextHeight);
                        if (diff <= height) { // 사다리 없이 이동 가능한지 확인
                            st.push(next);
                            visited[nextRow][nextColumn] = true; // 방문 처리는 이동 가능한 경우에만 할 것
                        } else {
                            next.setLadderCost(diff);
                            pq.add(next);
                        }
                    }
                }
            }
            Cell nextStart = null;
            while (pq.size() > 0) {
                Cell c = pq.poll();
                if (c == null) {
                    break;
                }
                if (visited[c.row][c.column]) {
                    continue;
                }
                nextStart = c;
                costSum += nextStart.ladderCost;
                break;
            }
            start = nextStart;
        }
        return costSum;
    }
    static boolean inRange(int row, int column) {
        return row < rowLen && row >= 0 && column < columnLen && column >= 0;
    }
    static class Cell {
        int row;
        int column;
        int height;
        int ladderCost;
        Cell(int row, int column, int height) {
            this.row = row;
            this.column = column;
            this.height = height;
        }
        void setLadderCost(int ladderCost) {
            this.ladderCost = ladderCost;
        }
    }
}