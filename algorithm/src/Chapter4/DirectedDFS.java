package Chapter4;

public class DirectedDFS {
    public   boolean[] marked;
    public DirectedDFS(Digraph G,int s) {
        marked = new boolean[G.V()];
        dfs(G,s);
    }
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        validateVertices(sources);
        for (int v : sources) {//遍历source 进行深搜
            if (!marked[v]) dfs(G, v);
        }
    }
    private void dfs(Digraph g,int s){
        // 深度优先遍历
        marked[s] = true;
        for (int ele:g.adj(s)
             ) {
            if(!marked[ele]){
                dfs(g,ele);
            }
        }
    }
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }
}
