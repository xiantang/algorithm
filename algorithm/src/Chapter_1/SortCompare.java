package Chapter_1;

import algs4.StdRandom;

public class SortCompare {
    public static double time(String alg,Comparable[] a){
        /**
        * @Description: 传入字符串 并使用字符串来排序
        * @Param: [alg, a]
        * @return: double
        * @Author: Mr.Zhu
        * @Date: 18-8-18
        */
        Stopwatch timer = new Stopwatch();

        if (alg.equals("Insertion")) new Insertion().sort(a);
        if (alg.equals("Selection")) new Selection().sort(a);
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg,int N,int T){
        /**
        * @Description: 使用alg作为参数的 传入参数进行排序
        * @Param: [alg, N, T] T 重复的次数
        * @return: double
        * @Author: Mr.Zhu
        * @Date: 18-8-18
        */
        double total =0.0;
        Double[] a = new Double[N];
        for (int i = 0; i <T ; i++) {
            for (int j = 0; j <N ; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg,a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        int N = 1000;
        int T = 1000;
        double t1 = timeRandomInput(alg1,N,T);
        double t2 = timeRandomInput(alg2,N,T);

        System.out.println(t1);
        System.out.println(t2);
//        for (int i = 0; i <100 ; i++) {
//            System.out.println(i);
//
//        }
    }



}
