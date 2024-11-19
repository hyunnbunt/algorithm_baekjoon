class Solution {
    /** 
    모든 경로를 다 탐색? 
    미로의 가로와 세로 길이의 최대 50
    한 지점에서 k번 이동하는 모든 경우의 수
    각 지점 p에서 d,u,l,r 네 가지 방향 가능
    4^k번을 모두 탐색해서 그 중에 목표 지점에 도달하면서 사전 순으로 빠른 지점을 찾기?
    k는 2500이 최대 4^2500 = 2^5000 너무 큰 숫자? 하지만 최적화 가능
    (항상 d -> l ->r-> u 순서로 탐색)
    하지만 impossible이 나오는 최악의 경우 : 2^5000 연산 필요
    같은 지점을 방문하는 경우가 잦음. 예를 들어 출발점을 u, d방향으로 이동 후 다시 방문 가능
    그러면 k가 2 감소된 상태로 같은 목표 지점으로 가야 한다. 그러면 예를 들어,
    f(s, e, k) 는 f(s, e, k-2) + f(s, s, 2)가 될 수 있다? 근데 이건 항상 그렇지 않다. 
    같은 곳을 두 번 방문하는 경우가 많긴 하지만 항상 그렇지 않다. 
    예를 들어서 k가 s->e의 최단거리보다 크다면 무조건 돌아가야 함! 
    f(s, e, k) = f(s, e, k-1)(하나 돌아가기 가능? -> 불가능) 
    * k > dist(s, e) => k-dist(s, e)는 짝수여야 함. 홀수라면 impossible 리턴
    위 조건을 만족하는 경우 아래와 같이 k를 항상 짝수를 유지하면서 답을 찾아가보자
    f(s, e, k) => (어딘가를 두 번 방문한다면? 흠...k를 바꾸는 것이 아니라 e를 바꿔보자)
    f(s, eu위치, k-1) => 가능하다!
    d -> l ->r-> u로 가야하므로
    eu => er => el => ed 순서대로 impossible이 아닌 것을 찾고 거기에 각각 d, l, r, u를 붙이고 리턴
    넷 다 impossible이라면 impossible리턴
    종료 조건?
    f(s, e, k)에서 s-e 거리 == k라면 사전 순으로 가장 빠른 경로를 만들 것 (돌아가지 않음) v
    s-e가 k보다 작으면 x, k - (s-e)는 짝수여야 함.
    */
    public static class Point {
        int r;
        int c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        int to(Point p) {
            int r= Math.abs(this.r - p.r);
            int c = Math.abs(this.c - p.c);
            return r + c;
        }
        String getPath(Point p) {
            StringBuilder sb = new StringBuilder();
            int r = p.r - this.r;
            int c = p.c - this.c;
            while (r>0) {
                sb.append("d");
                r--;
            }
            while (c<0) {
                sb.append("l");
                c++;
            }
            while (c>0) {
                sb.append("r");
                c--;
            }
            while (r<0) {
                sb.append("u");
                r++;
            }
            return sb.toString();
        }
        boolean inRange() {
            return this.r >= 1 && this.r <= N && this.c >= 1 && this.c <= M;
        }
    }
    public static int N;
    public static int M;
    public String sol(Point s, Point e, int k) {
        int d = s.to(e);
        if (d == k) {
            return s.getPath(e);
        }
        if (d > k || (k-d) % 2 != 0) {
           return "impossible";
        }
        int[][] vector = new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        String[] direction = new String[]{"d", "l", "r", "u"};
        for (int i = 0; i < 4; i ++) {
            int[] v = vector[i];
            String dir = direction[i];
            Point next = new Point(s.r+v[0], s.c+v[1]);
            if (next.inRange()) {
                String nextPath = sol(next, e, k-1);
                if (!nextPath.equals("impossible")) {
                    return dir + nextPath;
                }
            }
        }
        return "impossible";
    }
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        Point s = new Point(x, y);
        Point e = new Point(r, c);
        N = n;
        M = m;
        return sol(s, e, k);
    }
}