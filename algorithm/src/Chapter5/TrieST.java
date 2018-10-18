package Chapter5;

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
}



