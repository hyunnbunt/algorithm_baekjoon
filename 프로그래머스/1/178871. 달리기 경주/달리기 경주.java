import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> pMap = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            pMap.put(players[i], i);
        }
        /** 
        이름 불리면 pMap에서 players 배열 인덱스를 찾고, 그 앞에 있는 player와 순서 바꾸기
        => pMap도 변경해줘야 한다 
        */
        for (String winningPlayer : callings) {
            int winningIndex = pMap.get(winningPlayer);
            int losingIndex = winningIndex-1;
            String losingPlayer = players[losingIndex];
            players[losingIndex] = players[winningIndex];
            players[winningIndex] = losingPlayer;
            pMap.put(winningPlayer, winningIndex-1);
            pMap.put(losingPlayer, losingIndex+1);
        }
        return players;
    }
}