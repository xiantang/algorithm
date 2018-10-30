package Chapter5;

public class Huffman {
    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;

            //指向其他Node对象的引用
            this.left = left;
            this.right = right;
        }

        // 判断是否是叶子节点
        public  boolean isLeaf(){
            return left == null && right == null;

        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }
}
