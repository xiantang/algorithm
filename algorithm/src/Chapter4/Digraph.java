package Chapter4;

import algs4.Bag;

public class Digraph {
    private final int V; //端点
    private int E; //边
    private Bag<Integer>[] adj;

    public  Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V];
        for (int v = 0; v <V ; v++) {
            adj[v] = new Bag<Integer>();
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
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public Digraph reverse(){
        Digraph digraph = new Digraph(V);
//        for (Bag<Integer> bag:adj
//             ) {
//            for (Integer ele:bag
//                 ) {
//                addEdge(ele,);
//            }
//        }
        for (int v = 0; v <V ; v++) {
            for (int  ele:adj[v]
                 ) {
                digraph.addEdge(ele,v);
            }
        }
        return digraph;
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

}
