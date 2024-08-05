import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        /** 
        ext 값이 val_ext보다 작아야 한다 => sort_by 값 기준 오름차순 정렬
        */
        Map<String, Integer> extMap = new HashMap<>();
        extMap.put("code", 0);
        extMap.put("date", 1);
        extMap.put("maximum", 2);
        extMap.put("remain", 3);
        int extIndex = extMap.get(ext);
        List<int[]> lis = new ArrayList<>();
        for (int[] d : data) {
            int value = d[extIndex];
            if (value < val_ext) {
                lis.add(d);
            }
        }
        int sortIndex = extMap.get(sort_by);
        Collections.sort(lis, (o1, o2) -> o1[sortIndex] - o2[sortIndex]);
        int[][] answer = lis.toArray(new int[lis.size()][4]);
        return answer;
    }
}