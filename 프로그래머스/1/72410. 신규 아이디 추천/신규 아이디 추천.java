class Solution {
    public static boolean isAllowed(char c) {
        if (c == '-' || c == '_' || c == '.') {
            return true;
        }
        return false;
    }
    public String removeNotAllowedCharacters(String s) {
        // 문자, 숫자, -, _, . 제외한 다른 문자 제거
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isDigit(c) && !isAllowed(c)) {
                s = s.replace(Character.toString(c), "");
            }
        }
        return s;
    }
    public String compressDotsInARow(String s) {
        // .이 2번 연속이면 치환
        char[] sCharArr = s.toCharArray();
        for (int i = 1; i < sCharArr.length; i ++) {
            if (sCharArr[i] == '.' && sCharArr[i-1] == '.') {
                sCharArr[i-1] = '*';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : sCharArr) {
            if (c == '*') {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
    public String removeDotsOnBothSide(String s) {
        // .이 문자열 처음이나 끝이라면 제거
        if (s.startsWith(".")) {
            s = s.substring(1);
        }
        if (s.endsWith(".")) {
            s = s.substring(0, s.length()-1);
        }
        return s;
    }
    public String ifEmptyCharA(String s) {
        // 빈 문자열이라면 'a'를 대입
        if (s.length() == 0) {
            return "a";
        }
        return s;
    }
    public String shortenToLength(String s, int maxLen) {
        // 16자 이상이면 초과된 글자 삭제
        if (s.length() > maxLen) {
            return s.substring(0, maxLen);
        }
        return s;
    }
    public String repeatLastCharIfShorterThan(String s, int minLen) {
        // 2자 이하라면 마지막 문자를 반복
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < minLen) {
            char lastChar = sb.charAt(sb.length()-1);
            sb.append(Character.toString(lastChar));   
        }
        return sb.toString();
    }
    public String solution(String new_id) {
        // 대문자를 소문자로 치환
        new_id = new_id.toLowerCase();
        new_id = removeNotAllowedCharacters(new_id);  
        new_id = compressDotsInARow(new_id); 
        new_id = removeDotsOnBothSide(new_id);
        new_id = ifEmptyCharA(new_id);
        new_id = shortenToLength(new_id, 15);
        new_id = removeDotsOnBothSide(new_id);
        new_id = repeatLastCharIfShorterThan(new_id, 3);
        return new_id;
    }
}