package Chapter5;

public class TST<Value> {
    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value val;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (c < x.c) {
            x.left = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;

        }
        return x;

    }

    private Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        } else return x.val;
    }

    private Node get(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) {
            return null;
        }
        if (c > x.c) {
            return x.right = get(x.right, key, d);
        } else if (c < x.c) {
            return x.left = get(x.left, key, d);
        } else if (d < key.length() - 1) {
            return x.mid = get(x.mid, key, d + 1);
        } else return x;
    }

    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();
        tst.put("dasda", 1);
        System.out.println(tst.get("dasda"));
        System.out.println(tst.get("aaaa"));
    }
}
