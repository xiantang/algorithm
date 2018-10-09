package Chapter4;

public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightDigraph G,int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v <G.V() ; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological top = new Topological(G);
        for (int v:top.order()
             ) {
            relax(G,v);
        }

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

            }
        }
    }
}
