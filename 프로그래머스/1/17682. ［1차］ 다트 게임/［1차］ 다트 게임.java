import java.util.*;
class Solution {
    public int solution(String dartResult) {
        int[] point = new int[3];
        int pIdx = -1;
        // String 순회하며 숫자를 찾을 것 ()
        for (int i = 0; i < dartResult.length(); i ++) {
            
            char curr = dartResult.charAt(i);
            if (Character.isDigit(curr)) {
                // 숫자를 찾으면 점수배열의 인덱스를 1 증가시키면서 새로운 배열에 숫자를 저장
                point[++pIdx] = curr - '0'; // char -> int
                // 예외 처리 : 숫자가 두 자리인 경우 다음 인덱스도 확인 필요
                char next = dartResult.charAt(i+1); // 숫자 인덱스가 마지막 인덱스일 수 없으므로 범위체크x
                if (Character.isDigit(next)) {
                    point[pIdx] *= 10;
                    point[pIdx] += next - '0';
                    i++;
                }
                continue;
            }
            if (curr == 'S') {
                // 싱글 -> 1제곱, 점수에 변화 없음
                continue;
            }
            if (curr == 'D') {
                point[pIdx] = (int) Math.pow(point[pIdx], 2);
            }
            if (curr == 'T') {
                point[pIdx] = (int) Math.pow(point[pIdx], 3);
            }
            if (curr == '*') {
                point[pIdx] *= 2;
                if (pIdx > 0) {
                    point[pIdx-1] *= 2;
                }
            }
            if (curr == '#') {
                point[pIdx] *= -1;
            }
            System.out.println(Arrays.toString(point));
        }
        int answer = 0;
        for (int p : point) {
            answer += p;
        }
        return answer;
    }
}