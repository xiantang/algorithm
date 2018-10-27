## 字符串 

不可变性:String 对象是不可变的，因此可以将他作为赋值语句，作为参数或为函数的参数或者返回值   
不用担心变化    

子字符串:String对象 一般使用40字节 然后24+2N字节 总共64+2N个字节   
子字符串 是使用的父亲字符串的`char[]`的`别名`   
所以子串只占用40个字节

### 低位优先的排序算法     

顾名思义：低位优先的字符串排序算法是将字符映射成   
数字  从低位开始排序的排序算法  
所以这样的算法是稳定的 
他主要需要4个操作：

* 计算出现频率
* 转换为索引
* 将元素分类 
* 回写
```java
package Chapter5;

public class LSD {
    public static void sort(String a[],int W){
        int N = a.length;
        int R = 256;

        String[] aux = new String[N];
        for (int d = W-1;d>=0;d--) {
            int[] count = new int[R + 1]; //count是一个长度256的数组
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
                //+1是为了转换为ASCII
            }
            for (int r = 0; r <R ; r++) {
                count[r+1]+=count[r];
            }//count 转换为索引

            for (int i = 0; i <N ; i++) {

                aux[count[a[i].charAt(d)]++] = a[i];
             //分类到aux
            }

            for (int i = 0; i <N ; i++) {
                a[i] = aux[i];
                //回写
            }

        }


    }
}
```
### 高位优先的排序算法 

```java
public class MSD {
    private static int R=256;   //基数
    private static final int M = 15;  //小数组切换阀值
    private static String[] aux;  //数据分类的辅助数组
    private static int charAt(String s,int d){
        if (d<s.length()){
            return s.charAt(d);
        }else return -1;
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a,0,N-1,0);
    }
    public static void sort(String[] a,int lo,int hi,int d){
        if (hi<lo){
            return;
        }
        int count[] = new int[R+2];

        for (int i =lo; i <=hi ; i++) {

            int index = charAt(a[i],d)+2;
            count[index]++;  //计算频率
        }
        for (int r = 0; r <R+1 ; r++) {
            count[r+1] += count[r];
        }
        for (int i = lo; i <=hi ; i++) {
            aux[count[charAt(a[i],d)+1]++] = a[i];
        }
        for (int i = lo; i <=hi ; i++) {
            a[i] = aux[i-lo];
        }
        for (int r = 0; r <R ; r++) {

            sort(a,lo+count[r],lo+count[r+1]-1,d+1);
        }
    }
}
```

小型数组：将一个长度小于等于10的子数组切换为插入排序能够轻易的降低运行时间

时间复杂度：对于随机输入，高位优先只会先检查足以区别的字符串，所以是亚线性级别的。但是对于所有输入的字符串都相同的情况下，就是线性级别的时间消耗。


### 三向字符串快速排序   

通过递归将字符串切分为三个子数组，  
分别为大于小于当前字符的子数组  
等于当前字符的子数组   
然后将等于当前数组的子数组以下一个字符  
开始进行调用，大于和小于当前数组的子数组  
以当前字符开始调用（因为未排序）  

```java
package Chapter5;

public class Quick3string {
    private static int charAt(String s,int d){
        //返回索引d 所在字母表的排序
        if (d<s.length())return s.charAt(d);
        else return -1;
    }

    private static void sort(String[] a){
        sort(a,0,a.length-1,0);
    }

    private static void sort(String[] a,int lo,int hi,int d){
        if (hi<=lo)return;
        int lt = lo,gt =hi;
        int v = charAt(a[lo],d);
        int i = lo+1;
        while (i<=gt){
            int t = charAt(a[i],d);  //返回当前i的所指字符的大小
            if (t<v) exch(a,lt++,i++);//如果比当前字符小就到lt的位置
            else if (t>v) exch(a,i,gt--);//这里i不进行++是因为下次循环还要继续检验
            else i++;

        }

        sort(a,lo,lt-1,d); // 排序上面当前字母 部分的
        if (v>=0)sort(a,lt,gt,d+1); // 排序相等的字母的下一部分
        sort(a,gt+1,hi,d);// 排序下面当前字母的部分
    }
    private static void exch(String[] a,int lt,int i){
        String temp= a[lt];
        a[lt] = a[i];
        a[i] = temp;
    }

    public static void main(String[] args) {
        String[] a= {"QQQQ","QFFF","QEEE"};
        sort(a);
        for (String s:a
             ) {
            System.out.println(s);;
        }
    }
}

```
随机化：在排序之前将数组打乱或者将第一个元素和一个随机的元素交换   
以得到一个随机的切分元素，预防数组接近有序或者数组已经有序。 
性能：运行时间是N*字符串的长度+2NlnN次比较成正比
应用：处理具有很长的共同前缀的字符串排序 例如：网站日志


### 单词查找树  
 
定义：由字符串键中的所有字符构成，允许使用被查找键中的字符进行查找
性质：值为空的节点在符号表中没有对应的键，它们的存在是为了简化单词查找树中的查找操作。


实现:

```java
public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    private Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        else return (Value) x.val;

    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;//如果是null 就return null
        if (d == key.length()) return x; //当字符串的长度和当前字符的位置相同就return
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    private void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }
    private Iterable<String> keys(){
            //获取所有字符串
            return keysWithPrefix("");
        }
    
        private Iterable<String> keysWithPrefix(String pre){
            //获取所有以pre开头的字符串
            Queue<String> q= new Queue<String>();
    
            collect(get(root,pre,0),pre,q);
            return q;
        }
    
        private void collect(Node x,String pre,Queue<String> q){
            if (x==null)return;
            if (x.val!=null){
    //            System.out.println(pre);
                q.enqueue(pre);
            }
            for (int r = 0; r <R ; r++) {
                collect(x.next[r],pre+(char)r,q);
            }
    
        }
        public Iterable<String> keysThatMatch(String pat){
                Queue<String> q =  new Queue<String>();
                collect(root,"",pat,q);
                return q;
        
            }
            private void collect(Node x,String pre,String pat,Queue<String> q){
                int d= pre.length();
                if (x==null)return;
                if (d== pat.length()&&x.val!=null)q.enqueue(pre);
                if (d == pat.length())return;
                char next = pat.charAt(d);
                for (int c = 0; c <R ; c++) {
                    if (next=='.'||next==c){
                        collect(x.next[c],pre+(char)c,pat,q);
                    }
                }
        
            }
        public void delete(String key){
                root = delete(root,key,0);
            }
            private Node delete(Node x,String key,int d){
                if (x==null)return null;
                if (d==key.length())x.val=null;
                else {
                    char c = key.charAt(d);
                    x.next[c] = delete(x.next[c],key,d+1);
        
                }
                if (x.val!=null){
                    return x;
                }
                for (int r = 0; r <R ; r++) {
                    if (x.next[r]!=null)return x;
                }
                return null;
        
        
            }    
        public String longstPrefixOf(String s){
                int length = search(root,s,0,0);
                return s.substring(0,length);
            }
            private int search(Node x,String s, int d,int length){
                if (x==null)return length;
                if (x.val!=null)length=d;  
                if (s.length()==d)return length; //如果长度和字符串相同就返回长度
                char c = s.charAt(d);
                return search(x.next[c],s,d+1,length);
            }
            
        public static void main(String[] args) {
            TrieST<Integer> trieST = new TrieST<Integer>();
            trieST.put("abnaaa",1123);
            trieST.put("abcd",2123);
            trieST.put("aeqeq",21);
            System.out.println(trieST.keysWithPrefix("ab"));
            for (String s:trieST.keys()
                 ) {
                System.out.println(s);
            }
        }
}
```
查找操作的三种情况：
1. 尾字符所对应的节点中的值非空
2. 尾字符所对应的节点的值为空
3. 查找结束于一条空链接  

所有键:  
1. 使用`keysWithPrefix()`方法查找所有`""`开头的字符串   
2. 在其中调用`collect()`通过递归遍历所有的链接 记住`int`转换为`char`

通配符匹配:
1. `keysThatMatch`方法创建一个队列存储字符串  获取所有匹配`pat`的字符串   
2. `collect`方法分别为3种情况 如果`x`是null 推出栈
如果当前长度和pre的长度相同并且`x.val`不是空就压入队列     

最长前缀:
使用`search`方法 从当前节点开始👇查找  并且是根据当前字符来查找  当val存在的时候 更新d的值  
如果不存在并且x不是空的 就继续向下查找 

删除指定键:
实现非常巧妙 找到那个键 将他的值设置为空 如果没找到就直接返回空 这样的话 在回溯的过程中  
如果当前的节点没有子节点并且`val=0`那么就将他设置为`null`就达成了删除的作用 

时间复杂度运算:
在get()和put()中都会带有一个参数`d`，他的初始值为0，每次调用都会使`d+1` 当长度和键相同的时候  
就停止递归 


### 三向单词查找树（TST）    

每个节点都含有1个字符，3条链接，1个值   
这三条链接分别对应着小于，等于，大于节点字母的所有键。   

查找/插入操作   
查找：获取当前`d`所在的字符位置，如果当前的节点是`null`就表示未命中  
`return` `null` 如果大于`x.val` 继续向右边查找 返回值用`x.right`接住   
小于也是相同的思路  如果当前的`d`和`key`的长度相同 就返回当前节点    
如果`d`的值和`x.val`相同并且长度小于`key.length()`继续调用 并且`d+1`

插入:存入操作需要返回的是根节点,所以不需要将存入的节点返回  只要返回根节点就行

### 子字符串查找     

#### 暴力子串查找  
最坏情况 例如模式字符串可能有一串A，文本字符串也有一大串A

显示回退法  
定义两个指针i,j分别指向模式和文本的首字母   
如果i和j小于对应的字符串长度就循环 如果两个
字母匹配就使模式的字符串向后移动，否则就回退两个
指针

#### Knuth-Morris-Pratt子字符串查找算法    

KMP查找算法中不会回退文本指针 如果匹配失败 回退的距离是已知文字  
和模式的最大长度  匹配成功继续检查下一个字符j+1

模式指针的回退：
不会回退文本指针，只会回退模式指针  
如果匹配失败 回退的距离是已知文字和模式的最大重叠长度   
匹配成功继续检查下一个字符j+1    

确定有限状态机（DFA） 
构造DFA
匹配转换：获得`dfa[pat.charAt(j)][j]`中的值 指针j移动到指定位置
非匹配转换: 向左移动`dfa[pat.charAt(j)][j]`  
停止状态🤚：到达状态M就找到了

DFA构造:
需要扫描的文本字符在charAt(1)-charAt(j-1)之间   
每个可能匹配失败的位置都可以找到重启的位置  

匹配成功复制X位置的值，并且使`dfa[pat.charAt(j)][j]=j+1`

获得下一个X的位置需要通过递推式子`x[j+1] = dfa[pat.charAt(j)][X[j]]`,其中charAt(j)指的是匹配成功的位置
时间复杂度:对于长度为M的模式字符串和长度为N的文本，访问字符不会超过M+N
优点:在重复度很高的文本中的速度很快


#### Boyer-Moore 字符串查找算法 
概述:在文本字符串回退的时候，从右向左扫描模式字符串并将它和文本匹配

使用一个索引`i`在文本中从左向右移动，用另外一个索引`j`从  
右向左移动  

匹配失败的情况：
1. 匹配失败的字符不在模式字符串当中的话，将模式字符串向右移动j+1位置
2. 匹配失败字符串在模式字符串中，就使用字母表跳跃
3. 匹配失败的字符串在模式字符串中，但是文本字符串和模式最右端的E对齐，就会将模式字符串向左移动


#### Rabin-Kaarp 指纹字符串查找算法   

需要计算模式字符串的散列函数，然后用相同的散列函数计算文本中所有可能的M个字符的子字符串散列值  
并寻找匹配

计算散列函数：对于字符串的每个数字都将起散列值*R，再除Q取其余数
右移算法: 右移动算法主要是通过获得hash值的算法，`(上个字符的hash+需要舍弃的字符*(997-30)*10+最新的字符)%Q`

### 正则表达式   

部分子字符串的查找问题  

#### 正则表达式的描述模式   

语言和模式:语言是字符串的集合，模式是语言的一种详细说明，模式可以转换成指定的语言   

* 链接操作:写出AB的时候就表示`{AB}`指代的是A和B相互连接,指定了一种语言{AB}
* 或操作:如果在两种选择中指定了一个或运算符`|`,那么他们将属于同一种语言 
* 闭包操作:将模式的任意部分重复任意次数 包括0次`*`  
* 括号:括号可以来改变默认的优先级顺序

定义:

* 空字符串
* 单个字符
* 包含在括号中的另一个正则
* 两个链接起来的正则
* 用或运算符分割的两个/多个正则
* 用闭包运算符标记的正则


#### 字符集描述符

* 通配符
* 指定的集合
* 集合范围
* 补集

| 选项 | 记法 | 举例 | 原始写法 |  
| ------ | ------ | ------ | ------ |
| 至少重复一次 | +           | (AB)+ | (AB)(AB)*  |
| 重复0次或1次 | ？         | （AB）? | ε \ AB |
| 重复指定次数 | 由{}指定次数 | （AB）{3} | (AB)(AB)(AB)  |
| 重复指定范围的次数 | 由{}指定 | (AB){1-2}| (AB) \ (AB)(AB) |

#### 转义序列   

反斜杠将元字符和字母表的字符分隔开

####  非确定有限状态自动机

![](https://img-blog.csdn.net/20130717144235218?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcGFuanVuYmlhbw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


* 转换匹配：当前状态和字母表的一个字符相对，并且文本中的当前字符和该字符匹配
* ε-转换：通过红色的边转换到下个状态，并且不扫描任何字符