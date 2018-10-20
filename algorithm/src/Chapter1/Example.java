package Chapter1;

import algs4.StdOut;
import algs4.StdRandom;

/**
 * @创建人:xiantang
 * @创建时间:15/08/18
 * @email:zhujingdi1998@gmail.com
 * @github:github.com/xiantang
 * @blog:zhanshengpipidi.cn/blog
 * @描述:
 **/
public class Example {
    public void sort(Comparable[] a) {

    }

    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + "  ");
        }
        StdOut.println();
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public void toRight(int pre, int la, Comparable[] a) {
        Comparable temp = a[la];
        for (int i = la; i > pre; i--) {
            a[i] = a[i - 1];
        }
        a[pre] = temp;


    }

    public static void main(String[] args) {
        Double[] a = new Double[10];
        for (int i = 9; i >= 0; i--) {
            a[i] = StdRandom.uniform();
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(i);
            System.out.println(a[i]);
        }
        System.out.println("------------");
        new Example().toRight(1, 6, a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(i);
            System.out.println(a[i]);
        }
    }
}
