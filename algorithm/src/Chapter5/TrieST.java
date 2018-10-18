package Chapter5;

import algs4.In;
import algs4.Queue;

public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    private Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        else return (Value) x.val;

    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;//如果是null 就return null
        if (d == key.length()) return x; //当字符串的长度和当前字符的位置相同就return
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    private void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }
    private int size(){
        return size(root);
    }
    private int size(Node x){
        if (x==null)return 0;
        int count = 0;
        if (x.val!=null){
            count++;
        }
        for (int c = 0; c <R ; c++) {
            count+=size(x.next[c]);
        }
        return count;
    }

    private Iterable<String> keys(){
        //获取所有字符串
        return keysWithPrefix("");
    }

    private Iterable<String> keysWithPrefix(String pre){
        //获取所有以pre开头的字符串
        Queue<String> q= new Queue<String>();

        collect(get(root,pre,0),pre,q);
        return q;
    }

    private void collect(Node x,String pre,Queue<String> q){
        if (x==null)return;
        if (x.val!=null){
//            System.out.println(pre);
            q.enqueue(pre);
        }
        for (int r = 0; r <R ; r++) {
            collect(x.next[r],pre+(char)r,q);
        }

    }

    public static void main(String[] args) {
        TrieST<Integer> trieST = new TrieST<Integer>();
        trieST.put("abnaaa",1123);
        trieST.put("abcd",2123);
        trieST.put("aeqeq",21);
        System.out.println(trieST.keysWithPrefix("ab"));
        for (String s:trieST.keys()
             ) {
            System.out.println(s);
        }
    }


}



