import java.util.*;
class Solution {
    public static String n;
    public int solution(String numbers) {
        n = numbers;
        Set<Integer> set = new HashSet<>();
        sol(0, new boolean[n.length()], set);
        int answer = 0;
        for (int s : set) {
            if (isPrime(s)) {
                answer ++;
            }
        }
        return answer;
    }
    public static void sol(int idx, boolean[] selected, Set<Integer> set) {
        if (idx >= selected.length) {
            List<String> selectedList = new ArrayList<>();
            for (int i = 0; i < selected.length; i ++) {
                if (selected[i]) {
                    selectedList.add(n.charAt(i) + "");
                }
            }
            updateSet(selectedList, set);
            return;
        }
        selected[idx] = true;
        sol(idx+1, selected, set);
        selected[idx] = false;
        sol(idx+1, selected, set);
    }
    public static void updateSet(List<String> digits, Set<Integer> set) {
        for (int i = 0; i < digits.size(); i ++) { 
            Stack<Select> st = new Stack<>();
            st.push(new Select(digits.get(i), i, Integer.toString(i)));
            while (!st.isEmpty()) {
                Select curr = st.pop();
                if (curr.visited.length() == digits.size()) {
                    StringBuilder sb = new StringBuilder();
                    for (char idx : curr.visited.toCharArray()) {
                        sb.append(digits.get(idx-'0'));
                    }
                    set.add(Integer.parseInt(sb.toString()));
                    continue;
                }
                for (int j = 0; j < digits.size(); j ++) {
                    if (curr.visited.indexOf(Character.forDigit(j, 10)) == -1) {
                        st.push(new Select(digits.get(j), j, curr.visited + j));
                    }
                }
            }
        }
    }
    public static class Select {
        String digit;
        int index;
        String visited;
        Select(String digit, int index, String visited) {
            this.digit = digit;
            this.index = index;
            this.visited = visited;
        }
    }
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i ++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }   
}