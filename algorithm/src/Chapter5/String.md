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
