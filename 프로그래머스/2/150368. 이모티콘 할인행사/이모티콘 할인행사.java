import java.lang.Math;
import java.util.*;
class Solution {
    static int numOfEmoticons;
    static int[] discountRate;
    public class Node {
        int depth;
        int[] prices;
        Node(int depth, int[] prices) {
            this.depth = depth;
            this.prices = prices;
        }
    }
    public int[] solution(int[][] users, int[] emoticons) {
        /**
        이모티콘마다 10, 20, 30, 40프로 할인 가능
        그 비율에 따라 사용자들의 행동이 결정됨
        이모티콘 개수 m : 4*m만큼의 경우의 수 * n명의 유저 각각의 행동 분석 => 전체 결과 리턴
        m은 최대 7의 상수이므로, 전체 시간 복잡도는 n이라고 할 수 있음. 전부 확인해볼 것.
        이모티콘의 가격 집합 => 독립적인 경우이며, 각각의 경우마다 user들의 행동을 확인한다
        가능한 가격 배열 생성하기 DFS
        */
        numOfEmoticons = emoticons.length;
        discountRate = new int[]{10, 20, 30, 40};
        int members = 0;
        int money = 0;
        Stack<Node> st = new Stack<>();
        st.push(new Node(0, new int[numOfEmoticons]));
        while(!st.isEmpty()) {
            Node curr = st.pop();
            int indexOfEmoticon = curr.depth; // 처음: 0
            int[] rates = curr.prices; // 처음: {0, 0, 0, 0}
            if (indexOfEmoticon == numOfEmoticons) {
                int[] answer = updateAnswer(emoticons, rates, users);
                members = Math.max(members, answer[0]);
                money = Math.max(members, answer[1]);
            } else {
                for (int dr : discountRate) {
                    int[] updated = Arrays.copyOf(rates, numOfEmoticons);
                    updated[indexOfEmoticon] = dr;
                    st.push(new Node(indexOfEmoticon+1, updated));
                    for (int r : updated) { 
                        System.out.print(r + " ");
                    }
                    System.out.println("-");
                }
            }

        }
        return new int[]{members, money};
    }
    public static int[] updateAnswer(int[] prices, int[] rates, int[][] users) {
        int members = 0;
        int money = 0;
        int[][] userResults = new int[users.length][2];
        for (int i = 0; i < users.length; i ++) {
            int buy = users[i][0];
            int membership = users[i][1];
            int[] userResult = userResults[i];
            for (int j = 0; j < rates.length; j++) {
                int r = rates[j];
                if (userResult[1] == 1) {
                    break;
                }
                if (r>=buy) {
                    userResult[0] += prices[j]*(100-r)/100;
                    money += prices[j]*(100-r)/100;
                    if (userResult[0]>=membership) {
                        userResult[1] = 1;
                        money -= userResult[0];
                    }
                }
            }
        }
        for (int[] ur : userResults) {
            if (ur[1] == 1) {
                members++;
            } else {
                money += ur[0];
            }
        }
        for (int r : rates) {
            System.out.print(r);
        }
        System.out.println("-");
        System.out.println("{" + members + ", " + money + "}");
        return new int[]{members, money};
    }
}