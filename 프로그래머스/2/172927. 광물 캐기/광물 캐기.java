import java.util.*;
import java.lang.Math;
class Solution {
/** 완전 탐색 => 최소값을 찾아 리턴
광물 수를 n이라고 할 때, 완전탐색 시간 복잡도 : 3^(n/5)이며 n<=50이므로 => 3^k
picks에서 유효 곡괭이 선택 후 현재까지의 피로도 합 업데이트 후 다음으로 넘김. 마지막 미네랄의 피로도 계산하는 경우 최소값 업데이트.
DFS, stack 활용. 곡괭이와 현재 피로도, 깊이 정보를 넣는다 */
    class Path {
        int[] leftPicks;
        int selectedPick;
        int depth;
        int stress;
        Path(int[] leftPicks, int selectedPick, int depth, int stress) {
            this.leftPicks = leftPicks;
            this.selectedPick = selectedPick;
            this.depth = depth;
            this.stress = stress;
        }
    }
    public int solution(int[] picks, String[] minerals) {
        int[][] stressArr = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}}; // 접근 : [곡괭이][광물] 
        int minStress = Integer.MAX_VALUE; // 초기값
        int maxMineralIndex = minerals.length-1; // 0~4 : 0, 5~9: 1
        for (int p = 0; p < 3; p ++) { // 곡괭이 세 가지  
            if (picks[p] <= 0) continue; // 곡괭이 수가 0이하면 탐색하지 않음
            Stack<Path> st = new Stack<>(); // 다음 선택지를 차례로 담을 스택 준비
            Path start = new Path(updatePicks(picks, p), p, 0, 0); // 곡괭이 넘버, 깊이, 피로도 
            st.push(start); // 유효 곡괭이 중 하나를 스택에 넣고 탐색 시작
            while (!st.isEmpty()) { 
                Path curr = st.pop(); 
                // 현재 곡괭이 선택했을 경우 현재 깊이의 광물 스트레스 계산
                for (int i = 0; i < 5; i ++) {
                    int m = curr.depth*5 + i;
                    if (m > maxMineralIndex) break;
                    int currMineral = convertToIndex(minerals[m]);
                    curr.stress += stressArr[curr.selectedPick][currMineral]; // 현재 선택한 곡괭이로 차례인 광물을 캘 때의 피로도 추가
                    if (m == minerals.length-1) {
                        minStress = Math.min(minStress, curr.stress);
                        break;
                    }  // 마지막 광물 피로도 계산 후 최소 피로도 합을 업데이트
                }
                curr.depth ++;
                if (curr.depth*5>maxMineralIndex) continue;
                if (curr.stress >= minStress) continue;
                for (int nextP = 0; nextP < 3; nextP ++) { // 다음을 스택에 추가
                    if (curr.leftPicks[nextP] <= 0) continue;
                    curr.leftPicks = updatePicks(curr.leftPicks, nextP);
                    curr.selectedPick = nextP;
                    st.push(curr);
                }
            }
        }
        return minStress;
    }
    public int convertToIndex(String s) {
        if (s.equals("diamond")) return 0;
        if (s.equals("iron")) return 1;
        if (s.equals("stone")) return 2;
        return -1;
    }
    public int[] updatePicks(int[] picks, int currPickIndex) {
        int[] updated = Arrays.copyOf(picks, 3);
        updated[currPickIndex]--;
        return updated;
    }
}