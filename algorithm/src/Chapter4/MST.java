package Chapter4;

public class MST {
    //最小生成树
    private EdgeWeightGraph G;

    public MST(EdgeWeightGraph g) {
        G = g;
    }
    public Iterable<Edge> edges(){
        return G.edges();
    }
    public double weight(){
        double weight = 0;
        for (Edge edge:edges()
             ) {
            weight+= edge.weight();
        }
        return weight;
    }
}
