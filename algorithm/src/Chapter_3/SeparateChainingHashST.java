package Chapter_3;

public class SeparateChainingHashST<Key, Value> {

//    private static final int M = 97;
//
//    private  int hash(Key key){
//        return (key.hashCode() & 0x7fffffff)%M;
//    }

//    public static void main(String[] args) {
//        HashTable<String,String> hashTable = new HashTable<String,String>();
//        System.out.println(hashTable.hash("ddd"));

    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    private SeparateChainingHashST() {
        this(997);
    }

    private SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < st.length; i++) {
            st[i] = new SequentialSearchST();
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

    }

    private boolean contain(Key key){
       return get(key)!=null;
    }

    private void  delete(Key key){

    }


    public static void main(String[] args) {
        SeparateChainingHashST<String,Integer> chainingHashST = new SeparateChainingHashST<String, Integer>();
        chainingHashST.put("DD",1);
        System.out.println(chainingHashST.get("DD"));
        System.out.println(chainingHashST.contain("DF"));
    }
}
