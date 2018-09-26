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

