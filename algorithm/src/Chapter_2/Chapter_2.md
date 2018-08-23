### 快排优化
* 对于小数组的排序使用插入排序
* 三取样切分 在重复数目不够多的情况下是使用了
更多的插入交换操作
* 三相切分快排  


### 二叉堆
当一颗二叉树的每个节点都大于他的字节点时,他就会被称为堆有序
二叉堆主要是具有高效的上升和下沉的操作  
所以能够高效的维护一个优先级队列


### 优先级队列
```java
package Chapter_2;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        //构造函数维护一个N+1的数组
        pq = (Key[]) new Comparable[maxN+1];
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    private boolean less(int i,int j){
        return  pq[i].compareTo(pq[j])<0;
    }
    private void exch(int i,int j){
        Key temp;
        temp = pq[i];
        pq[i] = pq[j];
        pq[j] =temp;
    }
    private void swim(int k){
        while (k>1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }
    private void sink(int k ){
        while (2*k<=N){
            int j = 2*k;
            if (j>N && less(j,j+1)) j++;
            if (!less(k,j))break;
            exch(k,j);
            k = j;
        }
    }

    public void  insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        pq [1] = pq[N--];
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(5);
        maxPQ.insert(5);
        maxPQ.insert(8);
        maxPQ.insert(3);
        maxPQ.insert(3);
        maxPQ.insert(3);
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.delMax());
    }
}

```
主要是利用了二叉堆的父元素大于下面的两个子元素 
如果子元素是位置是k 那么父元素一定是`floor(k/2)`
如果父元素是k 那么他的子元素一定是k+1/k
就利用这点 我们可以在插入队列的时候 将元素放在对尾
然后将这个元素不断的尝试能否上升 交换位置 能够保证数组有序
对于最大的就将它与最后的一个元素交换位置弹出最后一个元素 并且使队列的长度-1
然后使用`sink()`使这个最小的元素下称 维护队列的有序