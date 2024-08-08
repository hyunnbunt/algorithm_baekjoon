import java.util.*;
class Solution {
    static String[] p;
    static int n;
    static int m;
    static Map<Character, int[]> dirMap;
    public int[] solution(String[] park, String[] routes) {
        p = park;
        n = park.length;
        m = park[0].length();
        dirMap = new HashMap<>();
        dirMap.put('W', new int[]{0, -1});
        dirMap.put('E', new int[]{0, 1});
        dirMap.put('N', new int[]{-1, 0});
        dirMap.put('S', new int[]{1, 0});
        int[] curr = {-1, -1};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                char ch = readPark(r, c);
                if (ch == 'S') {
                    curr[0] = r;
                    curr[1] = c;
                    System.out.println("start from: " + r + ", " + c);
                }
            }
        }
        for (String r : routes) {
            char dir = r.charAt(0);
            int move = r.charAt(2)-'0';
            curr = followRoute(curr, dir, move);
        }
        return curr;
    }
    static char readPark(int r, int c) {
        return p[r].charAt(c);
    }
    static boolean inRange(int r, int c) {
        return r>=0 && c>=0 && r<n && c<m;
    }
    static int[] followRoute(int[] curr, char dir, int move) {
        int[] v = dirMap.get(dir);
        int currR = curr[0];
        int currC = curr[1];
        while (move > 0) {
            int nextR = currR + v[0];
            int nextC = currC + v[1];
            if (inRange(nextR, nextC) && readPark(nextR, nextC) != 'X') {
                currR = nextR;
                currC = nextC;
                move--;
            } else {
                return curr;
            }
            System.out.println(currR + ", " + currC);      
        }
        return new int[]{currR, currC};
    }
}