package Chapter5;

import algs4.BinaryStdIn;

public class Huffman {
    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            // 指向其他Node对象的引用
            this.left = left;
            this.right = right;
        }

        // 判断是否是叶子节点
        public boolean isLeaf() {
            return left == null && right == null;

        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }
    // 使用前缀码展开
    public static void expand(){
        Node root = readTrie();


    }

    private static Node readTrie() {
        if (BinaryStdIn.readBoolean())
            return new Node(BinaryStdIn.readChar(),0,null,null);
        return new Node('\0',0,readTrie(),readTrie());
    }
}
