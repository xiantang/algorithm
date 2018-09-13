package Chapter_3;

public class LinearProbingHashST <Key,Value>{
    private int N;
    private int M =16;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];

    }
    public  LinearProbingHashST(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    private void resize(int len){

        LinearProbingHashST<Key,Value> t;
        t = new LinearProbingHashST<Key,Value>(len);
        for (int i = 0; i <this.M ; i++) {
            t.keys[i] = this.keys[i];
            t.vals[i] = this.vals[i];
        }
        M = t.M;

    }
    public  void  put(Key key,Value val){
        //如果已经存在的长度大于表的长度的二分之一
        //就调整表的长度
        if (N>M/2)resize(2*M);
        int i;
//        for (int j = 0; j < ; j++) {
//
//        }

    }

}
