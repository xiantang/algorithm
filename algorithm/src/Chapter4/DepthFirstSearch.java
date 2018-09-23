package Chapter4;

import algs4.In;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph g,int s){
        marked = new boolean[g.V()];
        dfs(g,s);
    }

    public boolean marked(int v){
        return marked[v];
    }

    public void dfs(Graph G,int s){
        marked[s] = true;
        count++;
        for (int v:G.adj(s)
             ) {
            if (!marked[v])dfs(G,v);
        }
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In());
        DepthFirstSearch search = new DepthFirstSearch(graph,1);
        System.out.println(search.count());
    }
}
