import java.util.*;
class Solution {
    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i ++) {
            int[] node = nodeinfo[i];
            nodeList.add(new Node(node[0], node[1], i+1));
        }
        Collections.sort(nodeList, (o1, o2) -> o2.y - o1.y);
        Tree tree = new Tree(nodeList);
        int[][] answer = {tree.getPreOrderArr(), tree.getPostOrderArr()};
        return answer;
    }
    class Node {
        int x;
        int y;
        int number;
        Node left;
        Node right;
        Node(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }
    class Tree {
        Node root;
        int size;
        List<Integer> preOrderList;
        List<Integer> postOrderList;
        Tree(List<Node> nodes) {
            this.root = nodes.get(0);
            this.size = nodes.size();
            for (int i = 1; i < nodes.size(); i ++) {
                Node prev = root;
                Node curr = nodes.get(i);
                while (true) {
                    if (curr.x < prev.x) {
                        if (prev.left == null) {
                            prev.left = curr;
                            break;
                        }
                        prev = prev.left;
                    } else {
                        if (prev.right == null) {
                            prev.right = curr;
                            break;
                        }
                        prev = prev.right;
                    }
                }
            }
        }
        void preOrder(Node curr) {
            if (curr == null) return;
            this.preOrderList.add(curr.number);
            preOrder(curr.left);
            preOrder(curr.right);
        }
        void postOrder(Node curr) {
            if (curr == null) return;
            postOrder(curr.left);
            postOrder(curr.right);
            this.postOrderList.add(curr.number);
        }
        int[] getPreOrderArr() {
            this.preOrderList = new ArrayList<>();
            this.preOrder(this.root);
            return preOrderList.stream().mapToInt(Integer::intValue).toArray();
        }
        int[] getPostOrderArr() {
            this.postOrderList =  new ArrayList<>();
            this.postOrder(this.root);
            return postOrderList.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}