package Chapter_3;

public class SeparateChainingHashST<Key ,Value> {

    private static final int M = 97;

    private  int hash(Key key){
        return (key.hashCode() & 0x7fffffff)%M;
    }

    public static void main(String[] args) {
//        HashTable<String,String> hashTable = new HashTable<String,String>();
//        System.out.println(hashTable.hash("ddd"));

    }
}
