package Chapter3;

import algs4.Queue;

public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];

    }

    public LinearProbingHashST(int M) {
        this.M = M;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public int size(){
        return N;
    }

    private void resize(int len) {

        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(len);
        for (int i = 0; i < this.M; i++) {
            if (keys[i]!=null){
                t.put(keys[i],vals[i]);
            }

        }
        M = t.M;
        this.keys =t.keys;
        this.vals =t.vals;
    }
    private  void  delete(Key key){
        if (!contain(key))return;
        int i = hash(key);
        while (!key.equals(keys[i]))
        //找到i所在的位置
            i = (i+1)%M;
        //设置为`null`
        keys[i] = null;
        vals[i] = null;
        i = (1+i)%M;//向前走一步
        while (keys[i]!=null){
            //如果keys[i]!=null
            Key key1 = keys[i];
            Value val1 = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(key1,val1);
            i = (1+i)%M;
        }
        N--;
        if (N>0 && N== M/8) resize(M/2);
        for (int j = 0; j <keys.length ; j++) {
            System.out.println(keys[j]);
        }

    }
    public   boolean  contain(Key key){
        return get(key)!=null;
    }

    private int hash(Key key) {

        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {
        //如果已经存在的长度大于表的长度的二分之一
        //就调整表的长度
        if (N > M / 2) resize(2 * M);
        int j;
        for (j = hash(key); keys[j] != null; j = (j + 1) % M) {
            //如果不是`null` 就将j+1
            if (keys[j].equals(key)) {
                keys[j] = key;
                vals[j] = val;
                return;
            }

        }
        keys[j] = key;
        vals[j] = val;
        N++;


    }

    public Value get(Key key){


        for (int i = hash(key); keys[i]!=null ; i = (i+1)%M) {
            if (keys[i].equals(key)) {

                return vals[i];
            }
        }
        return null;
    }


    public Queue<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i <keys.length ; i++) {
            if (keys[i]!=null){
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> linearProbingHashST
                = new LinearProbingHashST<>();
        linearProbingHashST.put("S", 6);
        linearProbingHashST.put("E", 10);
        linearProbingHashST.put("A", 4);
        linearProbingHashST.put("R", 14);
        linearProbingHashST.put("C", 4);
        linearProbingHashST.put("H", 5);
        linearProbingHashST.put("E", 6);
        linearProbingHashST.put("X", 7);
        linearProbingHashST.put("A", 8);
        linearProbingHashST.put("M", 9);
        linearProbingHashST.put("P", 10);
        linearProbingHashST.put("E", 12);
        linearProbingHashST.delete("C");
        System.out.println(linearProbingHashST.get("P"));


//        for (:linearProbingHashST.keys
//             ) {
//
//        }

    }

}
