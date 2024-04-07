/*


첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다. 각 테스트 케이스는 반복 횟수 R(1 ≤ R ≤ 8), 문자열 S가 공백으로 구분되어 주어진다. S의 길이는 적어도 1이며, 20글자를 넘지 않는다.
출력

각 테스트 케이스에 대해 P를 출력한다.

*/

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        for (int i = 0; i < n; i += 1) {
            int m = Integer.parseInt(sc.next());
            char[] charArray = sc.next().toCharArray();
            for (char c : charArray) {
                for (int j = 0; j < m; j += 1) {
                    bw.write(c);
                }
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
