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
    public Iterable<String> keysThatMatch(String pat){
        Queue<String> q =  new Queue<String>();
        collect(root,"",pat,q);
        return q;

    }
    private void collect(Node x,String pre,String pat,Queue<String> q){
        int d= pre.length();
        if (x==null)return;
        if (d== pat.length()&&x.val!=null)q.enqueue(pre);
        if (d == pat.length())return;
        char next = pat.charAt(d);
        for (int c = 0; c <R ; c++) {
            if (next=='.'||next==c){
                collect(x.next[c],pre+(char)c,pat,q);
            }
        }

    }
    public String longstPrefixOf(String s){
        int length = search(root,s,0,0);
        return s.substring(0,length);
    }
    private int search(Node x,String s, int d,int length){
        if (x==null)return length;
        if (x.val!=null)length=d;
        if (s.length()==d)return length; //如果长度和字符串相同就返回长度
        char c = s.charAt(d);
        return search(x.next[c],s,d+1,length);
    }
    public static void main(String[] args) {
        TrieST<Integer> trieST = new TrieST<Integer>();
        trieST.put("abnd",1123);
        trieST.put("abcd",2123);
        trieST.put("aeqeq",21);
        System.out.println(trieST.longstPrefixOf("abn"));

    }


}



