import java.util.*;

class Solution {
    Point[] dp;
    public int[] solution(int e, int[] starts) {
        int[] answer = {};
        dp = new Point[e+1]; // 1~e까지 약수의 개수를 저장
        
        for(int i=0; i<=e; i++){
            dp[i] = new Point(i, 0); // i, i가 어떤 수의 배수가 되는 경우의 수 카운트
        }
        
        for(int i=1; i<=e; i++){ 
            // i -> 1부터 e까지 모든 숫자를 순회
            for(int j=i; j<=e; j+=i){
                // i를 i만큼 증가시키면서 e까지 순회 -> i, 2i, 3i, ...ki :
                dp[j].c++;
            }
        }
        
        // Point : count는 큰 -> 작은, num은 작은 -> 큰 정렬
        Arrays.sort(dp, new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2){
                if (o1.c == o2.c) {
                    return o1.num - o2.num;
                }
                return o2.c - o1.c;
            }
        });          
        
        answer = new int[starts.length];
        int i = 0;
        for(int start : starts) {
            for(Point p : dp) {
                if(start <= p.num){
                    answer[i++] = p.num;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    static class Point{
        int num, c;
        Point(int num, int c){
            this.num = num;
            this.c = c;
        }
    }
}