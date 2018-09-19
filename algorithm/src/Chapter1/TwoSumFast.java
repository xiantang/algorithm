package Chapter1;

import algs4.BinarySearch;
import algs4.StdOut;

import java.util.Arrays;

public class TwoSumFast {
    public static int count(int[] a){
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i <N ; i++) {
            if (BinarySearch.rank(-a[i],a)>i){
                cnt ++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,-4,5,6,7,-8,-5,-1,2};
        StdOut.println(count(a));
    }

}
