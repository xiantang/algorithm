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