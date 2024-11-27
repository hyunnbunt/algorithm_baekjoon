class Solution {
    public static int w_lng, w_shrt;
    public int solution(int[] wallet, int[] bill) {
        w_lng = Math.max(wallet[0], wallet[1]);
        w_shrt = Math.min(wallet[0], wallet[1]);
        int answer = 0;
        while (!fit(bill)) {
             bill = new int[]{Math.max(bill[0], bill[1])/2, Math.min(bill[0], bill[1])};
             answer++;
        }
        return answer;
    }
    public boolean fit(int[] bill) {
        int lng = Math.max(bill[0], bill[1]);
        int shrt = Math.min(bill[0], bill[1]);
        return lng <= w_lng && shrt <= w_shrt;
    }
}