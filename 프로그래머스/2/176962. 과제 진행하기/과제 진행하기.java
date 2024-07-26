import java.util.*;
import java.lang.Math;
class Solution {
    class Task {
        String name;
        int start;
        int left;
        Task(String name, int start, int left) {
            this.name = name;
            this.start = start;
            this.left = left;
        }
    }
    public String[] solution(String[][] plans) {
        
        /**
        * 과제를 시작 시간 순서로 정렬
        * 모든 과제를 순회할 것
        * 중요! 생각해볼 문제 : 연속된 두 과제를 비교하고, 앞선 과제를 완료할지 멈출지 결정한다. 뒤의 과제는 결정에 도움.
        * 시작 시간이 가장 빠른 과제 선택 => 끝나는 시간 변수에 저장 : prev, prevEnd
        * 두 번쨰 배열부터 순회하는 배열의 각 원소마다, 그 시작 시간이 지난 원소의 끝나는 시간(변수)보다 앞선다면
        * 그 시작 시간이 지난 원소의 끝나는 시간과 같다면 스택 업데이트하지 않고 끝낸 과제에 지난 과제를 추가
        * 그 시작 시간이 지난 원소의 끝나는 시간 이후라면 끝낸 과제에 지난 과제를 추가하고 Stack에서 과제 꺼내오기
        * 이름과 PlayTime을 사용해서 빈 자리를 채울 때까지 pop해주며 소모, 끝나면 끝난 과제에 추가
        * 하다가 멈췄다면 playTime 업데이트하고 stack에 다시 넣고 prev, prevEnd 업데이트해줄 것
        * prev의 name, PlayTime을 배열 형태로 Stack에 넣기, prev와 prevEnd 업데이트
        * 
        */
        // Arrays의 sort와 Collections의 sort는 둘 다 평균 시간복잡도 nlogn이지만, Arrays sort는 최악의 경우 n^2의 시간복잡도. 
        
        
        // 새로운 클래스 Task에 과제의 정보들을 담을 것
        List<Task> tasks = new LinkedList<>();
        for (String[] plan : plans) {
            String name = plan[0];
            int start = convertToMin(plan[1]);
            int left = Integer.parseInt(plan[2]);
            tasks.add(new Task(name, start, left));
        }
        // 시작 시간 순서로 정렬
        Collections.sort(tasks, (o1, o2) -> o1.start - o2.start);
        // 멈춘 과제를 넣을 스택 준비
        Stack<Task> st = new Stack<>();
        // 끝낸 과제 이름을 순서대로 넣을 리스트 준비 -> 나중에 배열로 변환
        List<String> finished = new LinkedList<>();
        for (int i = 0; i < tasks.size()-1; i ++) {
            Task curr = tasks.get(i);
            Task next = tasks.get(i+1);
            int empty = next.start - (curr.start + curr.left);
            if (empty >= 0) {
                finished.add(curr.name);
                while (!st.isEmpty()&&empty>0) {
                    Task restart = st.pop();
                    empty -= restart.left;
                    if (empty >= 0) {
                        finished.add(restart.name);
                    } else {
                        restart.left = Math.abs(empty);
                        st.push(restart);
                        empty = 0;
                    }
                }
            } else {
                curr.left = Math.abs(empty);
                st.push(curr);
                empty = 0;
            }
        }
        finished.add(tasks.get(tasks.size()-1).name);
        while (!st.isEmpty()) {
            finished.add(st.pop().name);
        }
        return finished.toArray(String[]::new);
    }
        
        
        
        
//         Arrays.sort(plans, (o1, o2) -> timestampToInt(o1[1]) - timestampToInt(o2[1])); // 시작 시간 순서로 정렬
//         Stack<String[]> st = new Stack<>(); // 멈춘 과제를 넣을 스택
//         String[] prev = plans[0];  // 맨 처음 과제를 첫 지난 과제로 설정
//         int prevEnd = timestampToInt(prev[1]) + Integer.parseInt(prev[2]); // 지난 과제의 끝 시간
//         int count = 0; // 지금까지 끝낸 과제의 개수
//         String[] answer = new String[plans.length]; // 끝낸 과제의 이름을 끝낸 순서대로 저장한 배열
//         for (int i = 1; i < plans.length; i ++) { // 과제는 두 번째부터 ~ 배열 전부를 순회
//             int currStart = timestampToInt(plans[i][1]);    
//             int breakTime = currStart - prevEnd; // 지난 과제 끝 시간과 현재 과제 시작 시간 사이의 비는 시간
//             if (breakTime >= 0) { // 비는 시간이 있거나 0이라면, 지난 과제를 끝내고 빈 자리를 채울 것
//                 answer[count++] = prev[0]; // 지난 과제는 끝낸 것으로 간주하고 이름 저장
//                 while (!st.isEmpty() && breakTime > 0) { // 비는 시간이 있다면 계속 멈췄던 과제를 꺼내서 할 것
//                     String[] popped = st.pop();
//                     int poppedPlay = Integer.parseInt(popped[2]); // 꺼낸 과제 남은 시간
//                     if (poppedPlay < breakTime) { // 꺼낸 과제가 멈추면 playtime 업데이트 후 다시 스택에 집어넣기
//                         poppedPlay -= breakTime; 
//                         st.push(popped);
//                     } else { // 꺼낸 과제를 다 끝낼 경우
//                         breakTime -= poppedPlay; 
//                         answer[count++] = popped[0]; // 꺼낸 과제 끝내고 정답 배열에 추가
//                     }
//                 }
//             } else { // 지난 과제 끝난 시간과 현재 과제 시작 시간 순서 뒤바뀜 (현재 과제가 먼저 시작), 지난 과제 playtime 수정 후 스택에 넣기
//                 prev[2] = Integer.toString(Math.abs(breakTime)); // 부족한 시간 계산 후 형 변환, prev 업데이트
//                 st.push(prev); // stack에 넣기
//             }
//             prev = plans[i]; // 다음 라운드를 위해 현재 과제를 지난 과제로 설정 
//             prevEnd = timestampToInt(prev[1]) + Integer.parseInt(prev[2]); // 지난 과제 끝 시간 업데이트

//         }
//         // 배열을 다 순회한 후, stack에 남아있는 과목이 있다면 모두 꺼내 수행할 것
//         answer[count++] = plans[plans.length-1][0];
//         // 배열을 다 순회했다는 것은 이젠 시작할 새로운 과제가 없다는 뜻 (잘릴 일이 없으므로 순서대로 수행하면 됨)
//         while (!st.isEmpty()) { // 스택에 있는 것을 모두 꺼냄
//             String[] popped = st.pop();
//             answer[count++] = popped[0]; // 끝낸 과제 배열에 과제 이름 추가
//         }
//         return answer;
//     }
    public int convertToMin(String timestamp) {
        String[] hm = timestamp.split(":");
        return Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
    }
}