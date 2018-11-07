package Chapter6;

import algs4.FlowNetwork;
import algs4.Queue;

public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkerson(FlowNetwork G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {
            // 利用存在的增广路径
            // 计算当前的瓶颈内容
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;

        }
    }
    public double value(){
        return value;
    }

    public boolean inCut(int v){
        return marked[v];
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.V()]; // 标记已知的顶点
        edgeTo = new FlowEdge[G.V()]; // 路径上最后一条边
        Queue<Integer> q = new Queue<>();
        marked[s] = true; // 标记起点
        q.enqueue(s);     // 将他入列
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (FlowEdge e : G.adj(v)
            ) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;    // 保存路径的最后一条边
                    marked[w] = true; // 标记w
                    q.enqueue(w);     // 将它入列
                }
            }

        }
        return marked[t];
    }
}
