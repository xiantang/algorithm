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

其实深度优先遍历也可以显式的使用一个栈(LIFO)  
我们使用递归的方式也是一种操作系统内置的栈  

### 广度优先搜索(BreathFirstPath)

结论：使用队列来操作  吐出一个节点将他的所有相邻且不被标记的   
节点进行入队（队尾）



```java
package Chapter4;

import Chapter1.Stack;
import algs4.In;
import algs4.Queue;

public class BreadFirstPath {
    private boolean[] marked;
    private int edgeTo[];
    private final  int s;

    public BreadFirstPath(Graph graph,int s) {
        this.s = s;
        edgeTo = new int[graph.V()];
        marked = new boolean[graph.V()];
        bfs(graph,s);

    }

    public void bfs(Graph g,int s){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] =true;
        while (!queue.isEmpty()){
            int n = queue.dequeue();
            marked[n] = true;
            for (int v:g.adj(n)
                 ) {
                if (!marked[v]){
                    edgeTo[v] = n;
                    marked[v] = true;
                    queue.enqueue(v);
                }

            }
        }
    }
    public boolean hasPath(int v){
        return marked[v];
    }
    public  Iterable<Integer> pathTo(int v){
        if (!hasPath(v)){
            return null;
        }
        Stack<Integer> stack = new Stack<>();

        for (int i = v; i !=s ; i=edgeTo[i]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }
    public static void main(String[] args) {
        In in = new In();
        Graph graph = new Graph(in);
        BreadFirstPath breadFirstPath = new BreadFirstPath(graph,0);
        for (int i
                :breadFirstPath.pathTo(3)
             ) {
            System.out.println(i);
        }


    }
}

```

命题：对于s->v的任意顶点v 广度优先遍历一定会找到一条s->v的最短路径   

如果距离顶点为1的相邻顶点的距离为1，那么这些顶点的最近边的距离   
距离顶点的距离是2，所以这些顶点都是根据和起点的距离    


### 有向图    

出度:该顶点指出的边的总数     
入度:指向该顶点的边的总数   


### 标记-清除的垃圾收集   

标记-清除的垃圾回收机制会为每个对象保留一个   
位数做垃圾收集用。他会周期的运行一个类似   
DirectedDFS的有向图可达性算法来标记所有   
可以被访问对象，然后清理所有对象，回收没有  
被标记的对象，腾出内存供给新的对象使用。  

 有向无环图（DAG）：就是一副不带有向环的有向图
 
 
### 有向图是否有环     
 
核心方法

```java
public void dfs(Digraph G,int v){
        marked[v] = true;
        onStack[v] = true;
        for (int w:G.adj(v)
             ) {
            if(hasCycle){
                return;
            }
            else if (!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
            else if (onStack[w]){
                cycle = new Stack<Integer>();
                for (int x = v; x !=w ; x=edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                hasCycle = true;
            }
        }
        onStack[v] = false;
    }
```

从v-w是一条路径其中有很多节点   
到达w节点时v节点还在栈中  
但是在v的下一个节点  是w   
这就是一个环了，所以我们判断的方式可以是   
如果一个节点在栈中，但是当前节点的下个节点
就是在栈中的这个节点，那么就形成了一个环    

### 拓扑排序


求证：一幅有向无环图的拓扑顺序就是所有顶  
点的逆后序列
证明: 对于边v->w 我们给出两种情况    
1. dfs(w)被标记 就表示dfs(w)在dfs(v)之前返回了   
就意味着全部返回后w所在的reversePost栈深度比v深      
2. dfs(w)没有被标记 dfs(w)进入系统堆栈后返回才会   
之后再返回dfs(v)  所以当他门两个数进入reversePost栈  
w永远比v深

```java
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
```
求证：使用深度优先搜索对有向图进行拓扑排序所需要的时间和V+E成正比   

证明: 第一次遍历的时候是检查是否有环需要遍历所有节点和边 
第二次是进行深搜产生逆后续的排列 

### 有向图的强连通性      

如果两个顶点v和w是相互可达的，则称他们为强连通     


三个特性：

1. 自反性:任意顶点`v`和自己都是强连通的   
2. 对称性:如果`v`和`w`是强连通的，那么`w`和`v`也是强连通的     
3. 传递性:如果`v`和`w`是强连通且`v`和`x`是强连通的   
那么`v`和`x`也是强连通的

强连通分量: 强连通性将所有顶点分为了一些等价类    
每个的等价类是由相互均为强连通分量的顶点最大自集合组成    

一个含有1-V个的有向图含有1-V个强连通分量   
一个强连通图只有一个强连通分量   


### Kosaraju算法

```java
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

```
证明：每个和s强连通的顶点v都会在dfs(s)的时候被访问到     
利用反证法证明    
存在一个强连通点v和s强连通，不会在调用dfs(s)的时候被访问   
 ，那么就存在s->v的路径    
 但是无法访问到v就表明v已经被标记过了，就表明s被
 标记了，与上面产生矛盾达成反证。
 
 ### 顶点对的可达性   
 
 有向图G的传递闭包是由一组相同的顶点组成的另一幅有向图    
 在传递闭包中存在一条v->w的边当且仅当G中w->v是可达的   
 
 
 ```java
package Chapter4;

public class TransitiveClousre {
    
    private DirectedDFS[] all;
    TransitiveClousre(Digraph G){
        all = new DirectedDFS[G.V()];
        for (int v = 0; v <G.V() ; v++) {
            all[v] = new DirectedDFS(G,v);
        }
    }
    boolean reachable(int v,int w){
        return all[v].marked[w];
    }
}

```

### 最小生成树    


定义:树的所有边的权值只和最小的生成树    

约定:
1.只考虑连通图    
2.边的权重不一定表示距离  
3.边的权重可能为0或者是负数    
4.所有边的权重各不相同    

原理:
1.用一条边连接树的任意两个顶点将会产生一个新的环         
2.从树中删除一条边将会得到两颗独立的树      

切分定理:在一幅加权图中，他的横切边中权重最小的必然属于   
图的最小生成树.

需要使用反证法证明，假设T不包含最小的横切边e   
如果将e加入T必然形成一个环，并且环至少含有应外一条横切边f
f大于e，那么删除f就可以得到一个更小的树，矛盾所以错误。


无向权重图 返回所有的树枝     

```java
public Iterable<Edge> edges(){
        Bag<Edge> edges = new Bag<Edge>();
        for (int v = 0; v <V ; v++) {
            for (Edge e:adj[v]
                 ) {
                if (e.other(v)>v)edges.add(e);
            }
        }
        return edges;
    }
```
遍历每个背包，从背包里面获取边，如果该边的另一个顶点大于   
其他顶点。那么就只有一条边进入，不会将两个边插入背包。

### Prim算法    

结论:每一步都为一颗生长的树中添加一条边，一开始这颗树只有一颗顶点    
然后会向他添加V-1条边，每次总是将下一条连接树中的顶点与不在树中的最小横切边   
加入树中

```java
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
```

### prim 即时算法    

```java
package Chapter4;

import Chapter2.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;
    public PrimMST(EdgeWeightGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v <G.V() ; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty()){
            // 删除队列中最小的节点
            visit(G,pq.delMin());
        }
    }
    private void visit(EdgeWeightGraph G,int v){
        marked[v] = true;
        for (Edge e:G.adj(v) // 获取所有的v相邻的节点
             ) {
            int w = e.other(v);
            if (marked[w])continue; // 如果被标记，那么就继续
            if (e.weight()<distTo[w]){
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w,distTo[w]);
                else pq.insert(w,distTo[w]);
            }
        }

    }
}
```