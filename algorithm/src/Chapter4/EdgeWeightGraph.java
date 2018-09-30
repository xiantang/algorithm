package Chapter4;

import algs4.Bag;
import algs4.In;

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
    public EdgeWeightGraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i <E ; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readFloat();
            Edge edge = new Edge(v,w,weight);
            addEdge(edge);
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(Edge e){
        int v = e.either(),w = e.other(v);
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

    @Override
    public String toString() {
        String res="";
        for (int v = 0; v <V ; v++) {
            res=res+v+":";
            for (Edge e:adj[v]
            ) {
               res=res+e.toString()+"  ";

            }
            res+="\n";
        }
        return res;
//        return super.toString();
    }

    public static void main(String[] args) {
        EdgeWeightGraph edgeWeightGraph = new EdgeWeightGraph(new In());
        System.out.println(edgeWeightGraph.toString());
    }
}
