import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class Main {
    /**
     * 출발, 도착 지점이 고정되어있고, 각 루트의 가중치 존재=>다익스트라?
     * 모든 정점을 방문하면서, 해당 정점의 간선들 중에서 방문되지 않은 정점들과 연결되는 간선을 골라,
     * 그 가중치를 더한 값이 다음 정점에 이미 저장된 도착 비용보다 작을 때 업데이트.
     * 각 정점마다 출발 지점에서부터 여러 경로 중 최소 비용이 드는 경로의 비용을 저장한다.
     * 저장된 데이터를 이용해서 계속 최소의 비용 찾기를 반복해서 목적지까지 도착하면 된다.
     * 메모리 초과 : 정점 최대 5만개, 간선 최대 5만 개. 가중치 최대 1천. => 최소 비용 초기값 조절: 간선 개수 * 1000
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = nm[0]; int m = nm[1]; // n : 정점의 수, m : 간선의 수
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>(); // 정점 번호 1~n
        for (int i = 0; i <= n; i ++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i ++) {
            int[] vertex = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int from = vertex[0]; int to = vertex[1]; int cost = vertex[2]; // 간선의 정보 : 시작 정점, 끝 정점, 비용
            graph.get(from).add(new int[]{to, cost});
            graph.get(to).add(new int[]{from, cost}); // 간선은 양방향
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        pq.add(new int[]{1, 0});
        // pq에 들어가는 배열 정보 : 업데이트 된 정점 번호와 출발 정점~해당 정점까지의 최소 비용.
        // 출발 정점은 1번이고, 1번 정점~1번 정점 비용은 0이다
        int[] minCost = new int[n+1]; // 가장 최소 비용을 저장, 업데이트할 배열 생성
        Arrays.fill(minCost, Integer.MAX_VALUE); // 모든 최소 비용을 초기값 무한으로(정수 최대값) 설정
        while (!pq.isEmpty()) { // 방문하지 않은 정점으로의 간선이 더 이상 없을 때까지 반복
            int[] curr = pq.poll();
            int prevNode = curr[0]; int costToPrevNode = curr[1];
            for (int[] e : graph.get(prevNode)) {
                int next = e[0]; int costToNextNode = e[1];
                if (costToNextNode+costToPrevNode < minCost[next]) {
                    minCost[next] = costToNextNode+costToPrevNode;
                    pq.add(new int[]{next, minCost[next]});
                }
                // pq에 들어가는 배열 정보 : 업데이트 된 정점 번호와 출발 정점~그 정점까지의 최소 비용.
                // 업데이트 됐을 때만 추가해야지, 무조건 Math.min 쓰려고 작은 것 업데이트 하고 업데이트 되던 말던 확인 없이 pq에 초과하면 메모리 초과 나지...
            }
        }
        System.out.println(minCost[n]);
    }
}
