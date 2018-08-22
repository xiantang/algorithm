package Chapter_2;

import Chapter_1.Example;
import Chapter_1.Insertion;

public class Quick extends Example {
    public void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    public  void sort(Comparable[] a,int lo,int hi){
        if (hi<=10+lo) {new Insertion().sort(a); return;
        }
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    public int partition(Comparable[] a,int lo,int hi){
        int i = lo,j=hi+1;
        Comparable v = a[lo];
        while (true){
            while (less(a[i++],v)) if(i==hi) break;
            while (less(v,a[--j])) if(j==lo) break;
            if (i>=j)break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }
}
