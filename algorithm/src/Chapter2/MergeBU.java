package Chapter2;

import Chapter1.Example;

/**
 * @创建人:xiantang
 * @创建时间:21/08/18
 * @email:zhujingdi1998@gmail.com
 * @github:github.com/xiantang
 * @blog:zhanshengpipidi.cn/blog
 * @描述:
 **/
public class MergeBU  extends Example {
    private static Comparable[] aux;
    public   void sort(Comparable[] a){
            int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz <N ; sz+=sz) {
            for (int lo = 0; lo <N-sz ; lo+=sz) {
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }
    public void merge(Comparable[] a,int lo,int mid,int hi){
        int i = lo,j=mid+1;
        for (int k = lo; k <=hi ; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <=hi ; k++) {
            if (i>mid) a[k] = aux[j++];
            else if (j>hi) a[k] = aux[i++];
            else if (less(aux[j],aux[i])) a[k] = aux[k++];
            else a[k] = aux[i++];
        }
    }
}
