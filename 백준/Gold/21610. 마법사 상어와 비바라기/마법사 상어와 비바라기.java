
/** n개의 줄, n개의 정수 => n*n 행렬. 물의 양 저장되어 있음
 * 이동 m 번 명령할 때, m개의 줄에 적힌 d, s대로 순차 적용됨.int[][]로 저장할 것.
 * 비바라기 시전 : n, 1 / n, 2 / n-1, 1 / n-1, 2 에 비구름 생성
 * 방향은 문제 참고. 거리는 방향만큼 이동 횟수라고 보면 됨.
 * 1) 구름 이동 => 입력대로. => 구름 이동 어떻게? 구름 이동할 위치를 q에 넣어두고 순회
 * 2) 구름 이동 위치의 물의 양 1 증가 => 칸 위치 저장되어 있어야 함. q에서 확인
 * 3) 해당 칸마다 대각선 방향에 물의 양이 0 이상인지 확인. 맞다면 개수를 세고 현재 칸에 더하기.
 * => q에서 구름 제거?하지 말자. 나중에 이 칸을 기억해야 함.
 * => 대각선은 마법으로 이동 불가능
 * 아까 3에서 q에 안 들어갔던 애들 중에서 물의 양이 2 이상일 때 -2 된다.
 * 4) 물의 양이 2 이상인 모든 캄에 구름이 생김. 새로운 q에 넣기. 이 칸 물 양 -2.
 * 이때 이미 q에 있는 구름은 넣지 않는다.
 * 해당 칸을 q에서 순회하며 물의 양을 2씩 줄인다.
 * */
import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static int m;
    static int[] dirX;
    static int[] dirY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = nm[0];
        m = nm[1];
        // 물의 양 행렬 입력받기
        int[][] water = new int[n][n];
        for (int i = 0; i < n; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j ++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // m번의 이동 명령 입력받기
        int[][] nextMove = new int[m][2];
        for (int i = 0; i < m; i ++) {
            nextMove[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        }
        // 방향값 지정
        dirX = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
        dirY = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        int[][] startClouds = {{n-1, 0}, {n-1, 1}, {n-2, 0}, {n-2, 1}};
        Queue<int[]> clouds = new LinkedList<>(Arrays.asList(startClouds));
//        printCloudLoc(clouds);
        boolean[][] cloudHistory = new boolean[n][n];
        for (int[] next : nextMove) {
            int di = next[0]-1;
            int si = next[1];
            for (int[] c : clouds) {
                int[] newCloudLoc = move(c, dirX[di], dirY[di], si);
                c[0] = newCloudLoc[0];
                c[1] = newCloudLoc[1];
            }
//             printCloudLoc(clouds);
            // 구름에서 비 내리기, 물 복사
            plusWater(water, clouds, cloudHistory);
//             printWater(water);
            copyWater(water, clouds);
//             printWater(water);
            // 물의 양이 2 이상인 곳에 구름 만들기, 물의 양 감소
             makeClouds(water, clouds, cloudHistory);
//             printCloudLoc(clouds);
//             printWater(water);
        }
        System.out.println(getSumWater(water));
    }

    public static int getSumWater(int[][] water) {
        int sum = 0;
        for (int[] col : water) {
            for (int row : col) {
                sum += row;
            }
        }
        return sum;
    }

    public static void printCloudLoc(Queue<int[]> clouds) {
        System.out.println("Printing cloud locations: ");
        for (int[] c : clouds) {
            int x = c[0]+1;
            int y = c[1]+1;
            System.out.print("(" + x+", " + y + ") ");
        }
        System.out.println();
    }

    public static void printWater(int[][] water) {
        System.out.println("Printing water map: ");
        for (int[] col : water) {
            for (int row : col) {
                System.out.print(row + " ");
            }
            System.out.println();
        }
    }
    public static int[] move(int[] curr, int moveX, int moveY, int count) {
        return magicMove(curr, moveX*count, moveY*count);
    }
    public static int[] magicMove(int[] curr, int moveX, int moveY) {
        int newX = ((curr[0]+moveX)%n+n)%n;
        int newY = ((curr[1]+moveY)%n+n)%n;
        return new int[]{newX, newY};
    }
    public static void plusWater(int[][] water, Queue<int[]> clouds, boolean[][] cloudHistory) {
        for (int[] c : clouds) {
            cloudHistory[c[0]][c[1]] = true;
            water[c[0]][c[1]] ++;
        }
    }
    public static void copyWater(int[][] water, Queue<int[]> clouds) {
        while (!clouds.isEmpty()) {
            int[] c = clouds.poll();
            for (int i = 1; i < dirX.length; i+=2) {
                int nextC = c[0]+dirX[i];
                int nextR = c[1]+dirY[i];
                if (nextC>=0&&nextC<n&&nextR>=0&&nextR<n) {
                    if (water[nextC][nextR] != 0) {
                        water[c[0]][c[1]] ++;
                    }
                }
            }
        }
    }

    public static void makeClouds(int[][] water, Queue<int[]> clouds, boolean[][] cloudHistory) {
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                if (!cloudHistory[i][j] && water[i][j] >= 2) {
//                    System.out.println("water[" + i+"]["+j+"] : " + water[i][j]);
                    clouds.add(new int[]{i, j});
                    water[i][j] -= 2;
                } else {
                    cloudHistory[i][j] = false;
                }
            }
        }
    }
}
