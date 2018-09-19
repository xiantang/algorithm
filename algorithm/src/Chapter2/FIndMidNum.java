package Chapter2;

import Chapter1.Selection;
import algs4.StdRandom;

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

    public static void main(String[] args) {
        Integer[] dou = new Integer[13];
//        Double[] a= new Double[10];
        for (int i = 0; i < dou.length; i++) {
            dou[i] = StdRandom.uniform(3);

        }
        System.out.println(new FIndMidNum().partition(dou, 0, 12));
        ;
        new Selection().sort(dou);
        for (int i = 0; i < dou.length; i++) {
            System.out.println(dou[i]);
        }
    }
}


