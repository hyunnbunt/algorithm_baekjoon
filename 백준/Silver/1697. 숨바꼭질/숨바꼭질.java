import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main {

    /**
     수빈의 위치 : N, 동생의 위치 K - 0 <= K, N <= 100000 (int 범위)
     수빈의 이동 가능한 다음 위치 -> 현재위치로부터 +1, -1, *2
     가장 최소 이동 횟수로 N->K로 갈 때 그 횟수 출력 - bfs : queue (addLast, removeFirst)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nk[0];
        int k = nk[1];
        if (n >= k) {
            System.out.println(n-k);
            return;
        }
        Queue<int[]> q = new LinkedList<>();
        TreeSet<Integer> visited = new TreeSet<>();         // visited 확인할 때 1회 logN의 시간 (N = visited.size())
        q.offer(new int[]{n, 0});
        int count = 0;
        while (!q.isEmpty()) {
            int[] x = q.poll();
            if (x[0] == k) {
                count = x[1];
                break;
            }
            if (!visited.contains(x[0])) {
                visited.add(x[0]);
                q.offer(new int[]{x[0]+1, x[1]+1});
                q.offer(new int[]{x[0]-1, x[1]+1});
                 if (x[0] <= k * 2 / 3) {
                    q.offer(new int[]{x[0]*2, x[1]+1});
                }
            }
        }
        System.out.println(count);
    }
}
