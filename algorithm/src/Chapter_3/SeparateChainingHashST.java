package Chapter_3;

public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private int J;
    private SequentialSearchST<Key, Value>[] st;

    private SeparateChainingHashST() {
        this(997);
    }
    private SeparateChainingHashST(int M,int J){
        this.J =J;
        this.M =M;
    }
    private SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < st.length; i++) {
            st[i] = new SequentialSearchST();
        }

    }
    private void  resize(){
        for (int i = 0; i <st.length ; i++) {
            for (SequentialSearchST.Node x = first; x != null; x = x.next) {
                i += 1;
            }
        }
    }
    private int hash(Key key) {

        return (key.hashCode() & 0x7fffffff) % M;
    }

    private Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    private void put(Key key, Value val) {

        st[hash(key)].put(key, val);
        N+=1;
        int A =N/st.length;
        if (A>J){

            resize();
        }
    }

    private boolean contain(Key key) {
        return get(key) != null;
    }

    private void delete(Key key) {
        if (!contain(key)) return;
        //先进行hash操作
        int i = hash(key);
        SequentialSearchST<Key, Value> st1 = this.st[i];
        st1.delete(key);
        N--;

    }



    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> chainingHashST = new SeparateChainingHashST<String, Integer>();
        chainingHashST.put("DD", 1);

        System.out.println(chainingHashST.contain("DF"));
        chainingHashST.delete("DD");
        System.out.println(chainingHashST.get("DD"));
        System.out.println(chainingHashST.N);
//        System.out.println(-214748364& 0x7FFFFFFF);
    }
}
