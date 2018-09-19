package Chapter3;



public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.value = val;
            this.N = N;
            this.color = color;
        }
    }

    private int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;


    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    public void put(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val, 0, BLACK);
        } else root = put(root, key, val);
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void filpColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public Value get(Key key) {
        return get(root, key);
    }


    public Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = x.key.compareTo(key);
        if (cmp > 0) return get(x.right, key);
        else if (cmp < 0) return get(x.left, key);
        else return x.value;

    }


    public Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 0, RED);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(x.right, key, val);
        else if (cmp < 0) x.left = put(x.left, key, val);
        else x.value = val;
        if (isRed(x.right) && !isRed(x.left)) {
            //如果右链接为红色
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            //如果左链接为红色并且左链接的左链接为红色
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            //如果两个链接都为红色
            filpColors(x);

        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    private boolean isEmpty(){
        return root.N == 0;
    }
    public void deleteMin() {
        if (!isRed(root.left)&&!isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }
    private  void deleteFilpColor(Node h){
           h.color = BLACK;
           h.left.color = RED;
           h.right.color = RED;
    }
    private Node moveRedLeft(Node h)
    {
        deleteFilpColor(h);
        if (isRed(h.right.left)){
            h.right  = rotateRight(h.right);
            h = rotateLeft(h);
        }

        return h;
    }
    private Node deleteMin(Node h) {
        if (h.left == null) return null;
        if(!isRed(h.left)&&!isRed(h.left.left)){
             h=moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return blance(h);
    }

    private Node blance( Node x){
        if (isRed(x.right) && !isRed(x.left)) {
            //如果右链接为红色
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            //如果左链接为红色并且左链接的左链接为红色
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            //如果两个链接都为红色
            filpColors(x);

        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;


    }

    public static void main(String[] args) {
        RedBlackTree<String, Integer> redBlackTree = new RedBlackTree<String, Integer>();
        redBlackTree.put("E", 10);
        redBlackTree.put("B", 20);
        redBlackTree.put("A", 30);
        System.out.println(redBlackTree.get("B"));
        redBlackTree.deleteMin();
    }
}
