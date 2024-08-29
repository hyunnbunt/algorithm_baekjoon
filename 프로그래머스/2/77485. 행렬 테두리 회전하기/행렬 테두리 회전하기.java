import java.util.*;
class Solution {
    public static int[] getLocation(int[] start, int[] end, int k) {
        int[][] vector = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int count = 0;
        int[] curr = start.clone();
        int v = 0;
        while (count < k) {
            v = v % 4;
            curr[0] = curr[0] + vector[v][0];
            curr[1] = curr[1] + vector[v][1];
            count ++;
            if (v % 2 == 1 && (curr[0] == start[0] || curr[0] == end[0])) { // 상하
                v++;
            } else if (v % 2 == 0 && (curr[1] == start[1] || curr[1] == end[1])) { // 좌우
                v++;
            }
        }
        return curr;
    }
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] rc = new int[rows][columns];
        int n = 0;
        for (int[] r : rc) {
            for (int i = 1; i <= columns; i++) {
                r[i-1] = i + n * columns;
            }
            n++;
        }
        int[] answer = new int[queries.length];
        for (int qIdx = 0; qIdx < queries.length; qIdx++) {
            int[] q = queries[qIdx];
            int[] start = new int[]{q[0]-1, q[1]-1};
            int[] end = new int[]{q[2]-1, q[3]-1};
            int size = (end[0] - start[0] + end[1] - start[1]) * 2;
            List<Integer> numbers = new LinkedList<>();
            List<int[]> locations = new LinkedList<>();
            int k = 0;
            while (k < size) {
                int[] curr = getLocation(start, end, k++);
                numbers.add(rc[curr[0]][curr[1]]);
                locations.add(curr);
            }
            int last = numbers.remove(numbers.size()-1);
            numbers.add(0, last);
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < locations.size(); i ++) {
                int[] curr = locations.get(i);
                int number = numbers.get(i);
                rc[curr[0]][curr[1]] = number;
                pq.add(number);
            }
            answer[qIdx] = pq.poll();
        }
        return answer;
    }
}