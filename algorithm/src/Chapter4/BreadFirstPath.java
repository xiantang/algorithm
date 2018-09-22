package Chapter4;

import Chapter1.Stack;
import algs4.In;
import algs4.Queue;

public class BreadFirstPath {
    private boolean[] marked;
    private int edgeTo[];
    private final  int s;

    public BreadFirstPath(Graph graph,int s) {
        this.s = s;
        edgeTo = new int[graph.V()];
        marked = new boolean[graph.V()];
        bfs(graph,s);

    }

    public void bfs(Graph g,int s){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] =true;
        while (!queue.isEmpty()){
            int n = queue.dequeue();
            marked[n] = true;
            for (int v:g.adj(n)
                 ) {
                if (!marked[v]){
                    edgeTo[v] = n;
                    marked[v] = true;
                    queue.enqueue(v);
                }

            }
        }
    }
    public boolean hasPath(int v){
        return marked[v];
    }
    public  Iterable<Integer> pathTo(int v){
        if (!hasPath(v)){
            return null;
        }
        Stack<Integer> stack = new Stack<>();

        for (int i = v; i !=s ; i=edgeTo[i]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }
    public static void main(String[] args) {
        In in = new In();
        Graph graph = new Graph(in);
        BreadFirstPath breadFirstPath = new BreadFirstPath(graph,0);
        for (int i
                :breadFirstPath.pathTo(3)
             ) {
            System.out.println(i);
        }


    }
}
