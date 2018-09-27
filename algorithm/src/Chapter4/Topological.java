package Chapter4;

import java.util.HashMap;

public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G){
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.isHasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();

        }

    }

    public Iterable<Integer> order(){
        return order;
    }
    public boolean isDAG(){
        return order!=null;
    }

}
