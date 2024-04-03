import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        /**
         * 주어진 str의 char를 순차적으로 순회 (의 짝 )가 항상 존재하는지 확인
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i ++) {
            String str = br.readLine();
            if (isVPS(str)) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    public static boolean isVPS(String str) {
        int len = str.length();
        int open = 0;
        for (int i = 0; i < len; i ++) {
            if (str.charAt(i) == '(') {
                open ++;
            } else {
                open --;
                if (open < 0) {
                    return false;
                }
                if (open == 0 && i == len - 1) {
                        return true;
                }
            }
        }
        return false;
    }
}