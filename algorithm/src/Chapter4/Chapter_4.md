## 图


### 无向图  

定义: 图是由一组顶点和一组能够将两个顶点相连的边组成的结构    

#### 特殊的图   

自环: 一条连接和一个顶点和其自身的边   
平行边: 连接同一对顶点的两条边   
连通图:如果从任意一个顶点都存在一条路径到达另一个任意顶点   
的图。


#### 临接表数组(普遍实现)  
定义:用一个以顶点为索引的列表数组   
其中每一个元素都是和改顶点相邻的   
列表数组。

是因为他能够满足以下两个条件:   

* 可以预留足够的空间   
* 能够简便的检查所有边    

并且具有以下特征:

* 使用的空间和V+E成正比。
* 添加一条边所需要的时间为常数级别。
* 遍历一个顶点所需要的时间和这个顶点的   
度数成正比。


具体实现:


```java
package Chapter4;

import algs4.Bag;
import algs4.In;

public class Graph {
    private final int V;   //顶点数目
    private  int E;   //边的数目
    private Bag<Integer>[] adj;    //临街表
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v <V ; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    public Graph(In in){
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i <E ; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }


    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    @Override
    public String toString() {
        String s = V+ " vertices ,"+E +" edges\n";
        for (int v = 0; v <V ; v++) {
            s += v+ ":";
            for (int w:this.adj(v)
                 ) {
                s+= w+" ";
            }
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args) {
        In in = new In();

        Graph graph = new Graph(in);
        System.out.println("OK");
        System.out.println(graph);
    }
}

```

我们构造方法第一个是构造一个长度为`V`的图    
第二个构造方法是从输入流中读取     
首先读取临接表的顶点数目   
然后再读取临接表的边的数目   
然后再填充每条边以及他的顶点    
  
#### 深度优先搜索     
访问一个顶点时候:
1. 将他标记为已访问   
2. 递归的访问没有标记过的邻居节点     
3. 如果这个节点的相邻节点全部被访问 
返回他的上一级去访问他的上一级的所有未被标记节点

 ```java
package Chapter4;
import algs4.In;
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph g,int s){
        marked = new boolean[g.V()];
        dfs(g,s);
    }

    public boolean marked(int v){
        return marked[v];
    }

    public void dfs(Graph G,int s){
        marked[s] = true;
        count++;
        for (int v:G.adj(s)
             ) {
            if (!marked[v])dfs(G,v);
        }
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In());
        DepthFirstSearch search = new DepthFirstSearch(graph,1);
        System.out.println(search.count());
    }
}
```

深度优先搜索标记和起点的联通的所有的顶点的时间和顶点的读书成正比   

