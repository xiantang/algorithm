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
        }
        node.N = size(node.left)+size(node.right)+1;
        return node;
    }


```
基线条件：
如果该节点的左子树为空就返回他的右子树  
递归条件:
该节点的左子树不为空  