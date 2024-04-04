import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] order = new String[n];
        Map<String, Integer> cars = new HashMap<>();
        for (int i = 0; i < n; i ++) {
            String temp = br.readLine();
            order[i] = temp;
            cars.put(temp, i);
        }
        Set<String> prevCars = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < n; i ++) {
            String temp = br.readLine();
            int c = cars.get(temp);
            if (c > i) {
                sum ++;
            } else {
                for (int k = 0; k < c; k ++) {
                    if (!prevCars.contains(order[k])) {
                        sum ++;
                        break;
                    }
                }
            }
            prevCars.add(temp);
        }
        System.out.println(sum);
    }
}