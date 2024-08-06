class Solution {
    public int solution(int n, int m, int[] section) { 
        int count = 0;
        int prevColoredBoundary = 0;
        for (int i = 0; i<section.length; i++) {
            if (section[i]>prevColoredBoundary) {  
                prevColoredBoundary = section[i]+m-1;
                count++;
            }
        }
        return count;
    }
}