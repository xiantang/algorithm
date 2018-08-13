### 栈
* 泛型: 集合类型的抽象数据类型 的一个关键性质我们可以存储任何类型的数据
* 自动装箱: 将一个原始的数据类型转换为一个封装类型叫做自动装箱 
```java
package Chapter_1;

import algs4.StdIn;
import algs4.StdOut;

public class FixedCapacityStackOfString {
    private String[] a;
    private int N;
    public FixedCapacityStackOfString(int cap) {
        /**
          创建一个容量为cap的空栈
         **/
        a = new String[cap];
    }
    public boolean isEmpty(){
        return N==0;
    }
    public void push(String item){
        a[N++] = item;
    }
    public int size(){
        return N;
    }
    public String pop(){

        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfString s;
        s = new FixedCapacityStackOfString(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }
            else if (!s.isEmpty()){
                StdOut.print(s.pop()+" ");
            }
        }
        StdOut.print("("+s.size()+"left on stack");
    }

}
```
主要采用指针进行栈顶的操作

对`push`方法进行修改 当栈满了就将该栈   
的长度*2

```java
public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < max; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void push(Item item){
        if (N == a.length) resize(2*a.length);
        else a[N++] = item;
    }
```

使栈的使用率永远不会低于1/4
```java
public Item pop(){
        Item item = a[--N];
        a[N] = null;
        if(N>0 && N==a.length/4) resize(a.length/2);
        return item;
    }
```
* 垃圾游离:当一个用例不需要这个对象的时候  
数组中还是会存留这个对象  java的垃圾回收器无法  
知道这一点  除非这个引用被覆盖   
我们这里需要将游离的对象设置为null即可

* 嵌套类是可可以访问到实例的变量的
```java
public  class  ReveseArrayIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext(){
            return i>1;
        }

        public Item next(){
            return a[i--];
        }
        public void remove(){

        }

    }
```

```java
package Chapter_1;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private  Item[] a = (Item[]) new Object[1];
    private int N = 0;
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i <a.length ; i++) {
            temp[i] = a[i];
        }
    }
    public void push(Item item){
        if(N==a.length) resize(2*a.length);
        a[N++] = item;
    }
    
    public Item pop(){
        Item item = a[N--];
        a[N] = null;
        if (N>0 && N == a.length/4) resize(a.length/2);
        return item;
    }
    private class ReverserArrayIterator implements Iterator{
        private int i = N;
        
        public boolean hasNext(){
            return i >0;
        }
        public Item next(){
            return a[--i];
        }
        public void remove(){}
        
    }
    @Override
    public Iterator<Item> iterator() {
        return new ReverserArrayIterator();
    }
    
}

```
这个stack api 的实现是所有集合类抽象数据类型的  
模板 所有元素在数组中 并且动态调整栈和数组的大小

### 链表
* 链表表示的一组元素

* 长方形表示对象
* 将实例的变量写在长方形中
* 用指向被引用的对象的箭头表示引用关系

```java
package Chapter_1;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private class  Node{
        Item item;
        Node next;
    }
    private Node first;
    private int N;
    public boolean isEmpty(){
        return first == null;
    }
    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        Item pop_item = first.item;
        first = first.next;
        N--;
        return pop_item;
    }
    public class IteratorStack implements Iterator{
        private int i = N-1;
        private Node itFirst = first;
        @Override
        public boolean hasNext() {
            return i != 0;
        }

        @Override
        public Object next() {
            Node nextItem = itFirst.next;
            itFirst = nextItem;
            i--;

            return nextItem.item;

        }
    }
    @Override
    public Iterator<Item> iterator() {
        return new IteratorStack();
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        s.push("dd");
        s.push("dd");
        s.push("dd");
        s.push("dd");

        for (String i :s
             ) {
            System.out.println(i);
        }
    }
}


```

java 的迭代器在每次遍历会执行是否`hasNext()`如果为false 就断开 


```java
public void delLast(){
        Node prelast = first ;
        Node last = first.next;
        if (first == null){
            throw new NoSuchElementException();
        }else  if (last == null){
            first = null;
        }else {
            while (last.next!=null){
                prelast = prelast.next;
                last = last.next;
            }
            prelast.next = null;

        }

    }
```
删除最后一个元素

使用构造函数传入一个栈复制他
```java
Node(Node x) {
            /** 
            * @Description: 吊的飞起  用构造函数递归 
            * @Param: [x] 
            * @return:  
            * @Author: Mr.Zhu
            * @Date: 18-8-12 
            */ 
            item = x.item;
            if (x.next != null) next = new Node(x.next);

        }
public Stack(Stack<Item> s,int N) {
        first = new Node(s.first);
        this.N = N;
    }
```
基线条件:当前节点没有next 就设置为null
递归条件:当前节点有next 调用构造方法

### 算法分析

* 再多实验也不一定证明我是对的,但只需要一个实验就能证明我是错的
* 对大常数敏感
