package Chapter4;

import algs4.Bag;

public class EdgeWeightGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    public EdgeWeightGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v <V ; v++) {
            adj[v] = new Bag<Edge>();
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(Edge e){
        int v = e.either(),w = e.either();
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> edges(){
        Bag<Edge> edges = new Bag<Edge>();
        for (int v = 0; v <V ; v++) {
            for (Edge e:adj[v]
                 ) {
                if (e.other(v)>v)edges.add(e);
            }
        }
        return edges;
    }
}
