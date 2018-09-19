### 符号表

随机命中:查找每个键的可能性都相同的情况下,
结果就是一次查找平均所需要的时间.
平均比较次数: ~N/2 (1+2+3+    +N)/N=(N+1)/2~N/2


### 有序数组的二分查找
```java
public int rank(Key key){
        int lo = 0;
        int hi = N-1;
        while (lo<=hi){
            int mid = (lo+hi)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp>0)lo = mid+1;
            else if (cmp<0)hi = mid-1;
            else return mid;
        }
        return lo;
    }
```
这个实现还是很巧妙的 利用的二分法获得一个节点的位置
如果这个节点不存在 就返回比这个节点小的节点的数量
他这个`<=`页很关键 在只剩下一个元素的时候如果这个  
元素不是目标元素的话就使`lo`比`hi`大1

```java
public void  put(Key key,Value val){
        int i = rank(key);
        if (i<N && keys[i].compareTo(key) == 0){
            values[i] = val;
            return;
        }
        for (int j = N; j >i ; j--) {
            keys[j] = keys[j-1];values[j] = values[j-1];
        }
        keys[i] = key;values[i] = val;
        N++;
    }
```
如果拿到这个值就给他更新 如果没有就使他之后的元素位移1位
巧妙

```java
public boolean isContain(Key key) {
               /**
                * 方法实现说明 如果不包含 就返回False
                * @method      contain
                * @author      xiantang
                * @param       [key]
                * @return      boolean
                * @date        2018/9/1 21:06
                */
               return get(key) != null;
           }
 public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (isContain(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

```
keys()方法获取所有的区间内的元素
至于这个`isContain()`方法是用来查看是否存在这个值    
如果存在就给他`return` `true` 
如果不存在 那`get`找到的就是`false`    
那么`return`的就是`false`  
这里添加一个`if` 是因为如果`hi`在队列里面但是
上面的循环是循环不到下面的元素的  


 ### 二叉查找树
 
 定义:
 BST是一颗二叉树，其中每个节点都含有一个`Comparable`
 键  并且每个节点大于其左子树的任意节点的键
 
 * 就是因为他是二叉查找树 所以他的右子树肯定比左子树大   
 我们总是可以把新来的键插到树的叶子上
 
 

```java
private Node put(Node x,Key key,Value value){
        if (root == null) {
            //如果只有头节点
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp>0) put(x.left,key,value);
        else if(cmp<0) put(x.right,key,value);
        else x.value = value ;
        x.N = size(x.left) +size(x.right)+1;
        return x;
        
    }
```
这个`put` 利用了二叉树和递归的完美契合 
下面我们来解释这个递归:
基线条件：如果x是null 就不执行自身或者当前节点的`key`和传入参数的`key`相同
递归条件：`key` 大于或者小于当前节点的`key`

我觉得最巧妙的莫过于这个`x`节点的判断是否为空   



```java
private Value get(Node x,Key key){
        if (x ==null)return null;
        int cmp = key.compareTo(x.key);
        if (cmp<0)return get(x.left,key);
        else  if (cmp>0) return get(x.right,key);
        else return x.value;

    }
```

基线条件:如果`x`是`null`就`return` 或者如果两个键的值相等就`return`
递归条件:如果两个大小不同 就执行自己 


### 分析
最好情况：一颗含有N个节点的树是完全平衡的，   
空节点到叶子节点的距离是`lgn`.
最差情况：叶子节点到到跟节点为`N`


```java
private Key select(int k){
        return select(root,k).key;
    }

    private Node select(Node node,int k){
        if (node ==null) return null;
        int t = size(node.left);
        if (k<t){
            return select(node.left,t);
        }
        if (k>t){
            return select(node.right,k-t-1);
        }else return node;
    }
```
二叉搜索树`select()`实现    
基线条件：  
1. 当前节点是空就返回`null`    
如果节点的左边节点数目与k相等就返回这个节点    
递归条件：如果左边节点数目小于k   
就说明右边第一个节点一定大于   
左边节点和当前节点   
然后k-1-t     
2. 如果左边节点数目大于k 那么进入左节点查找


二叉搜索树`deleteMin()`

```java
private void deleteMin(){
        root = deleteMin(root);
    }
    private  Node  deleteMin(Node node){
        if (node.left == null){
            return node.right;
        }
        else {
            node.left = deleteMin(node.left);

            node.N = size(node.left)+size(node.right)+1;

            return node;
        }
    }

```
基线条件：
如果该节点的左子树为空就返回他的右子树  
递归条件:
该节点的左子树不为空 


### 删除指定节点

```java
private void delete(Key key) {
        root = delete(root, key);
    }
    private Node delete(Node node,Key key){
        if (node==null)return null;
        int cmp = key.compareTo(node.key);
        if (cmp<0) node.left = delete(node.left,key);
        if (cmp>0) node.right = delete(node.right,key);
        else {
            if (node.right == null)return node.left;
            if (node.left == null) return node.right;
            Node x=node;
            node = min(x.left);
            node.right = deleteMin(x.right);
            node.left = x.left;
           
        }
         node.N = size(node.left)+size(node.right)+1;
        return node;
    }
```

基线条件:如果node为空就返回`null`  
如果找到了需要删除的节点就进行操作  
获取节点左子树的最小节点    
通过`deleteMin()`删除节点    
然后使后继节点继承当前节点     

递归条件:如果没有找到node节点 就不断递归查找    


将node两侧的元素的数目更新


### 查找范围    

```java
private Iterable<Key> keys(){
        return keys(min(),max());
    }

    private Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root,queue,lo,hi);
        return queue;
    }

    private void keys(Node node,Queue<Key> queue,Key lo ,Key hi){
        if (node ==null)return;
        int cmpa = lo.compareTo(node.key);
        int cmpb = hi.compareTo(node.key);
        if (cmpa<0)keys(node.left,queue,lo,hi);
        if (cmpa<=0 && cmpb>=0) queue.enqueue(node.key);
        if (cmpb>0)keys(node.right,queue,lo,hi);
    }
```
递归的查找根节点的左子树（如果左子树节点在范围内）    
然后查找根节点    
最后查找右子树

### 2-3 查找树

2-3树定义:包含两个键和三条链接的节点的树

插入操作:
* 向2-节点插入新键：需要进行一次未命中的查找    
首先把根节点挂在底部 然后将他转化成为一个3-节点就可以了   


* 向父亲节点为2-节点的3-节点插入新键：也将三节点上挂载上新的节点     
这次需要将这个4-节点中的mid值 传递给父亲节点   
和父亲节点作为一个3-节点  


* 向父亲节点为3-节点的3-节点插入新键：步骤和上面差不多   
需要注意的是父亲节点变成了4-节点我们需要做的事    
是将父亲节点的mid再传送给上面的父亲节点  以此类推 
直到上面的节点为2-节点 或者是根节点为3-节点为止   



* 根节点为4-节点： 需要做的是是将mid提取出来   
树高加1


结论： 全局变换
除了根节点向上脱离出一个节点 其他4-节点转2个2-节点   
所有的路径到根节点的路径都不会变长


### 左偏红黑树   
红链接：两个2-节点链接起来构成的3-节点    
黑链接：两个2-节点中间的链接  


红黑树的旋转：


1. 左旋转操作:
![](http://p99jlm9k5.bkt.clouddn.com/blog/image/1/21.png)
```java
private Node rotateLeft(Node h){          
    Node x = h.right;                     
    h.right = x.left;                     
    x.left = h;                           
    x.color = h.color;                    
    h.color = RED;                        
    x.N = h.N;                            
    h.N = size(h.left)+size(h.right)+1;   
    return x;                             
}                                         
```
左旋转操作先将`h.left`保存到x中   
然后将`x.left`作为`h.left`   
将`x.left`设置为`h`
保留`h`的颜色到`x`  
将`h`的颜色设置为红色   
然后更新`h.N`的节点数目   
至于为什么先更新`h.N`的节点数目主要因为   
在之后的操作中会更新`x.N`的节点数目    
并且因为左侧红黑树是从下往上生长的   


2. 右旋转操作:
![](http://p99jlm9k5.bkt.clouddn.com/blog/image/1/26.png)
```java
private Node rotateRight(Node h){       
    Node x= h.left;                     
    h.left = x.right;                   
    x.right = h;                        
    x.color = h.color;                  
    h.color = RED;                      
    x.N = h.N;                          
    h.N = 1+size(h.left)+size(h.right); 
    return x;                           
}                                       
```


3. 左右节点都为红色节点:
![](https://upload-images.jianshu.io/upload_images/4155986-f44848f707b5fac2.png?imageMogr2/auto-orient/)

```java
private void  filpColors(Node h){ 
    h.color = RED;                
    h.left.color = BLACK;         
    h.right.color = BLACK;        
}                                 
```
疑问：红黑树中删除最小元素的操作      
对于moveRedLeft()方法的实现不怎么理解     


`put()`实现:

```java
public Node put(Node x, Key key, Value val) {         
    if (x == null) {                                  
        return new Node(key, val, 0, RED);            
    }                                                 
    int cmp = key.compareTo(x.key);                   
    if (cmp > 0) x.right = put(x.right, key, val);    
    else if (cmp < 0) x.left = put(x.left, key, val); 
    else x.value = val;                               
    if (isRed(x.right) && !isRed(x.left)) {           
        //如果右链接为红色                                    
        x = rotateLeft(x);                            
    }                                                 
    if (isRed(x.left) && isRed(x.left.left)) {        
        //如果左链接为红色并且左链接的左链接为红色                        
        x = rotateRight(x);                           
    }                                                 
    if (isRed(x.left) && isRed(x.right)) {            
        //如果两个链接都为红色                                  
        filpColors(x);                                
                                                      
    }                                                 
    x.N = size(x.left) + size(x.right) + 1;           
    return x;                                         
}                                                     
```
这个put重点在于回去整理二叉树的时候

TODO:实现删除操作

### 散列表 

* 除留余数法     
我们选择大小为素数M的数组，对于任意的正整数k.
计算k除以M的余数。
求字符串长度的n为R进制值     
java 字符串`hashCode()`默认实现    
```java
@Contract(pure=ture)public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
```

加1溢出改变了符号位    

-2147483647      10000000 00000000 00000000 00000001
-2147483648      10000000 00000000 00000000 00000000


* `a.equal(b)`能保证a和b是同一个对象    
那么两个对象的`hashCode()`肯定相同   
java约定如果两个对象的`hashCode()`不同   
那么这两个对象就不同


### 拉链法  

```java
package Chapter_3;

public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    private SeparateChainingHashST() {
        this(997);
    }

    private SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < st.length; i++) {
            st[i] = new SequentialSearchST();
        }

    }

    private int hash(Key key) {

        return (key.hashCode() & 0x7fffffff) % M;
    }

    private Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    private void put(Key key, Value val) {
        st[hash(key)].put(key, val);

    }

    private boolean contain(Key key) {
        return get(key) != null;
    }

    private void delete(Key key) {
        if (!contain(key)) return;
        //先进行hash操作
        int i = hash(key);
        SequentialSearchST<Key, Value> st1 = this.st[i];
        st1.delete(key);


    }


    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> chainingHashST = new SeparateChainingHashST<String, Integer>();
        chainingHashST.put("DD", 1);

        System.out.println(chainingHashST.contain("DF"));
        chainingHashST.delete("DD");
        System.out.println(chainingHashST.get("DD"));

//        System.out.println(-214748364& 0x7FFFFFFF);
    }
}

```
主要是创建一个列表   
然后列表的每个节点由一个链表组成   
插入方法:
如果有个节点插入的话   
那么这个首先对这个节点的`hashCode()`  
和`0x7fffffff`进行位运算   
得到一个肯定0到`214748364`的值    
然后对他进行`mod`将它散列到一个列表当中   
插入列表内链表对象的尾部     

>& is bitwise. && is logical.     
>& evaluates both sides of the operation.
>&& evaluates the left side of the operation, if it's true, it continues and evaluates the right side.   
>(hash & 0x7FFFFFFF) will result in a positive integer.


![](https://images0.cnblogs.com/blog/631817/201502/271613146279675.x-png)

### 基于线性探测法的散列表   

探测：检查一个数组的位置是否含有被查找的键    
的操作叫做探测。    

```java
private void resize(int len) {

        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(len);
        for (int i = 0; i < this.M; i++) {
            if (keys[i]!=null){
                t.put(keys[i],vals[i]);
            }
        }
        M = t.M;
        this.keys =t.keys;
        this.vals =t.vals;
    }
```
重点需要讲一下这个`resize`将原来的数组为空的   
插入到新的数组   
他的实现方式是实例化一个自己    
然后将它转换为调用自己的`put`方法将元素重新散列   
就可以保证`get`方法能够使用     妙～



###  SeparateChainingHashST 添加resize()

如果平均的链表长度大于指定的长度     
就将数组的长度翻倍      


```java
private void resize() {
//        System.out.println("RESIZE");
        int newM = M*2;
        SeparateChainingHashST<Key,Value> separateChainingHashST =
                new SeparateChainingHashST<Key,Value>(newM);
        for (int i = 0; i < st.length; i++) {
            for (SequentialSearchST.Node x = st[i].first; x != null; x = x.next) {
                separateChainingHashST.put((Key)x.key,(Value)x.value);
            }
        }
        st = separateChainingHashST.st;
        M = 2*M;
    }
```
首先遍历整个数组以及他的链表元素   
将它的每个元素都推入 新的数组   
然后修改当前实例的成员    


### 应用 

#### 过滤器

```java
public class DeDup {
    public static void main(String[] args) {
        HashSet<String> set;
        set = new HashSet<String>();
        while (!StdIn.isEmpty()){
            String key = StdIn.readString();
            if (!set.contains(key)){
                set.add(key);
                StdOut.print(key+" ");
            }
        }
    }
}
```
如果存在就不输出


### 稀疏向量

```java
public double dot(double[] that){
        double sum = 0.0;
        Queue<Integer> queue  = st.keys();
        for (int i :queue
             ) {
            sum += that[i]*this.get(i);
        }
        return sum;

    }
```
因为向量都是稀疏的  我们只要将向量在列表的位置和值存在
符号表   然后和对应的向量的索引位置的相乘就行了    

所以就是为什么这么快的原因了    