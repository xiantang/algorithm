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

对了 !`sink()`选择子节点较大者

### 堆排序 
先构造一个堆 然后每次拿出最大的 使用交换 和下沉操作
```java
package Chapter_2;

import Chapter_1.Example;
import algs4.StdRandom;

public class HeapSort extends Example {
    public   void sort(Comparable[] a){
        int N = a.length-1;
        for (int i = N/2; i>=1  ; i--) {
            sink(a,i,N);
        }
        while (N>1){
            exch(a,1,N--);

            sink(a,1,N);
        }
    }
    public  void sink(Comparable []a ,int k,int N){
        while (2*k<=N){
            int j = 2*k;
            if (j<N && a[j].compareTo(a[j+1])<0) j++;
            if (a[k].compareTo(a[j])>0) break;
            // 如果k大于j就break掉

            exch(a,k,j);
            k = j;
        }
    }
    public static void main(String[] args) {
            Double [] dou = new Double[12];
    //        Double[] a= new Double[10];
            for (int i = 1; i <dou.length ; i++) {
                dou[i] = StdRandom.uniform();
    
            }
            new HeapSort().sort(dou);
            for (int i = 1; i <dou.length ; i++) {
                System.out.println(dou[i]);
            }
        }
}
```
问题:为什么要首元素为`null`   
回答:因为这样堆顶的元素是1,之后方便一些   

优点:内存效率高   
缺点:但是无法利用缓存 与他比较的往往不是附近的元素
所以 缓存不容易命中

