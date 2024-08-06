import java.lang.Math;
import java.util.*;
class Solution {
    static int E;
    static int[] R;
    static int U;
    public class Node {
        int depth;
        int[] rates;
        Node(int depth, int[] rates) {
            this.depth = depth;
            this.rates = rates;
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        /**
         이모티콘마다 10, 20, 30, 40프로 할인 가능 -> 비율에 따라 사용자들의 행동이 결정됨
         이모티콘 개수 m에 대해서 4^m만큼의 경우의 수 * n명의 유저, 각 상황의 결과 확인
         m은 최대 7의 상수이므로, 전체 시간 복잡도는 n. 전부 확인해도 된다.
         선택가능한 할인율이 적용된 이모티콘의 가격의 배열을 모두 찾기 => DFS => 배열 완성할 때마다 유저 행동 분석
         */
        E = emoticons.length;
        R = new int[]{10, 20, 30, 40};
        U = users.length;
        int members = 0;
        int money = 0;
        Stack<Node> st = new Stack<>();
        st.push(new Node(0, new int[E]));
        while(!st.isEmpty()) {
            Node curr = st.pop();
            int d = curr.depth;
            if (d == E) {
                int[] answer = getResult(emoticons, curr.rates, users);
                if (answer[0] > members || (answer[0] == members && answer[1] > money)) {
                    members = answer[0];
                    money = answer[1];
                }
            } else {
                for (int r : R) {
                    int[] updated = Arrays.copyOf(curr.rates, E);
                    updated[d] = r;
                    st.push(new Node(d+1, updated));
                }
            }

        }
        return new int[]{members, money};
    }
    public static int[] getResult(int[] emoticons, int[] rates, int[][] users) {
        int members = 0;
        int money = 0;
        int[][] userResults = new int[U][2];
        for (int i = 0; i < U; i ++) {
            int buy = users[i][0];
            int membership = users[i][1];
            int[] userResult = userResults[i];
            for (int j = 0; j < E; j++) {
                int r = rates[j];
                if (userResult[1] == 1) {
                    break;
                }
                if (r>=buy) {
                    int discounted = emoticons[j]*(100-r)/100;
                    userResult[0] += discounted;
                    money += discounted;
                    if (userResult[0]>=membership) {
                        userResult[1] = 1;
                        members++;
                        money -= userResult[0];
                    }
                }
            }
        }
        return new int[]{members, money};
    }
}