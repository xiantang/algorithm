### 排序

```java
package Chapter_1;

/**
 * @创建人:xiantang
 * @创建时间:15/08/18
 * @email:zhujingdi1998@gmail.com
 * @github:github.com/xiantang
 * @blog:zhanshengpipidi.cn/blog
 * @描述:
 **/
public class Selection extends Example{
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i <N ; i++) {
            int min = i;
            for (int j = i+1; j <N ; j++) {
                if (less(a[j],a[min])) min = j;
            }
            exch(a,i,min);
        }
    }

    public static void main(String[] args) {
        Integer[] a= new Integer[10];
        for (int i = 0; i <a.length ; i++) {
            a[i] = i;
        }
        new Selection().sort(a);
        for (int i:a
             ) {
            System.out.println(i);
        }
    }
}

```
* 插入排序
每次都选择第一个为最小的
然后不断更新最小的值 
将循环最后剩下的那个最小的值 
和第一个最小的值作为交换 QD


### 比较两个的时间复杂度
垃圾的工程代码写的有点多 
`for`循环都有点生疏了
```java
for (int i = 0; i <100 ; i++) {
            System.out.println(i);

        }
```
在for循环中i++是最后执行的 就是当我执行完print的时候
他才会++

```java
for (int j = i; j >0&&less(a[j],a[j-1]) ; j--) {
//                if (less(a[i],a[j-1])){
                    exch(a,j,j-1);
                    
//                }                
            }
```
我们可以知道前面的数值是一定有序的 
不存在前面由大到小的情况 只要不断前进与比他大的值交换就可以了
交换元素


```java

public void toRight(int pre,int la,Comparable[]a){
        Comparable temp = a[la];
        for (int i = la; i >pre ; i--) {
            a[i] =a[i-1];
        }
        a[pre]=temp;


    }
```

改进的 从后面开始遍历 使当前的值等于他之前的值 
就不会有任何冲突了 woc
比交换好用一些

```java
public class Shell extends Example {
    public  void sort(Comparable [] a){
        int N = a.length;
        int h = 1;
        while (h<N/3) h = 3*h+1;
        while (h>=1){
            for (int i = h; i <N; i++) {
                for (int j = i; j >=h&&less(a[j],a[j-h]) ; j-=h) {
                    exch(a,j,j-h);
                }
                h =h/3;
            }
        }
    }
}
```
希尔排序主要是从宏观上进行排序  
首先线选择数组长度的乘以一个常数作为h
然后用h将数组进行分组  
使用插入排序进行排序 
然后不断缩小范围   得到的数组会变长 但是会局部有序 
所以使用插入排序的效率会变高
最后在n=1的情况下啊进行排序


### 归并排序
归并排序主要是通过将一个数组分成两个数组 将其排序
使用两个指针来比较两个值的大小  然后将其复制到原来的数组 
归并排序利用递归的思路实现分治 他首先会将最细小的部分比较完
实现包括这里两个的数组的 大数组 的两个小数组是有序的 
然后不断的回溯

###切分方法
```java
public int partition(Comparable[] a ,int lo, int hi){
        Comparable v = a[lo]; //v是第一个元素
        int i = lo;
        int j = hi+1;
        while (true){
            while (less(a[++i],v)){

                if (i>=hi){
                    break;
                }
            }
            while (less(v,a[--j])){

                if (j<=lo){
                    break;
                }
            }

            if (i>=j){
                break;
            }
            exch(a,i,j);
        }
        
        exch(a,lo,j);
//        System.out.println(a[j]);
        return j;

    }
```
这个方法挺6的 利用的是原地操作 
假设第一个值为中间值 
然后分别从数组的两边进行缩小 如果头部找到比第一个值大的就`break`
尾部找到比第一个值小的就`break` 然后将这两个`break`的值进行交换 
而且把++i --j放入while循环中 就不用担心会不会相遇了 因为这样一定相遇 
然后为什么要选择j与第一个元素相互交换呢 
因为我们能够得出j所在位置的元素一定会比v小或者相等   
然后j后面的元素一定是大于v

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

### 应用 
使用`Comparator`接口可以比较两个属性值的大小
总结:
* 希尔排序:复杂度只是一个近似值
* 插入排序:复杂度取决于属于元素的排列情况
* 快排:复杂度和概率有关 取决于输入元素的排列情况

算法 | 是否稳定 | 是否原地排序 | 时间复杂度  
---- | --- | --- | --- 
插入排序 | 是 |是 | N^2 
选择排序 | 否 | 是 | N^2 
希尔排序 | 否 | 是 | NlogN? /N^(6/5)? 
快速排序 | 否 | 是 | NlogN
三向快速排序 | 否 | 是 | N-NlogN
归并排序 | 是 | 否 | NlogN
堆排序 | 否  | 是 | NlogN


### 求第几个数 也可以拿来求中位数 
```java
package Chapter_2;

import Chapter_1.Example;

public class FIndMidNum extends Quick {
    public Comparable select(Comparable[] a , int k){
        /** 
        * @Description:  
        * @Param: [a, k] 
        * @return: java.lang.Comparable 
        * @Author: Mr.Zhu
        * @Date: 18-8-26 
        */ 
        int lo = 0 , hi = a.length-1;
        while (hi>lo){
            int j = partition(a,lo,hi);
            if (j==k)return a[k];
            if (j>k) hi = j-1;
            else lo = j+1;
            
        }
        return a[k];
    }
    
}

```
理解: 就是随机取数组中的一个值 然后将其作为假设的中位数 
我们使用`partition()`方法去求这个假设的中卫数在整个数组中是
第几小的  如果这个j的值和我们要求的k相同就返回他的元素值 
如果大于k 就将他设为j-1 小于k 可以将它设为j+1 这个二分法的
一个进阶

### i++,++i的区别 
如果i=1
i++ 是当前语句的时候不执行++操作 
等到执行完这个语句之后才会+1
```java
int i = 1;
System.out.println(i++);
System.out.println(i)
//1
//2
```
++i 则是传入的时候就进行++
```java
int i = 1;
System.out.println(++i);
System.out.println(i);
//2
//2
```

### 2.5.18 强制稳定 
```java
 private static void toStead(Transaction[] a, Comparator c){
        Map map = new HashMap();
        for (int i = 0; i <a.length ; i++) {
            map.put(a[i],i);
        }
        SelectP.sort(a,c);

        for (int i = 0; i<a.length ; i++) {
            int temp = i;
            if (temp==0){
            }
            else if (a[temp-1].who().equals(a[temp].who()) ){
                while (a[temp-1].who().equals(a[temp].who())){
                    if ((int)map.get(a[temp-1])>(int)map.get(a[temp])){
                        exch(a,temp-1,temp);
                        temp--;
                    }else break;
                }
            }
        }
    }
```
使选择排序强制稳定 就是将他的原有顺序存入一个`hashmap`
然后遍历排序好的数组 不断的取判断他和之前的属性是否相同
如果相同就查看他俩在`hashmap`的value 如果之前的值比之后的值大
就交换位置并且不断向前 类似倒序的插入排序 