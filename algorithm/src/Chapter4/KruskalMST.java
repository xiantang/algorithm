package Chapter4;

import algs4.MinPQ;
import algs4.Queue;
import algs4.UF;

public class KruskalMST {
    private Queue<Edge> mst;
    public KruskalMST(EdgeWeightGraph G){
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e:G.edges()
             ) {
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty()&&mst.size()<G.V()-1){
            //如果pq是空的或者mst的边小于G的点数
            Edge e = pq.delMin(); // 删除最小的边
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v,w)){
                continue;
            }//继续
            uf.union(v,w);//否则就连接两个
            mst.enqueue(e);

        }
    }
    public Iterable<Edge> edges(){
        return mst;
    }
}
