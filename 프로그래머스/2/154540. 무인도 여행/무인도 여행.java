import java.util.*;
class Solution {
    static int h;
    static int w;
    public int[] solution(String[] maps) {
        // 1) 각 칸에 X 또는 숫자 (X면 방문할 필요 없고, 숫자는 sum에 추가)
        // 2) 하나의 무인도를 방문할 때 그 식량의 양을 확인(상하좌우 연결된 모든 노드의 숫자 합)
        // 3) 최대값을 리턴
        // 4) 이차원 배열에 방문 여부를 추가할 것, 바다가 아니면서 방문하지 않은 격자를 탐색할 것
        h = maps.length; // 행
        w = maps[0].length(); // 열
        boolean[][] visited = new boolean[h][w]; // 방문 2차원 배열 생성
        char[][] charMaps = new char[h][w]; 
        for (int i = 0; i < h; i ++) { // String을 char 배열로 변환해서 2차원 배열 생성
            charMaps[i] = maps[i].toCharArray();
        }
        List<Integer> foodList = new ArrayList<>(); // 배열로 리턴해야 하지만, 무인도 개수를 알 수 없으므로 List에 담는다
        for (int i = 0; i < h; i ++) {
            for (int j = 0; j < w; j ++) {
                char curr = charMaps[i][j]; // 현재 확인하고 있는 땅
                if (visited[i][j] || curr == 'X') { // 이미 방문한 곳이거나 바다일 경우 다음 땅으로 넘어갈 것
                    continue;
                }
                Stack<int[]> st = new Stack<>(); // dfs 하기 위해 Stack 생성
                st.push(new int[]{i, j}); // 시작점을 스택에 넣는다
                visited[i][j] = true; // 현재 무인도 시작점은 방문 처리한다 (스택에 넣으면서 방문 처리해야 중복 탐색이 안 됨)
                int[][] vector = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 방향은 상하좌우로 이동
                int food = 0; // 하나의 시작점에서부터 들르는 모든 땅의 식량 수를 저장할 변수. 하나의 무인도의 식량임 (한 땅 방문시마다 업데이트)
                while (!st.isEmpty()) { // 스택이 빌 때까지 하나씩 꺼내서 확인
                    int[] loc = st.pop();
                    food += charMaps[loc[0]][loc[1]]-'0';
                    // System.out.println(charMaps[loc[0]][loc[1]]-'0' + " + ");
                    for (int[] v : vector) {
                        int nextH = loc[0] + v[0];
                        int nextW = loc[1] + v[1];
                        if (!inRange(nextH, nextW) || charMaps[nextH][nextW] == 'X' || visited[nextH][nextW]) { 
                            // 다음 방문할 땅이 범위 바깥이거나 바다이거나 방문한 곳이면 스택에 넣지 않음
                            continue;
                        }
                        st.push(new int[]{nextH, nextW});
                        visited[nextH][nextW] = true; // 스택에 넣으면서 방문 처리해야 스택에 중복으로 같은 땅이 추가되지 않음
                    }
                }
                // System.out.println("food with island at " + i + ", " + j + ": " + food);
                foodList.add(food); // 탐색이 끝난 후 리스트에 식량 수를 추가
                
            }
        }
        // 방문 가능한 무인도가 없다면 (탐색된 식량 정보가 없다면) -1이 담긴 배열을 리턴
        if (foodList.size() == 0) {
            return new int[]{-1};
        }
        Collections.sort(foodList); // 리스트를 먼저 정렬
        int[] answer = new int[foodList.size()];
        for (int i = 0; i < foodList.size(); i ++) {
            answer[i] = foodList.get(i);
        } // 정렬된 순서대로 배열에 담아 리턴
        return answer;
    }
    public boolean inRange(int nextH, int nextW) {
        return nextH >= 0 && nextH < h && nextW >= 0 && nextW < w;
    }
}