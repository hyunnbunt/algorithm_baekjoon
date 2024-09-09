import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people); 
        int heavy = people.length - 1;
        int light = 0;
        int boatCount = 0;
        while (heavy > light) {
            if (people[heavy] + people[light] <= limit) {
                heavy--;
                light++;
            } else {
                heavy--;
            }
            boatCount++;
        }
        if (heavy == light) {
            boatCount++;
        }
        return boatCount;
    }
}