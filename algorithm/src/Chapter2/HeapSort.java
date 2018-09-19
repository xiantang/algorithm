package Chapter2;

import Chapter1.Example;
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
