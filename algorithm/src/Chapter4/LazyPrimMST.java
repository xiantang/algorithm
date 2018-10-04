package Chapter4;
import algs4.MinPQ;
import algs4.Queue;

public class LazyPrimMST {
    private boolean[] marked;
    private MinPQ<Edge> pq;
    private Queue<Edge> mst;
    public LazyPrimMST(EdgeWeightGraph G){
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        pq  = new MinPQ<Edge>();
        visit(G,0);
        while (!pq.isEmpty()){
            Edge e = pq.delMin(); // 从优先级队列中获取最小的边
            int v = e.either(),w = e.other(v);
            if (marked[v] && marked[w])continue;
            if (!marked[v]) visit(G,v);
            if (!marked[w])visit(G,w);

        }
    }
    public void visit(EdgeWeightGraph G,int v){
        marked[v] = true;
        for (Edge e:G.adj(v)
             ) {
            if (!marked[e.other(v)])pq.insert(e);
        }
    }
    public Iterable<Edge> edges(){
        return mst;
    }
    private double weight(){
        int weight = 0;
        for (Edge e:edges()
             ) {
            weight+=e.weight();
        }
        return weight;
    }
}
