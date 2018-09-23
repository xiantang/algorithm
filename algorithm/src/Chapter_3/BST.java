package Chapter_3;

//import org.w3c.dom.Node;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value value;
        private Node left = null;
        private Node right = null;
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.N = n;
        }

    }

    public int size() {
        return size(root);
    }

    public int size(Node node) {
        if (node == null) return 0;
        else return node.N;
    }

    private Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {

        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;

    }

    private void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            //如果只有头节点
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    private Key select(int k){
        return select(root,k).key;
    }

    private Node select(Node node,int k){
        if (node ==null) return null;
        int t = size(node.left);
        if (k<t){
            return select(node.left,k);
        }
        if (k>t){
            return select(node.right,k-t-1);
        }else return node;
    }
    private void deleteMin(){
        root = deleteMin(root);
    }
    private  Node  deleteMin(Node node){
        if (node.left == null){
            return node.right;
        }
        else {
            node.left = deleteMin(node.left);
        }
        node.N = size(node.left)+size(node.right)+1;
        return node;
    }

    private  void  deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node node){
        if (node.right == null){
            return node.left;
        }
        else {
            node.right = deleteMax(node.right);
            node.N = size(node.right)+size(node.left)+1;
            return node;
        }
    }


    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("K", 1);
        bst.put("C", 2);
        bst.put("G",3);
        bst.put("D",1);
        bst.put("A",1);
        bst.deleteMin();
        System.out.println(bst.size());
        bst.deleteMax();
        System.out.println(bst.size());
    }


}
