import java.util.*;
class Solution {
    public long solution(int[] weights) {
        Arrays.sort(weights);
        Map<Double, Integer> people = new HashMap<>();
        long count = 0;
        for (int w : weights) {
            Double[] target = {w*1.0, (w*3.0)/4.0, (w*1.0)/2.0, (w*2.0)/3.0};
            for (Double t : target) {
                if (people.containsKey(t)) {
                    count += people.get(t);
                }
            }
            people.put(w*1.0, people.getOrDefault(w*1.0, 0)+1);
        }
        return count;
    }
}