import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] importance;
    static int validMaxImportance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cases; i ++) {
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);
            importance = new int[10];
            int[] impArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Document[] docs = new Document[n];
            for (int j = 0; j < n; j ++) {
                int curr = impArr[j];
                importance[curr] ++;
                docs[j] = new Document(j, curr);
            }
            int result = getPrintIndex(docs, m);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    public static int getPrintIndex(Document[] docs, int indexOfTargetDoc) {
        int count = 0;
        int index = 0;
        int emptyDocs = 0;
        updateValidMaxImportance();
        while (emptyDocs != docs.length) {
            Document curr = docs[index];
            if (curr != null && curr.importance == validMaxImportance) {
                count ++;
                if (index == indexOfTargetDoc) {
                    return count;
                }
                importance[curr.importance] --;
                if (importance[curr.importance] == 0) {
                    updateValidMaxImportance();
                }
                docs[index] = null;
                emptyDocs ++;
            }
            if (index == docs.length - 1) {
                index = 0;
            } else {
                index ++;
            }
        }
        return -1;
    }
    public static void updateValidMaxImportance() {
        for (int i = importance.length - 1; i >= 0; i --) {
            if (importance[i] != 0) {
                validMaxImportance = i;
                return;
            }
        }
    }
    public static class Document {
        int originalOrder;
        int importance;
        Document(int originalOrder, int importance) {
            this.originalOrder = originalOrder;
            this.importance = importance;
        }
    }
}