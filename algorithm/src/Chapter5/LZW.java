package Chapter5;

import algs4.BinaryStdIn;
import algs4.BinaryStdOut;


public class LZW {
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 12;

    public static void compress() {
        // 读取需要压缩的字符串
        String input = BinaryStdIn.readString();
        // 创建一个单词查找树
        TST<Integer> st = new TST<>();
        // 将字符表中所有的字符放入单词查找树
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R + 1; // R 为文件结束的(EOF)编码
        while (input.length() > 0) {
            // 找到字符串在单词查找树中的最长的前缀
            String s = st.longestPrefixOf(input);
            BinaryStdOut.write(st.get(s), W);
            int t = s.length();
            // 在符号表中创建新的编码
            if (t < input.length() && code < L) {
                st.put(input.substring(0, t + 1), code++);
                input = input.substring(t);
            }
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();


    }

    public static void expand() {
        String[] st = new String[L];
        int i; // 下一个补全的编码符
        for (i = 0; i < R; i++) {
            st[i] = "" + (char) i;
        }
        st[i++] = " ";
        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        while (true) {
            BinaryStdOut.write(val);
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword)
                s = val + val.charAt(0);
            if (i < L)
                st[i++] = val + s.charAt(0);
            val = s;

        }
        BinaryStdOut.close();


    }
}
