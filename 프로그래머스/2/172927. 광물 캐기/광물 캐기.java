
import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        // 섹션별로 피로도를 정리
        int[][] picksToStone = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int m = minerals.length; 
        int numOfPicks = picks[0] + picks[1] + picks[2];
        List<int[]> sectionInfo = new ArrayList<>();
        for (int i = 0; i < Math.min(m, numOfPicks); i ++) {
            int[] section = new int[3]; // 하나의 섹션에서 각 곡괭이 사용시 피로도 합을 저장
            for (int j = 0; j < 5; j ++) {
                int curr = 5 * i + j;
                if (curr >= m) {
                    break;
                }
                char firstChar = minerals[curr].charAt(0); 
                int mineral = -1;
                switch (firstChar) { // 광물의 종류 첫 번째 글자로 구분
                    case 'd':
                        mineral = 0;
                        break;
                    case 'i':
                        mineral = 1;
                        break;
                    case 's':
                        mineral = 2;
                        break;
                }
                for (int pick = 0; pick < 3; pick ++) {
                   section[pick]+=picksToStone[pick][mineral]; 
                }
            }
            sectionInfo.add(section);
        }
        Collections.sort(sectionInfo, (o1, o2) -> o2[2]-o1[2]);
        int answer = 0;
        for (int[] s : sectionInfo) {
            if (picks[0] > 0) {
                // 다이아 곡괭이가 있다면 우선 선택
                answer += s[0];
                picks[0]--;
                continue;
            }
            if (picks[1] > 0) {
                // 철 곡괭이가 있다면 다이아 다음으로 우선 선택
                answer += s[1];
                picks[1]--;
                continue;
            } 
            if (picks[2] > 0) {
                answer += s[2];
                picks[2]--;
            }
        }
        
        return answer;
    }
}