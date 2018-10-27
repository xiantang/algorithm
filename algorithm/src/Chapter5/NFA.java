package Chapter5;

import Chapter4.Digraph;
import Chapter4.DirectedDFS;
import algs4.Bag;

public class NFA {
    private Digraph G; // 有向图
    private char[] re; // 模式字符串
    private int M;

    public boolean recognizes(String txt) {

        Bag<Integer> pc = new Bag<Integer>();
        DirectedDFS dfs = new DirectedDFS(G, 0); //深度优先搜索所有 0 可达的状态
        for (int v = 0; v <G.V() ; v++) { // 遍历有向图的所有端点
            if (dfs.marked[v])pc.add(v); // 加入状态集合中
        }
        for (int i = 0; i <txt.length() ; i++) {
            //计算所有txt[i+1]可能到达的NFA状态
            Bag<Integer> match = new Bag<Integer>();
            for (int v:pc)
                if (v<M)
                    // 如果是转换匹配 就将下个状态加入集合
                    if (re[v] == txt.charAt(i)||re[v] == '.')
                        match.add(v+1);
            pc = new Bag<Integer>();
            dfs = new DirectedDFS(G,match);
            for (int v = 0; v <G.V() ; v++)
                if (dfs.marked[v])pc.add(v);

        }
        for (int v:pc
             ) {
            if (v==M) return true; //如果最终状态中有接受状态 就返回true
        }
        return false;
    }
}
