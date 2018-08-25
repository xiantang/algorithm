package Chapter_2;

import algs4.StdRandom;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        //构造函数维护一个N+1的数组
        pq = (Key[]) new Comparable[maxN+1];
    }
    public MaxPQ(Comparable []a ){
        N = a.length-1;
        pq = (Key[]) a;
        for (int i = 1; i <a.length ; i++) {
            swim(i);
        }

    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    private boolean less(int i,int j){
        return  pq[i].compareTo(pq[j])<0;
    }
    private void exch(int i,int j){
        Key temp;
        temp = pq[i];
        pq[i] = pq[j];
        pq[j] =temp;
    }
    private void swim(int k){
        while (k>1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }
    private void sink(int k ){
        while (2*k<=N){
            int j = 2*k;
            if (j>N && less(j,j+1)) j++;
            if (!less(k,j))break;
            exch(k,j);
            k = j;
        }
    }

    public void  insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        pq [1] = pq[N--];
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public static void main(String[] args) {
//        MaxPQ<Integer> maxPQ = new MaxPQ<>(5);
//        maxPQ.insert(5);
//        maxPQ.insert(8);
//        maxPQ.insert(3);
//        maxPQ.insert(3);
//        maxPQ.insert(3);
//        System.out.println(maxPQ.delMax());
//        System.out.println(maxPQ.delMax());
//        System.out.println(maxPQ.delMax());
//        System.out.println(maxPQ.delMax());
//        System.out.println(maxPQ.delMax());
        Double [] dou = new Double[12];
//        Double[] a= new Double[10];
        for (int i = 1; i <dou.length ; i++) {
            dou[i] = StdRandom.uniform();

        }
        MaxPQ maxPQ = new  MaxPQ(dou);
        for (int i = 0; i <maxPQ.pq.length ; i++) {
            System.out.println(maxPQ.pq[i]);
        }
    }
}
