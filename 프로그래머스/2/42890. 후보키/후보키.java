import java.util.*;
class Solution {
    static String[][] rl;
    static int numOfRows;
    static int numOfCols;
    static boolean[] visited;
    static Set<List<Integer>> candidateKeySet;
    public int solution(String[][] relation) {
        rl = relation;
        numOfRows = relation.length;
        numOfCols = relation[0].length;
        visited = new boolean[numOfCols];
        candidateKeySet = new HashSet<>();
        sol(0);
        return candidateKeySet.size();
    }
    public static void sol(int idx) {
        if (idx >= numOfCols) {
            if (containsAllTuples()) {
                List<Integer> candidateKey = getCandidateKey();
                if (isMinimal(candidateKey)) {
                    candidateKeySet.add(candidateKey);
                }
            }
            return;
        }
        visited[idx] = false;
        sol(idx+1);
        visited[idx] = true;
        sol(idx+1);
    }
    public static boolean containsAllTuples() {
        Set<String> tupleSet = new HashSet<>();
        for (int r = 0; r < numOfRows; r ++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < numOfCols; c ++) {
                if (visited[c]) {
                    sb.append(rl[r][c]);
                }
            }
            tupleSet.add(sb.toString());
        }
        return tupleSet.size() == numOfRows;
    }
    public static boolean isMinimal(List<Integer> key) {
        for (List<Integer> alreadyAdded : candidateKeySet) {
            if (key.containsAll(alreadyAdded)) {
                return false;
            }
        }
        return true;
    }
    public static List<Integer> getCandidateKey() {
        List<Integer> candidateKey = new ArrayList<>();
        for (int i = 0; i < visited.length; i ++) {
            if (visited[i]) {
                candidateKey.add(i);
            }
        }
        return candidateKey;
    }
}