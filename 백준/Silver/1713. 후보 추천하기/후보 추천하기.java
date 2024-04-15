import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int r = Integer.parseInt(br.readLine());
        List<int[]> candi = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < r; i ++) {
            int rec = Integer.parseInt(st.nextToken());
            boolean exists = false;
            for (int[] c : candi) {
                if (c[0]==rec) {
                    c[1] ++;
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                if (candi.size()<n) {
                    candi.add(new int[]{rec, 1});
                } else {
                    int min = 1001;
                    int minRec = -1;
                    for (int j = 0; j < candi.size(); j ++) {
                        int[] c = candi.get(j);
                        if (c[1]<min) {
                            min = c[1];
                            minRec = j;
                        }
                    }
                    candi.remove(minRec);
                    candi.add(new int[]{rec, 1});
                }
            }
        }
        candi.sort(Comparator.comparingInt(a -> a[0]));
        StringBuilder sb = new StringBuilder();
        for (int[] c : candi) {
            sb.append(c[0]).append(" ");
        }
        System.out.println(sb);
    }
}
