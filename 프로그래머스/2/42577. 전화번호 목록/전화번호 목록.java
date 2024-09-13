import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        Arrays.sort(phone_book, (o1, o2) -> o1.length() - o2.length());
        for (String ph : phone_book) {
            for (int len = 1; len <= ph.length(); len++) {
                if (set.contains(ph.substring(0, len))) {
                    return false;
                }
            }
            set.add(ph);
        }
        return true;
    }
}