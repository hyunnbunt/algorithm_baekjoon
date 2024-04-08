
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        /**
         입력 받으면서 행렬을 만들기 (2차원 배열)
         한 쌍의 정점, 간선 u, v에 대해 arr[u][v], arr[v][u]를 1로 설정 (양방향 간선)
         0부터 시작해서 1인 정점을 방문하고, 해당 정점 인덱스를 기준으로 앞의 과정 반복
         이때 dfs로 풀이. => 대기열에 방금 넣은 것 (현재 정점의 인접 정점)을 먼저 탐색 (Stack)
         인접 노드를 오름차순으로 방문하려면, 인접 노드를 스택에 내림차순으로 push해야한다
         => 행렬 메모리 초과
         => List[]로 다시 구현, 이때 배열에 리스트가 비어있다면 생성해주는 코드 추가 필요
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        List[] list = new List[n+1];
        for (int i = 0; i < m; i ++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (list[u] == null) {
                list[u] = new LinkedList<Integer>();
            }
            list[u].add(v);
            if (list[v] == null) {
                list[v] = new LinkedList<Integer>();
            }
            list[v].add(u);
        }
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n+1];
        stack.push(r);
        int[] visitedOrder = new int[n+1];
        int count = 1;
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                visited[curr] = true;
                visitedOrder[curr] = count++;
                if (list[curr] != null) {
                    list[curr].sort(Comparator.reverseOrder());
                    for (Object next : list[curr]) {
                        stack.push((int) next);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i ++) {
            sb.append(visitedOrder[i]).append("\n");
        }
        System.out.println(sb);
    }
}
