import java.util.*;
class Solution {
    public String calculateExpression(String[] exp, int type) {
        try {
            int result = Integer.parseInt(exp[0], type);
            String plusMinus = exp[1];
            int adder = Integer.parseInt(exp[2], type);
            if (plusMinus.equals("-")) {
                adder *= -1;
            }
            result += adder;
            return Integer.toString(result, type);
        } catch(NumberFormatException e) {
            return null;
        }
    }
    public void filter(String[] exp, boolean[] types) {
        for (int i = 2; i <= 9; i ++) { // 2진법~9진법
            if (!types[i]) {
                continue;
            }
            String result = calculateExpression(exp, i);
            if (result == null) {
                types[i] = false;
            } else if (!exp[4].equals("X") && !result.equals(exp[4])) {
                types[i] = false;
            }
        }
    }
    public String deductAnswer(String exp, boolean[] types) {
        Set<String> result = new HashSet<>();
        for (int i = 2; i <= 9; i ++) {
            if (!types[i]) {
                continue;
            }
            result.add(calculateExpression(exp.split(" "), i));
        }
        if (result.size() == 1) {
            for (String r : result) {
                return r;
            }
        }
        return "?";
    }
    public String[] solution(String[] expressions) {
        List<String> answers = new ArrayList<>(); 
        boolean[] types = new boolean[10];
        Arrays.fill(types, true);
        for (String exp : expressions) {
            String[] parsed = exp.split(" "); // 공백으로 구분
            filter(parsed, types);
            if (parsed[4].equals("X")) {
                answers.add(exp);
            }
        }
        String[] results = new String[answers.size()];
        for (int i = 0; i < answers.size(); i ++) {
            String curr = answers.get(i);
            results[i] = curr.replace("X", deductAnswer(curr, types));
        }
        return results;
    }
}