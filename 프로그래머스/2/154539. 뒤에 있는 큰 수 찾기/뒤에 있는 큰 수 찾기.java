import java.util.Stack;
class Solution {
    public int[] solution(int[] numbers) {
        // 숫자 배열 중 멀리 있는, 가까운 것보다 작은 수들은 제거하면서 차례대로 스택에 추가할 것
        // 두 가지 기준 충족해야 함 : 크다/제일 가깝다
        // 1) 크다 : 기준만 충족하면 됨
        // 2) 제일 가깝다 :  정렬이 필요 -> 스택에서 꺼내는 순서.
        int n = numbers.length;
        int[] answer = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int l = n-1; l >=0; l--) {
            int left = numbers[l];
            boolean found = false;
            while (!st.isEmpty()) {
                int right = st.peek();
                if (left >= right) { // right가 뒷 큰수가 아니라면 right를 제거해서 right보다 멀리있는 수들을 확인. 
                    st.pop();
                    // 다음 left 원소에 대해서 현재 left가 더 크다면 left가 뒷 큰수. 
                    // 그렇지 않다면 right는 left보다 작으므로 어차피 다음 left원소에 대해서도 뒷 큰수가 될 수 없.
                } else { // right가 뒷 큰수라면 right를 정답 배열에 저장할 것
                    answer[l] = right;
                    found = true;
                    break;
                }
            }
            if (!found || l == n-1) { // 뒷 큰수를 찾지 못했거나 마지막 원소일 경우에는
                answer[l] = -1;
            }
            st.push(left); // 현재 left를 다음 원소의 right로 만들기 위해 스택에 추가해줌
        }           
        return answer;
    }
}