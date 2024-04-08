
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
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
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.offer(r);
        int[] visitedOrder = new int[n+1];
        int count = 1;
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            visitedOrder[curr] = count++;
            if (list[curr] == null) {
                continue;
            }
            list[curr].sort(Comparator.naturalOrder());
            for (Object next : list[curr]) {
                q.offer((int) next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i ++) {
            sb.append(visitedOrder[i]).append("\n");
        }
        System.out.println(sb);
    }
}
