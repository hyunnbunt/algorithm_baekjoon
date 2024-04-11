
import java.io.*;
import java.util.*;

/**
 * x = 0, y = 0 부터 시작. int[] {0, 0}
 * 입력값을 하나씩 확인.
 * x 최대값 최소값 저장할 것
 * y 최대값 최소값 저장할 것
 * 1)현재 향하고 있는 방향 확인할 것 (아래 방향 벡터 인덱스 : 동서남북, 0123)
 * -> 동 : {1, 0}, 북 : {0, 1} / *1, *-1
 * 시작 : 북쪽. 남쪽은 -북쪽으로 함.
 * F일 때 : curr + 북
 * B일 때 : curr - 북
 * 현재 방향이 북쪽일 때
 * L : 북 -> 동, 1 -> -1 => 서쪽 (축 반대, -1)
 * R : 북 -> 동, 1-> 1 => 동쪽
 * **북쪽일 때 L 신경쓸 것 (-1 곱해줄 것) dirIndex:1, vector:1 => verctor 반대
 * 현재 방향이 -북쪽일 때
 * L : 북 -> 동, -1 -> 1 => 동쪽 (축 반대, -1) => vector 반대
 * R : 북 -> 동, -1 -> -1 => 서쪽
 * **남L
 * 현재 방향이 동쪽일 때
 * L : 동 -> 북, 1 -> 1 => 북쪽 (축 반대)
 * R : 동 -> 북, 1 -> -1 => 남쪽 (축 반대, -1) vector 반대
 * **동R
 * 현재 방향이 -동쪽일 때
 * L : 동 -> 북, -1 -> -1 => 남쪽
 * R : 동 -> 북, -1 -> 1 => 북쪽 (축 반대, -1) vector 반대
 * **서R
 * 북쪽일 때는
 * 현재 방향은 북쪽, 3 =>
 * char vector = 'L'
 * 나쪽일 때는 vector= 'R
 * vector 이면, - 해준다.
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < cases; c ++) {
            String keys = br.readLine();
            sb.append(getSquareSize(keys.toCharArray())).append("\n");
        }
        System.out.println(sb);
    }

    public static int getSquareSize(char[] keys) {
        int[] curr = {0, 0};
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        int[][] move = {{1, 0}, {0, 1}};
        // x축 움직일 때 : +-move[0], y축 움직일 때 : +-move[1]
        int dirIndex = 1;
        int vector = 1;
        for (char key : keys) {
            int[] currDir = move[dirIndex];
            if (key == 'F' || key == 'B') {
                int tempVector = vector;
                if (key == 'B') {
                    tempVector *= -1;
                }
                curr[0] += currDir[0] * tempVector;
                curr[1] += currDir[1] * tempVector;
                minX = Math.min(curr[0], minX);
                minY = Math.min(curr[1], minY);
                maxX = Math.max(curr[0], maxX);
                maxY = Math.max(curr[1], maxY);
            } else {
                if ((key == 'L' && dirIndex == 1) || (key == 'R' && dirIndex == 0)) {
                    vector *= -1;
                }
                dirIndex = Math.abs(dirIndex - 1);
            }
        }
        return (Math.abs(minX)+maxX)*(Math.abs(minY)+maxY);
    }
}
