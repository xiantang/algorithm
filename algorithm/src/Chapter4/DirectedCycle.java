package Chapter4;

import Chapter1.Stack;
import algs4.In;

import java.util.HashMap;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private boolean hasCycle = false;
    private Stack<Integer> cycle;
    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i])dfs(G,i);

        }
    }
    public void dfs(Digraph G,int v){
        marked[v] = true;
        onStack[v] = true;
        for (int w:G.adj(v)
             ) {
            if(hasCycle){
                return;
            }
            else if (!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
            else if (onStack[w]){
                cycle = new Stack<Integer>();
                for (int x = v; x !=w ; x=edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                hasCycle = true;
            }
        }
        onStack[v] = false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(4);
        digraph.addEdge(0,1);
        digraph.addEdge(1,2);
        digraph.addEdge(2,3);
        digraph.addEdge(3,1);
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        for (Integer i:directedCycle.cycle()
             ) {
            System.out.println(i);
        }
    }
}
