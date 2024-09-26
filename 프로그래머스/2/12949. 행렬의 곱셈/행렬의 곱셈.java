class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        for (int r = 0; r < arr1.length; r ++) {
            for (int c = 0; c < arr2[0].length; c ++) {
                for (int i = 0; i < arr2.length; i++) {
                    answer[r][c] += arr1[r][i] * arr2[i][c] ;
                }
            }
        }
        return answer;
    }
}