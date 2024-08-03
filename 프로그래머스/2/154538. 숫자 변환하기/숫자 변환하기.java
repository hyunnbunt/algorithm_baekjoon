import java.util.*;
class Solution {
    class Calcul {
        int x;
        int depth;
        Calcul(int x, int depth) {
            this.x = x;
            this.depth = depth;
        }
    }
    public int solution(int x, int y, int n) {
        // 최소 : BFS - Queue
        Queue<Calcul> q = new LinkedList<>();
        q.add(new Calcul(x, 0));
        HashSet<Integer> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Calcul curr = q.poll();
            // 최적화 : 이미 도달해본 숫자는 방문하지 않는다 (숫자의 범위 x~y, 연산은 언제나 x가 커지는 방향)
            if (visited.contains(curr.x) || curr.x > y) {
                continue;
            }
            visited.add(curr.x);
            if (curr.x == y) {
                return curr.depth;
            }
            q.offer(new Calcul(curr.x+n, curr.depth+1));
            q.offer(new Calcul(curr.x*2, curr.depth+1));
            q.offer(new Calcul(curr.x*3, curr.depth+1));
        }
        return -1;
    }
}