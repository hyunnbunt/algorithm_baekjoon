import java.util.*;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> winSet = new HashSet<>();
        for (int w : win_nums) {
            winSet.add(w);
        }
        int numOfZeros = 0;
        int numOfWins = 0;
        for (int l : lottos) {
            if (l == 0) {
                numOfZeros ++;
                continue;
            }
            if (winSet.contains(l)) {
                numOfWins ++;
            }
        }
        int bestResult = Math.min(Math.max(7 - (numOfWins + numOfZeros), 1), 6);
        int worstResult = Math.min(7 - numOfWins, 6);
        return new int[]{bestResult, worstResult};
    }
}