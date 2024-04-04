import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * n개의 스택에 같은 줄의 숫자들을 쌓는다 -> 입력 순서대로 스택에 push하면 스택 가장 위쪽에 해당 줄의 가장 큰 수부터 차례로 위치하게 된다
 * m번째 큰 숫자를 찾을 때, n개의 스택마다 peek()을 실행해 가장 큰 수를 확인하고 모든 스택의 가장 큰 수들 중 가장 큰 수, 즉
 * 모든 n * n 개의 숫자 중 가장 큰 숫자를 찾을 수 있다.
 * 위 과정을 n번 수행하면 n번째 큰 숫자를 찾을 수 있다.
 * 이 경우 시간 복잡도는 n2이다.
 * n은 1500까지로 제한되어 있으므로 1500 * 1500 => 1000000이상이며 100000000이하이므로 1초 이내 수행 가능할 것으로 예상
 * 메모리는 12 MB로 제한되어 있다. 스택 n개를 사용하지만, 정확히 얼마나 메모리가 쓰일지는 모른다.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack[] stacks = new Stack[n];
        for (int s = 0; s < n; s ++) {
            stacks[s] = new Stack<Integer>();
        }
        for (int c = 0; c < n; c ++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int r = 0; r < n; r ++) {
                stacks[r].push(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i = 0; i < n; i ++) {
            int maxRow = -1;
            int maxValue = -1000000001;
            for (int r = 0; r < n; r ++) {
                int currNum = (Integer) stacks[r].peek();
                if (currNum > maxValue) {
                    maxValue = currNum;
                    maxRow = r;
                }
            }
            int temp = (Integer) stacks[maxRow].pop();
            if (i == n-1) {
                System.out.println(temp);
            }
        }
    }
}