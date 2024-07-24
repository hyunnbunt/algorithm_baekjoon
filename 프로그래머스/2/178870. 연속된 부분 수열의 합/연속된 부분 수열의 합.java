class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {-1, -1}; // 답이 없을 경우 초기값
        int start = 0;
        int end = 0;
        int sum =0;
        int minLen = sequence.length + 1; // 적합한 수열 찾으면 길이 업데이트. 초기값은 가능한 길이의 최대값+1로 설정
        sum += sequence[start]; // 두 개의 포인터 중 가장 첫번째 시작 포인터를 더해주고 시작
        while(start < sequence.length && end < sequence.length){ // 시작 포인터와 끝 포인터가 둘 다 시퀀스 범위에 있어야 함
            // 로직 : 현재 시작 포인터와 끝 포인터 범위가 적절하지 않다면 (그 부분 수열의 합이 k보다 크거나 작다면) 포인터를 조정한다
            // 끝 포인터는 시작 포인터 오른편에서부터 작은 숫자~큰 숫자를 차례로 선택하며 범위를 늘리므로, 더 큰 값이 필요할 때 조정 가능하다
            // 시작 포인터는 0~끝포인터의 숫자를 차례로 선택하며 범위를 줄이므로, 더 작은 값이 필요할 떄 조정 가능하다
            if(sum < k){ // 목표보다 현재 범위의 합이 작을 때
                if (end >= sequence.length-1) break; // 마지막 포인터일 경우에는 더 이상 늘릴 수 없으므로 while 문 빠져나간다
                sum += sequence[++end]; // 끝 포인터를 옮긴 다음 끝포인터가 새로 찾은 값을 더해 합을 조정해준다 -> 범위 확인 로직 필요
                // 현재 while 조건문에서 start, end 값을 항상 확인하지만 end 값은 다음 포인터 값을 고려해서 조정하기 때문에, 범위 조정 시에 이미 다음 포인터가 유효한지 확인 필요
                // 하지만 while 조건문을 돌 때마다 end 값이 조정되는 것은 아니고, start만 조정되기 때문에 end포인터가 더 이상 조정 불가능하더라도 조건문은 계속 돌아야 함
                // 그러므로 조건문 안에서 추가적인 끝포인터 다음 포인터가 유효한지 확인하는 로직이 필요함
                continue; 
            }
            if(sum > k){
                sum -= sequence[start++]; // 시작 포인터가 가리키는 값을 빼서 합을 조정하고 시작 포인터를 옮긴다
                if (start > end) {
                    break;
                }
                continue;
            }
            int len = end - start + 1;
            if (len < minLen) { // 현재 찾은 수열이 지금까지 중 가장 짧다면 정답 배열을 수정
                answer[0] = start;
                answer[1] = end;
                minLen = len;
            }
            sum -= sequence[start++]; // 이미 수열을 찾았지만 최소 길이 수열이 아닐 수 있으므로 계속 탐색한다. 시작포인터를 옮겨서 현재 범위 값을 줄이고 탐색.
        }
        return answer;
    }
}