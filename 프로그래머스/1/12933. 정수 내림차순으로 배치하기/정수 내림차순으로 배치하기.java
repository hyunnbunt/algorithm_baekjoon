import java.util.*;
class Solution {
    public long solution(long n) {
        char[] charArr = Long.toString(n).toCharArray();
        Arrays.sort(charArr);
        StringBuilder sb = new StringBuilder();
        for (int i = charArr.length-1; i >= 0; i --) {
            char ch = charArr[i];
            sb.append(ch+"");
        }
        return Long.parseLong(sb.toString());
    }
}