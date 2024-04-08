
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    /**
     가장 키가 큰 거인을 고르기 : PriorityQueue 이용하기
     pq에서 하나를 뽑아 키를 1/2로 줄이고 다시 pq에 넣기 반복, 최대 t번
     t번 수행 중 가장 키 큰 거인을 다시 뽑아 이때 h보다 작으면 YES, 수행 횟수 출력
     t번 모두 수행 후 가장 키 큰 거인이 h보다 크거나 같으면 NO, 해당 거인의 키 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nht = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < nht[0]; i ++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        // 제일 키 큰 거인을 뽑았을 때 h보다 크거나 같으면 while문 수행
        int tallest = pq.poll();
        int count = 0;
        boolean result = true;
        while (tallest >= nht[1]) {
            if (count == nht[2] || tallest == 1) {
                result = false;
                break;
            }
            count ++;
            pq.offer(tallest / 2);
            tallest = pq.poll();
        }
        StringBuilder sb = new StringBuilder();
        if (result) {
            sb.append("YES").append("\n").append(count);
        } else {
            sb.append("NO").append("\n").append(tallest);
        }
        System.out.println(sb);
    }
}
