package Chapter4;

import algs4.Queue;

public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;
    public BellmanFordSP(EdgeWeightDigraph G,int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<Integer>();
        for (int v = 0; v <G.V() ; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        onQ[s] = true;
        while (!queue.isEmpty()&&!hasNgativeCycle()){
            int v = queue.dequeue(); // 吐出一个元素
            onQ[v] = false;
            relax(G,v);
        }
    }
    private void relax(EdgeWeightDigraph G,int v){
        for (DirectedEdge e:G.adj(v)) {
            int w = e.to();
            //如果比他小
            if (distTo[w] > distTo[v]+e.getWeight()){
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if (!onQ[w]){
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            if (cost++%G.V()==0)
                findNegativeCycle();
        }
    }
    private void  findNegativeCycle(){
        int V = edgeTo.length;
        EdgeWeightDigraph spt;
        spt = new EdgeWeightDigraph(V);
        for (int v = 0; v < V ; v++)
            if (edgeTo[v]!=null)
                spt.addEdge(edgeTo[v]);

    }
    private boolean hasNgativeCycle(){

    }
    private Iterable<DirectedEdge> negativeCycle(){

    }



}
