import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        TreeSet<Integer> sortingList = new TreeSet<>(Comparator.reverseOrder());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i ++) {
            int temp = Integer.parseInt(st.nextToken());
            nums[i] = temp;
            sortingList.add(temp);
        }
        Iterator<Integer> iter = sortingList.descendingIterator();
        HashMap<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        while (iter.hasNext()) {
            ranks.put(iter.next(), rank);
            rank ++;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(ranks.get(num)).append(" ");
        }
        System.out.print(sb);
    }
}