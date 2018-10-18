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

查找操作的三种情况：
1. 尾字符所对应的节点中的值非空
2. 尾字符所对应的节点的值为空
3. 查找结束于一条空链接