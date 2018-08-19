package Chapter_1;

import algs4.StdRandom;

public class Insertion extends Example {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
//        for (int i = 0; i <a.length ; i++) {
//            System.out.println(a[i]);;
//        }
        for (int i = 0; i < N ; i++) {
            int j = i;
            for (; j > 0 && less(a[i], a[j - 1]); j--) {

            }
            toRight(j,i,a);

        }
    }

    public static void main(String[] args) {
        Double[] a= new Double[10];
        for (int i = 9; i>=0 ; i--) {
            a[i] = StdRandom.uniform();
        }

        Insertion insertion =new Insertion();
        insertion.sort(a);
        for (double i:a
        ) {
            System.out.println(i);
        }

    }
}
