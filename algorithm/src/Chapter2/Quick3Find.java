package Chapter2;

/**
 * @创建人:xiantang
 * @创建时间:22/08/18
 * @email:zhujingdi1998@gmail.com
 * @github:github.com/xiantang
 * @blog:zhanshengpipidi.cn/blog
 * @描述:
 **/


import Chapter1.Example;


public class Quick3Find extends Example {
    public void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    public  void sort(Comparable[] a,int lo,int hi){
        if (hi<=lo) {return;
        }
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    public int partition(Comparable[] a,int lo,int hi){
        //主要是修改partiton
        int i = lo,j=hi+1;
        Comparable v;
        if(hi-lo>3){
            if (less(a[lo],a[lo+1]) && less(a[lo],a[lo+2]) && less(a[lo+1],a[lo+2]) ) v= a[lo+1];
            else if (less(a[lo+1],a[lo]) && less(a[lo+2],a[lo]) && less(a[lo+2],a[lo+1])) v = a[lo+2];
            else v= a[lo];
        }else v = a[lo];
        while (true){
            //先++再赋值
            while (less(a[++i],v)) if(i==hi) break;
            while (less(v,a[--j])) if(j==lo) break;
            if (i>=j)break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    public static void main(String[] args) {
        Double [] dou = new Double[10];
        for (int i = 0; i <dou.length ; i++) {
            dou[i] = i+0.0;
        }
        new Quick3Find().sort(dou);
        for (int i = 0; i <dou.length ; i++) {
            System.out.println(dou[i]);
        }
    }
}
