import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        // 이전 원소와 다르거나, 첫 원소일 때만 List에 저장한다
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i ++) {
            if (arr[i] != arr[i-1]) {
                list.add(arr[i]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}