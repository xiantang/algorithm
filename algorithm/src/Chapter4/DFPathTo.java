package Chapter4;

import Chapter1.Stack;
import algs4.In;

public class DFPathTo {
    private boolean marked[];
    private int edgeTo[];
    private final  int s;

    public DFPathTo(Graph G,int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    public void bfs(Graph g,int v){
        marked[v] = true;
        for (int w:g.adj(v)
             ) {
           if (!marked[w]){
               edgeTo[w]=v;
               bfs(g,w);

           }
        }

    }



    public boolean hasPathTo(int v){
       return marked[v];
    }
    public  Iterable<Integer> pathTo(int v){
       if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int i = v; i !=s ; i=edgeTo[i]) {
            path.push(i);
        }
        path.push(s);

        return path;

    }

    public static void main(String[] args) {
        In in = new In();
        Graph graph = new Graph(in);
        DFPathTo pathTo=new DFPathTo(graph,0);
        for (int i:pathTo.pathTo(3)
             ) {
            System.out.println(i);
        }



    }
}
