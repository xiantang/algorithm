package Chapter_3;

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


    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> linearProbingHashST
                = new LinearProbingHashST<>();
        linearProbingHashST.put("S", 0);
        linearProbingHashST.put("E", 10);
        linearProbingHashST.put("A", 2);
        linearProbingHashST.put("R", 3);
        linearProbingHashST.put("C", 4);
        linearProbingHashST.put("H", 5);
        linearProbingHashST.put("E", 6);
        linearProbingHashST.put("X", 7);
        linearProbingHashST.put("A", 8);
        linearProbingHashST.put("M", 9);
        linearProbingHashST.put("P", 10);
        linearProbingHashST.put("E", 12);
        System.out.println(linearProbingHashST.get("M"));

    }

}
