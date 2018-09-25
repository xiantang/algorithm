package Chapter4;

public class DirectedDFS {
    private  boolean[] marked;
    public DirectedDFS(Digraph G,int s) {
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    private void dfs(Digraph g,int s){

        marked[s] = true;
        for (int ele:g.adj(s)
             ) {
            if(!marked[ele]){
                dfs(g,ele);
            }
        }
    }
}
