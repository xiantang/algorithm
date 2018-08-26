package Chapter_2;

import algs4.Transaction;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SelectP {
    public static void sort(Object[] a, Comparator c){
        int N = a.length;
        for (int i = 0; i <N; i++) {
//            for (int j = i; j >0 && less(c,a[j],a[j-1]); j--) {
//                exch(a,j,j-1);
//            }
            int min = i;
            for (int j = i+1; j <a.length ; j++) {
                if (less(c,a[j],a[min])){
                    exch(a,j,min);
                }
            }
        }
    }
    public  static boolean less(Comparator a,Object v,Object w ){
          return  a.compare(v,w)<0;
    }
    private static void exch(Object [] a,int v ,int  w){
        Object t = a[v];
        a[v]  = a[w];
        a[w] =t;
    }
    private static void toStead(Transaction[] a, Comparator c){
        Map map = new HashMap();
        for (int i = 0; i <a.length ; i++) {
            map.put(a[i],i);
        }
        SelectP.sort(a,c);

        for (int i = 0; i<a.length ; i++) {
            int temp = i;
            if (temp==0){
            }
            else if (a[temp-1].who().equals(a[temp].who()) ){
                while (a[temp-1].who().equals(a[temp].who())){
                    if ((int)map.get(a[temp-1])>(int)map.get(a[temp])){
                        exch(a,temp-1,temp);
                        temp--;
                    }else break;
                }
            }
        }
    }
    public static void main(String[] args) {
        Transaction[] a = new Transaction[6];
        a[0] = new Transaction("Turing   6/17/1990  644.08");
        a[1] = new Transaction("Turing   6/11/1990  644.08");
        a[2] = new Transaction("Turing   6/15/1990  644.08");
        a[3] = new Transaction("Tarjan   3/26/2002 4121.85");
        a[4] = new Transaction("Knuth    6/14/1999  288.34");
        a[5] = new Transaction("Dijkstra 8/22/2007 2678.40");
        SelectP.toStead(a,new Transaction.HowMuchOrder());
//        SelectP.sort(a,new Transaction.HowMuchOrder());
//        for (int i = 0; i <a.length ; i++) {
//            System.out.println(a[i].amount());
//            System.out.println(a[i].when());
//        }
    }

}
