class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int successTime = 0;
        int attackIndex = 0;
        int completeBandageTime = bandage[0];
        int recoveryEverySec = bandage[1];
        int bonusRecovery = bandage[2];
        for (int sec = 1; sec <= 1000; sec++) {
            if (sec == attacks[attackIndex][0]) {
                answer -= attacks[attackIndex][1];
                if (answer <= 0) {
                    return -1;
                }
                if (attackIndex == attacks.length - 1) {
                    return answer;
                }
                successTime = 0;
                attackIndex++;
                continue;
            }
            answer += recoveryEverySec;
            successTime++;
            if (successTime == completeBandageTime) {
                answer += bonusRecovery;
                successTime = 0;
            }
            answer = Math.min(health, answer);
        }
        return answer;
    }
}