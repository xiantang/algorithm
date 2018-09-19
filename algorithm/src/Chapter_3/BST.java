package Chapter_3;

import algs4.Queue;
import algs4.StdRandom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        ArrayList<Value> value;
        private Node left = null;
        private Node right = null;
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = new ArrayList<Value>();
            this.value.add(value);
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
        else{
            int s = StdRandom.uniform(x.value.size());
            return x.value.get(s);
        }

    }

    private Value nonRecursiveGet(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            if (cmp > 0) x = x.right;
            else {
                int  index = StdRandom.uniform(x.value.size()-1);
                return x.value.get(index);
            }
        }
        return null;
    }

    private void nonRecursivePut(Key key, Value value) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0 && x.left == null) {
                x.left = new Node(key, value, 1);
                break;
            } else if (cmp < 0) x = x.left;
            else if (cmp > 0 && x.right == null) {
                x.right = new Node(key, value, 1);
                break;
            } else if (cmp > 0) x = x.right;

            else{
                x.value.add(value);
            }
        }
        ArrayList<ArrayList<Node>> arrayList = printGraph();
        ArrayList<Node> nodeArrayList = new ArrayList<>();
        for (ArrayList<Node> list : arrayList
        ) {
            for (Node ele : list
            ) {
                nodeArrayList.add(ele);
            }

        }
        Collections.reverse(nodeArrayList);
        for (Node ele : nodeArrayList
        ) {
//            System.out.println(ele.key);
            ele.N = size(ele.left) + size(ele.right) + 1;
        }

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
        else x.value.add(value);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    private Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int t = size(node.left);
        if (k < t) {
            return select(node.left, k);
        }
        if (k > t) {
            return select(node.right, k - t - 1);
        } else return node;
    }

    private void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = deleteMin(node.left);

            node.N = size(node.left) + size(node.right) + 1;

            return node;
        }
    }

    private void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) {
            return node.left;
        } else {
            node.right = deleteMax(node.right);
            node.N = size(node.right) + size(node.left) + 1;
            return node;
        }
    }

    private void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node x = node;
            node = min(x.left);
            node.right = deleteMin(x.right);
            node.left = x.left;

        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }

    }

    private Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return min(node.right);
        }

    }

    private void print() {
        print(root);
    }


    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }

    private ArrayList<ArrayList<Node>> printGraph() {
        Queue<Node> queue = new Queue<>();
        ArrayList<ArrayList<Node>> listAll = new ArrayList<>();

        queue.enqueue(root);
        while (!queue.isEmpty()) {
            int i = 0;
            int len = queue.size();
            ArrayList<Node> list = new ArrayList<Node>();
            while (i < len) {

                Node node = queue.dequeue();
                list.add(node);
//                System.out.println(node.key);
                if (node.left != null) {
                    queue.enqueue(node.left);
                }
                if (node.right != null) {
                    queue.enqueue(node.right);
                }
                i += 1;
            }
            listAll.add(list);

        }
        return listAll;

    }

    public void println() {
        ArrayList<ArrayList<Node>> arrayList = printGraph();
        for (ArrayList<Node> list : arrayList
        ) {
            for (Node ele : list
            ) {
                System.out.print(ele.key + " ");
            }
            System.out.println("\n");
        }
    }

    private Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null) return;
        int cmpa = lo.compareTo(node.key);
        int cmpb = hi.compareTo(node.key);
        if (cmpa < 0) keys(node.left, queue, lo, hi);
        if (cmpa <= 0 && cmpb >= 0) queue.enqueue(node.key);
        if (cmpb > 0) keys(node.right, queue, lo, hi);


    }


    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("K", 1);
        bst.put("C", 2);
        bst.put("G", 3);
        bst.put("D", 1);
        bst.put("A", 1);
        bst.put("A", 2);
        bst.put("A", 3);
        bst.put("A", 4);

        System.out.println(bst.get("A"));
        bst.delete("A");
        System.out.println(bst.get("A"));
//        int N = 100;
//        for (int i = 0; i<N ; i++) {
//            Integer a = StdRandom.uniform(10);
//            char c = (char)(StdRandom.uniform(25)+'a');
//            bst.put(c+"",a);
//        }
//
//        for (int i = 0; i <10000 ; i++) {
//            int num  = StdRandom.uniform(N);
//
//            bst.delete(bst.select(num));
//            Integer a = StdRandom.uniform(10);
//            char c = (char)(StdRandom.uniform(25)+'a');
//            bst.put(c+"",a);
//        }





//        bst.println();
//        for (Object key:bst.keys("B","D")
//             ) {
//            System.out.println(key);
//        };
//        bst.nonRecursivePut("B",1);
//        System.out.println(bst.get("B"));

//        bst.deleteMin();
//        System.out.println(bst.size());
//        bst.deleteMax();
//        System.out.println(bst.size());
//        System.out.println(bst.min(bst.root).key);;
//        bst.delete("K");
//        System.out.println(bst.get("K")
//        );

    }


}
