package Chapter4;

import Chapter2.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;
    public PrimMST(EdgeWeightGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v <G.V() ; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty()){
            // 删除队列中最小的节点
            visit(G,pq.delMin());
        }
    }
    private void visit(EdgeWeightGraph G,int v){
        marked[v] = true;
        for (Edge e:G.adj(v) // 获取所有的v相邻的节点
             ) {
            int w = e.other(v);
            if (marked[w])continue; // 如果被标记，那么就继续
            if (e.weight()<distTo[w]){
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w,distTo[w]);
                else pq.insert(w,distTo[w]);
            }
        }

    }
}
