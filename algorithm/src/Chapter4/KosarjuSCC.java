package Chapter4;

public class KosarjuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarjuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder df = new DepthFirstOrder(G.reverse());
        for (int s:df.reversePost()
             ) {
            bfs(G,s);
            count++;
        }

    }

    private void bfs(Digraph G,int v){
        marked[v] = true;
        id[v] = count;
        for (int w:G.adj(v)
             ) {
            if (!marked[w])
                bfs(G,w);
        }
    }


    public boolean stronglyConnected(int v,int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}
