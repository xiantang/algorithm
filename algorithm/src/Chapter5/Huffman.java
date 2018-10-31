package Chapter5;

import algs4.BinaryStdIn;
import algs4.BinaryStdOut;
import algs4.MinPQ;
import com.sun.tools.internal.ws.wsdl.document.BindingOutput;

public class Huffman {
    private static final int R = 256; // ASCII字母表

    private static class Node implements Comparable<Node> {

        private char ch; // 存储字符
        private int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            // freq 表示在输入流中出现的频率
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
        for (int i = 0; i < N; i++) {
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

    //  霍夫曼压缩
    public static void compress() {
        String s = BinaryStdIn.readString();
        // 读取输入
        char[] input = s.toCharArray();
        // 统计频率
        int freq[] = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;
        // 构造霍夫曼编码树
        Node root = builtTrie(freq);
        // 递归的构造编译表
        String st[] = new String[R];
        buildCode(st, root, "");

        // 递归的打印解码用的单词查找树
        writeTrie(root);

        // 打印字符串总数
        BinaryStdOut.write(input.length);

        // 使用霍夫曼编码处理输入
        for (int i = 0; i <input.length ; i++) {
            String code = st[input[i]]; // 找到字符对应的比特码
            for (int j = 0; j <code.length() ; j++)
                if(code.charAt(j)=='1')
                    BinaryStdOut.write(true);
                else BinaryStdOut.write(false);
        }
        BinaryStdOut.close();
    }

    // 输出查找树中的比特字符串
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    // 构造单词查找树编译表
    public static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;

    }


    public static void buildCode(String[] st, Node x, String s) {

        if (x.isLeaf()) {// 如果是叶子节点
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');

    }

    // 构造单词查找树
    private static Node builtTrie(int[] freq) {
        // 使用多棵单节点树初始化优先级队列
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char c = 0; c < R; c++)
            // 遍历所有的在字母表中的c
            // 如果他的频率大于0 就说明存在这个字母
            if (freq[c] > 0)
                pq.insert(new Node(c, freq[c], null, null));

        // 那么现在就存在了一个都是单独节点的森林了

        // 由底部向上构造查找树
        // 从队列吐出两个频率最小的节点
        // 构造一个空的节点做为他们的父亲
        // 并且插入队列
        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node z = pq.delMin();
            Node parent = new Node('\0', x.freq + z.freq, x, z);
            pq.insert(parent);

        }
        return pq.delMin();
    }

    private static Node readTrie() {
        if (BinaryStdIn.readBoolean())
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        return new Node('\0', 0, readTrie(), readTrie());
    }
}
