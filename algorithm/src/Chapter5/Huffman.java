package Chapter5;

import algs4.BinaryStdIn;
import algs4.BinaryStdOut;
import com.sun.tools.internal.ws.wsdl.document.BindingOutput;

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
    public static void expand() {
        // 读取单词查找树
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for (int i = 0; i <N ; i++) {
            Node x = root;
            // 迭代找到叶子节点的位置
            while (x.isLeaf())
                if (BinaryStdIn.readBoolean())
                    x = x.right;
                else x = x.left;
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();

    }

    private static Node readTrie() {
        if (BinaryStdIn.readBoolean())
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        return new Node('\0', 0, readTrie(), readTrie());
    }
}
