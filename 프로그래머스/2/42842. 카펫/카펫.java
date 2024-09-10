class Solution {
    public int[] solution(int brown, int yellow) {
        int aPlusB = (brown - 4) / 2; // yellow : a * b
        for (int a = 1; a <= Math.sqrt(yellow); a ++) {
            if (a * (aPlusB - a) == yellow) {
                return new int[]{aPlusB - a+2, a+2};
            }
        }
        return new int[]{};
    }
}