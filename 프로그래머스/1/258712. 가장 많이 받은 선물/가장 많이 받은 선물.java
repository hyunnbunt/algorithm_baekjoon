import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int[] result = new int[n];
        int[] points = new int[n];
        HashMap<String, Integer> nameToIndex = new HashMap<>();
        int friendIndex = 0;
        for (String friend : friends) {
            nameToIndex.put(friend, friendIndex);
            friendIndex ++;
        }
        int[][] history = new int[n][n];
        for (String gift : gifts) {
            String[] g = gift.split(" ");
            int giver = nameToIndex.get(g[0]);
            int getter = nameToIndex.get(g[1]);
            history[giver][getter] ++;
            points[giver] ++;
            points[getter] --;
        }
        for (int i = 0; i < n; i ++) {
            for (int j = i+1; j < n; j ++) {
                int iToJ = history[i][j] - history[j][i];
                if (iToJ > 0) {
                    result[i] ++;
                } else if (iToJ < 0) {
                    result[j] ++;
                } else {
                    int iWinsJ = points[i] - points[j];
                    if (iWinsJ > 0) {
                        result[i] ++;
                    } else if (iWinsJ < 0) {
                        result[j] ++;
                    }
                }
            }
        }
        int max = 0;
        for (int r : result) {
            if (r > max) {
                max = r;
            }
        }
        return max;
    }
}