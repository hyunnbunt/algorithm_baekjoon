import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        TreeSet<Integer> resultSet = new TreeSet<>();
        for (int i = 0; i < numbers.length; i ++) {
            for (int j = i + 1; j < numbers.length; j ++) {
                resultSet.add(numbers[i] + numbers[j]);
            }
        }
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
}}