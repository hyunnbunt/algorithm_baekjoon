import java.util.*;
import java.util.stream.*;
import java.lang.Math;
class Solution {
    public long solution(String expression) {
        List<Long> numbers = Arrays.stream(expression.split("[*, +, -]")).map(Long::parseLong).collect(Collectors.toList());
        List<Character> operators = Arrays.stream(expression.split("[0-9]")).filter(x -> x.length() > 0).map(x -> x.charAt(0)).collect(Collectors.toList()); 
        long answer = 0;
        String[] order = new String[]{"*+-", "*-+", "+*-", "+-*", "-*+", "-+*"};
        for (String or : order) {
            List<Long> n = new ArrayList<>();
            n.addAll(numbers);
            List<Character> c = new ArrayList<>();
            c.addAll(operators);
            for (int i = 0; i < 3; i ++) {
                sol(n, c, or.charAt(i));
            }
            answer = Math.max(answer, Math.abs(n.get(0)));
        }
        return answer;
    }
    public static Long calculate(long n1, long n2, char operator) {
        switch (operator) {
            case '*':
                return n1 * n2;
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
        }
        return null;
    }
    public static void sol(List<Long> numbers, List<Character> operators, char operator) {
        for (int i = 0; i < operators.size(); i ++) {
            if (operators.get(i) == operator) {
                Long result = calculate(numbers.get(i), numbers.get(i+1), operator);
                numbers.remove(i);
                numbers.add(i, result);
                numbers.remove(i+1);
                operators.remove(i);
                i--;
            }
        }
    }
}