package Chapter3;

public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private int J = 3;
    private SequentialSearchST<Key, Value>[] st;

    private SeparateChainingHashST() {
        this(997);
    }

    private SeparateChainingHashST(int M, int J) {
        this.J = J;
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];

    }

    private SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < st.length; i++) {
            st[i] = new SequentialSearchST();
        }

    }

    private void resize() {
//        System.out.println("RESIZE");
        int newM = M*2;
        SeparateChainingHashST<Key,Value> separateChainingHashST =
                new SeparateChainingHashST<Key,Value>(newM);
        for (int i = 0; i < st.length; i++) {
            for (SequentialSearchST.Node x = st[i].first; x != null; x = x.next) {
                separateChainingHashST.put((Key)x.key,(Value)x.value);

            }
        }
        st = separateChainingHashST.st;
        M = 2*M;

    }

    private int hash(Key key) {

        return (key.hashCode() & 0x7fffffff) % M;
    }

    private Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    private void put(Key key, Value val) {
//        System.out.println(hash(key));
//        System.out.println(hash(key));
        st[hash(key)].put(key, val);
        N += 1;
        int averageLengh = N / M;
        if (averageLengh > J) {

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
        SeparateChainingHashST<String, Integer> chainingHashST =
                new SeparateChainingHashST<String, Integer>(2);
        chainingHashST.put("DD", 1);
        chainingHashST.put("DF", 2);
        chainingHashST.put("DC", 2);
        chainingHashST.put("DJ", 2);
        chainingHashST.put("DQ", 1);
        chainingHashST.put("DW", 2);
        chainingHashST.put("DE", 2);
        chainingHashST.put("CC", 2);
        chainingHashST.put("EE", 1);
        chainingHashST.put("TT", 2);
        chainingHashST.put("BB", 6);
        chainingHashST.put("DQX", 2);
//        System.out.println(chainingHashST.contain("DF"));
//        chainingHashST.delete("DD");
//        System.out.println(chainingHashST.get("DD"));
//        System.out.println(chainingHashST.N);

//        System.out.println(-214748364& 0x7FFFFFFF);
    }
}
