package Chapter4;

import Chapter2.IndexMinPQ;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    public DijkstraSP(EdgeWeightDigraph G,int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v <G.V() ; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0; // 起点的位置
        pq.insert(s,0.0);
        while (!pq.isEmpty())
            relax(G,pq.delMin());
    }
    private void relax(EdgeWeightDigraph G,int v){
        for (DirectedEdge e:G.adj(v)
             ) {
            int w = e.to();
            // 如果s到v的权重加上v到w的权重 小于w的权重
            if (distTo[w]>distTo[v]+e.getWeight()){
                // 更新权重
                distTo[w] = distTo[v]+e.getWeight();
                // 更新到达w的边e
                edgeTo[w] = e;
                if (pq.contains(w)){
                    pq.change(w,distTo[w]);
                }
                else pq.insert(w,distTo[w]);
            }
        }
    }
}
