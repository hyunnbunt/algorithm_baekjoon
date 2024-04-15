
import java.io.*;
import java.util.*;
public class Main {
    /**
     * dfs 문제.
     * 특정 상황에서 두 가지 경우의 수를 선택 가능.
     * 두 가지 방법 중 하나를 선택하는 것을 반복하되,
     * 조건을 만족하거나 만족하지 않음을 알 수 있을 때까지만 반복한다.
     * 조건을 만족할 경우 =>
     * '몇 번의 선택이 있었는지'로 답을 찾거나,
     * 단순히 조건을 만족하는 경우가 있음을 확인할 수 있다.
     * 조건을 만족하지 않을 경우 =>
     * 더 이상 선택하지 않음으로써 현재까지 모든 선택의 경로에 대해 더 이상 확인하지 않을 수 있고,
     * 위 조건으로 인해서 선택할 수 있는 경우의 수가 하나도 없어질 경우
     * 조건을 만족하는 경우가 없음을 확인할 수 있다.
     * 현재 문제에서는 주어진 S를 가지고, 뒤에 A를 추가하거나, 문자열을 뒤집고나서 앞에 B를 추가한다.
     * 위 선택을 반복하다가 s의 길이가(항상 늘어남) T보다 커질 때, T와 같아진 적이 없다면
     * 조건을 만족하지 않는 경우라고 보고, 더 이상 탐색하지 않는다.
     * T와 동일한 문자열을 만드는 경우를 찾는 순간 1을 리턴한다.
     * dfs - 재귀 또는 stack을 활용한다.
     * stack을 활용하기.
     * => 시간 초과.
     * 길이 50일 때 dfs => 50번. 모두 끝까지 가볼 필요 없이, 미리 끝내는 경우 확인해볼 것.
     * 2^50 번의 dfs. 너무 많다.
     * t에 s가 포함되어 있거나 s'가 포함되어 있거나.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        Stack<String> stack = new Stack<>();
        stack.push(s);
        HashSet<String> checked = new HashSet<>();
        int l = t.length();
        while (!stack.isEmpty()) {
            String curr = stack.pop();
            if (curr.equals(t)) {
                System.out.println(1);
                return;
            }
            // dfs 할 필요 없는 경우? 
            // 1) 이미 확인한 문자열일 경우
            // 2) target에 curr 또는 curr을 뒤집은 문자열이 포함되지 않은 경우
            String reversedCurr = new StringBuilder(curr).reverse().toString();
            if (!checked.contains(curr) && (t.contains(curr)||t.contains(reversedCurr))) {
                checked.add(curr);
                stack.push(curr+'A'); // 뒤에 'A'를 붙임
                stack.push(new StringBuilder(curr).append('B').reverse().toString()); // 뒤에 'B'를 붙이고 뒤집음
            }
        }
        System.out.println(0);
    }
}
