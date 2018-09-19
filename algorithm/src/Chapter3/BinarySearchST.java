package Chapter3;

import algs4.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity) {
        /**
         * @Description: 构造函数
         * @Param: [capacity]
         * @return:
         * @Author: Mr.Zhu
         * @Date: 18-8-29
         */
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Comparable[capacity];

    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key); // 求一下下标
        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        } else return null;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = val;
        N++;
    }

    public void delete(Key key) {
        /**
         * @Description: 删除一个元素
         * @Param: [key]
         * @return: void
         * @Author: Mr.Zhu
         * @Date: 18-8-29
         */
        int target = rank(key);
        if (target == N) {
            return;
        }
        if (target < N) {
            for (int i = target; i < N; i++) {
                values[i] = values[i + 1];
                keys[i] = keys[i + 1];
            }
        }
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) lo = mid + 1;
            else if (cmp < 0) hi = mid - 1;
            else return mid;
        }
        return lo;
    }

    public boolean isEmpty() {

        return N == 0;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceilling(Key key) {
        /**
         * 方法实现说明
         * @method ceilling
         * @author xiantang
         * @param       [key]
         * @return Key
         * @date 2018/9/1 20:37
         */
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(keys[i]) == 0) {
            //如果两个key 相同的就返回这个key 如果相同就返回
            return keys[i];
        } else return keys[i - 1];

    }

    public boolean isContain(Key key) {
        /**
         * 方法实现说明 如果不包含 就返回False
         * @method      contain
         * @author      xiantang
         * @param       [key]
         * @return      boolean
         * @date        2018/9/1 21:06
         */
        return get(key) != null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (isContain(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }


    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<String, Integer>(10);
        binarySearchST.put("A", 1);
        binarySearchST.put("B", 2);
        binarySearchST.put("C", 2);
        binarySearchST.put("D", 2);
        binarySearchST.put("E", 2);
//        System.out.println(binarySearchST.rank("F"));;
//        binarySearchST.delete("B");
//        System.out.println(binarySearchST.get("B"));
//        System.out.println(binarySearchST.max());
//        System.out.println(binarySearchST.select(1));
//        System.out.println(binarySearchST.floor("Z"));
        Iterable<String> iterable= binarySearchST.keys("A","B");
//        Iterator iterator = iterable.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());;
//        }
        for (String e:iterable
             ) {
            System.out.println(e);
        }
    }
}
