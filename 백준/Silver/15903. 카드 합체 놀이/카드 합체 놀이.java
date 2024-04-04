import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /**
         * 모든 카드의 숫자들 중 가장 작은 카드 두 개를 선택하기만 하면
         * 결과적으로 가장 작은 점수를 얻을 수 있다.
         * 가장 작은 것을 바로 찾아낼 수 있는 minHeap을 사용하는데,
         * 카드를 두 번 꺼낸 후 서로 양측에 더한 새로운 두 개의 숫자를 계산해
         * 이 두 개의 숫자를 heap에 넣어야 순서가 업데이트된다.
         * 이후 가장 작은 숫자 두 개를 꺼내는 등의 일련의 과정을 반복하면 된다.
         * m번의 게임 play이므로 위 과정을 m회 반복해 전체 점수를 출력한다.
         * 전체 점수는 초기값을 데이터 입력 시에 설정하고, 매 loop마다 업데이트한다.
         */
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long sum = 0;
        for (int i = 0; i < n; i ++) {
            long temp = Integer.parseInt(st.nextToken());
            pq.offer(temp);
            sum += temp;
        }
        for (int i = 0; i < m; i ++) {
            long x = pq.poll();
            long y = pq.poll();
            long newCard = x + y;
            sum += newCard;
            pq.offer(newCard);
            pq.offer(newCard);
        }
        System.out.println(sum);
    }
}