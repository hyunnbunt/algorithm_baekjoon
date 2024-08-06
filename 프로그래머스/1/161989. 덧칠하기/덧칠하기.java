class Solution {
    public int solution(int n, int m, int[] section) { 
        int count = 0;
        int sectionIndex = 0;
        int prevColoredBoundary = 0;
        while (sectionIndex<section.length) {
            int s = section[sectionIndex];
            if (s>prevColoredBoundary) {  
                prevColoredBoundary = s+m-1;
                count++;
            }
            sectionIndex++;
        }
        return count;
    }
}