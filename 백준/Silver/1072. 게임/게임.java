

import java.util.Scanner;

public class Main {
    /**
     게임 횟수 x => 한 플레이마다 ++
     이긴 게임 y => 한 플레이마다 ++ (무조건 항상 이김)
     승률 z => x / y * 100 %
     x와 y, count = 0 모두를 1씩 올리면서 승률을 구해, 그 값이 z보다 크면 count를 리턴한다
     z는 100% 미만에서 절대 100%가 될 수 없다 / 현재 99% 바로 -1을 리턴.
     현재 100%이면 절대 변할 수 없다 (언제나 승률이 높아짐) / 바로 -1을 리턴.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextInt();
        long y = sc.nextInt();
        int z = (int) ((double) y * 100/ x);
        long play = -1;
        long low = 0;
        long high = x;
        while (low <= high) {
            long mid = (low + high) / 2;
            long newX = x + mid;
            long newY = y + mid;
            int newZ = (int) ((double) newY * 100/ newX);
            if (newZ!= z) {
                play = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(play);
    }
}
