package Chapter4;

import algs4.Bag;
import algs4.In;

import java.util.Iterator;

public class Graph {
    private final int V;   //顶点数目
    private  int E;   //边的数目
    private Bag<Integer>[] adj;    //临街表
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v <V ; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    public Graph(In in){
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i <E ; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }


    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    @Override
    public String toString() {
        String s = V+ " vertices ,"+E +" edges\n";
        for (int v = 0; v <V ; v++) {
            s += v+ ":";
            for (int w:this.adj(v)
                 ) {
                s+= w+" ";
            }
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args) {
        In in = new In();

        Graph graph = new Graph(in);
        System.out.println("OK");
        System.out.println(graph);
    }
}
