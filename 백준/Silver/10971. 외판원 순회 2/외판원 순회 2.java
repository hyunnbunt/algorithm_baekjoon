
import java.util.*;
import java.io.*;

public class Main {
    static int minCost;
    static boolean[] visited;
    static int n;
    static int[][] visitingCost;
    public static void main(String[] args) throws IOException {
        /**
         2차원 배열을 저장한다.
         모든 경로를 완전 탐색한다.
         재귀 함수를 활용한다.
         각 재귀함수 호출 때마다 매개 변수 : startCity, prevCity, prevCost
         개별 도시를 startCity로 설정하는 for loop 안에서 첫 재귀함수를 호출한다.
         모든 도시를 선택했거나, 더 이상 갈 수 있는 도시가 없으면 더 이상 재귀함수 호출하지 않는다.
         가장 깊이 탐색되었을 때, 함수를 끝내기 전 minCost를 업데이트해줘야 하면 해준다.
         minCost를 출력한다.
         * */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visitingCost = new int[n][n];
        for (int i = 0; i < n; i ++) {
            visitingCost[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        minCost = Integer.MAX_VALUE;
        visited = new boolean[n];
        // start from city 0, find a loop that's the most efficient. This loop is always the best way for every starting city.
        visited[0] = true;
        f(0, 0, 1);
        System.out.println(minCost);
    }

    /** public static void f(int prev, int cost, int visitedCities) {
     if (visitedCities == n) {
     if (visitingCost[prev][0] != 0) {
     cost += visitingCost[prev][0];
     minCost = Math.min(cost, minCost);
     return;
     }
     }
     }
     */
    public static void f(int prev, int cost, int visitedCities) {
        if (visitedCities == n) {
            if (visitingCost[prev][0] != 0) {
                cost += visitingCost[prev][0];
                minCost = Math.min(cost, minCost);
                return;
            }
        }
        for (int i = 0; i < n; i ++) {
            if (visitingCost[prev][i] != 0 && !visited[i]) {
                visited[i] = true;
                f(i, cost + visitingCost[prev][i], visitedCities + 1);
                visited[i] = false;
            }
        }
    }

}
