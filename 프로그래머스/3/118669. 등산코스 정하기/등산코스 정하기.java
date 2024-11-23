import java.util.*;
class Solution {
    /** 
    모든 휴게소에 대해서 (아무) 출입구로부터의 최단 거리를 갱신
    */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        List<Node>[] graph = new List[n+1]; // 간선 정보를 이용해 그래프 만들기 - 노드의 개수 n(번호 1~n)
        for (int i = 1; i <= n; i ++) {
            graph[i] = new ArrayList<>();
        }
        Set<Integer> gSet = new HashSet<>();
        for (int g : gates) {
            gSet.add(g);
        }
        Set<Integer> sSet = new HashSet<>();
        for (int s : summits) {
            sSet.add(s);
        }
        for (int[] path : paths) {
            // 최적화 : gate에서는 출발만! x->gate 불가능, gate->gate 불가능, gate ->x 가능, 
            if (!sSet.contains(path[0]) && !gSet.contains(path[1])) { // 도착지가 gate 아닐 때 && 출발지가 summit이 아닐 때만 추가
                graph[path[0]].add(new Node(path[1], path[2]));
            }
            if (!sSet.contains(path[1]) && !gSet.contains(path[0])) {
                graph[path[1]].add(new Node(path[0], path[2]));
            }
        }
        int[] result = new int[n+1];
        Arrays.fill(result, Integer.MAX_VALUE); // 다익스트라 배열 : 초기값 가장 크게 설정 -> 줄일 수 있을 때만 업데이트
        PriorityQueue<Place> pq = new PriorityQueue<>((o1, o2) -> o1.maxWeightFromGate - o2.maxWeightFromGate);
        for (int g : gates) {
            result[g] = 0;
            pq.add(new Place(g, 0)); 
        } 
        int minW = Integer.MAX_VALUE; // 모든 탐색에서 정상에 도달할 때마다 업데이트
        int summitN = -1;
        while (!pq.isEmpty()) { // 최적의 경로를 먼저 뽑기(가중치 작은 경로 먼저 선택) => 업데이트 및 탐색 횟수 줄임 
            Place curr = pq.poll(); // curr를 방문 중 - 현재 정상까지 최적의 경로가 있다면 그것보다 더 큰 경로는 아예 확인할 필요 없음
            if (curr.maxWeightFromGate > minW) {
                continue;
            }
            if (sSet.contains(curr.number)) { // summit 도달하면 멈추기
                if (curr.maxWeightFromGate == minW) {
                    summitN = Math.min(summitN, curr.number); // 번호 작은 것으로 업데이트
                } else {
                    minW = curr.maxWeightFromGate;
                    summitN = curr.number;
                }
                continue;
            }
            for (Node next : graph[curr.number] ) { // 다음 노드 방문할지 결정하는 로직
                int nextMaxWeight = Math.max(curr.maxWeightFromGate, next.weight); // 다음 노드까지 간선 정보 포함한 경로의 가중치?
                if (nextMaxWeight < result[next.number]) { // 저장된 값 업데이트 가능하다면 
                    result[next.number] = nextMaxWeight;
                    pq.add(new Place(next.number, nextMaxWeight));
                }
            }
        }
        return new int[]{summitN, minW};
    }
    public class Place {
        int number;
        int maxWeightFromGate;
        Place(int number, int maxWeightFromGate) {
            this.number = number;
            this.maxWeightFromGate = maxWeightFromGate;
        }
    }
    public class Node {
        int number;
        int weight;
        Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }
}