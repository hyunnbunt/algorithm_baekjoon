import java.util.Arrays;
class Solution {
    public int solution(int[] numbers) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i ++) {
            arr[i] = i;
        }
        for (int n : numbers) {
            arr[n] = 0;
        }
        return Arrays.stream(arr).sum();
    }
}