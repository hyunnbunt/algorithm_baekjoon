class Solution {
    public String solution(String p) {
        return convert(p);
    }
    public static String convert(String str) {
        if (str.length() == 0) {
            return str;
        }
        int curr = 0;
        int left = 0;
        while (curr < str.length()) {
            if (str.charAt(curr) == '(') {
                left++;
            } else {
                left--;
            }
            curr++;
            if (curr > 0 && left == 0) {
                break;
            }
        }
        String u = str.substring(0, curr);
        String v = convert(str.substring(curr));
        StringBuilder sb = new StringBuilder();
        if (isRight(u)) {
            return sb.append(u).append(convert(v)).toString();
        } else {
            sb.append("(").append(v).append(")");
            for (int i = 1; i < u.length()-1; i ++) {
                if (u.charAt(i) == '(') {
                    sb.append(")");
                } else {
                    sb.append("(");
                }
            }
            return sb.toString();
        }
    }
    public static boolean isRight(String str) {
        int left = 0;
        for (char ch : str.toCharArray()) {
            if (ch=='(') {
                left++;
            } else {
                left--;
            }
            if (left < 0) {
                return false;
            }
        }
        return true;
    }
}