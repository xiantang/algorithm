package Chapter5;

import Chapter1.Stack;
import Chapter4.Digraph;
import Chapter4.DirectedDFS;
import algs4.Bag;

public class NFA {
    private Digraph G; // 有向图
    private char[] re; // 模式字符串
    private int M;

    public NFA(String regexp) {
        Stack<Integer> ops = new Stack<Integer>();
        re = regexp.toCharArray(); // 转换为字符数组
        M = re.length;
        G = new Digraph(M + 1);
        for (int i = 0; i < M; i++) {  //遍历每个状态
            int lp = i;
            if (re[i] == '(' || re[i] == '|') // 如果是`左括号`或者是`或`就放入
                ops.push(i);
            else if (re[i] == ')') { //如果是`右括号`
                int or = ops.pop();
                if (re[or] == '|') {//吐出的如果是`或`操作的的话
                    // 就需要添加两个ε转换
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else lp = or;
            }
            if (i < M - 1 && re[i + 1] == '*') { // 闭包
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            if (re[i] == '(' || re[i] == '*' || re[i] == ')') // 添加一条边
                G.addEdge(i, i + 1);

        }

    }


    public boolean recognizes(String txt) {

        Bag<Integer> pc = new Bag<Integer>();
        DirectedDFS dfs = new DirectedDFS(G, 0); //深度优先搜索所有 0 可达的状态
        for (int v = 0; v < G.V(); v++) { // 遍历有向图的所有端点
            if (dfs.marked[v]) pc.add(v); // 加入状态集合中
        }
        for (int i = 0; i < txt.length(); i++) {
            //计算所有txt[i+1]可能到达的NFA状态
            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc)
                if (v < M)
                    // 如果是转换匹配 就将下个状态加入集合
                    if (re[v] == txt.charAt(i) || re[v] == '.')
                        match.add(v + 1);
            pc = new Bag<Integer>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked[v]) pc.add(v);

        }
        for (int v : pc
        ) {
            if (v == M) return true; //如果最终状态中有接受状态 就返回true
        }
        return false;
    }

    public static void main(String[] args) {
        String regexp = "dds=sdf";
        NFA  nfa = new NFA(regexp);
        System.out.println(nfa.recognizes("ddsfsdf"));
    }
}
