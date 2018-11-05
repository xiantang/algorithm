package Chapter6;

public class BTreeSET<Key extends Comparable<Key>, Value> {
    // max children per B-tree node = M-1
    // must be even and greater than 2
    private static final int M = 4;
    private Node root;     // root of the B-tree
    private int height;    // height of B-tree
    private int n;         // number of key-value pairs in B-tree

    private static final class Node {
        private int m;     // number of children
        private Entry[] children = new Entry[M]; // array of children

        private Node(int k) {
            m = k;
        }
    }

    private static class Entry {
        private Comparable key;
        private final Object val;
        private Node next; // helper field to iterate over array entries

        public Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty B-tree
     */
    public BTreeSET() {
        root = new Node(0);
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns the height of this B-tree (for debugging).
     *
     * @return the height of this B-tree
     */
    public int height() {
        return height;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return search(root, key, height);
    }

    public Value search(Node x, Key key, int ht) {
        Entry[] children = x.children;

        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, (Key) children[j].key)) return (Value) children[j].val;
            }
        } else {
            for (int j = 0; j < x.m; j++) {
                if (j + 1 == x.m || less(key, (Key) children[j + 1].key))
                    return search(children[j].next, key, ht - 1);

            }
        }
        return null;
    }

    private void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("argument key to put() is null");
        Node u = insert(root, key, val, height);


    }

    private Node insert(Node h, Key key, Value val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);
        // external node
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, (Key) h.children[j].key)) break;
            }
            
        }
        // internal node 
        else {
            for (j = 0; j < h.m; j++) {
                
                if ((j + 1 == h.m) || less(key, (Key) h.children[j + 1].key)) {
                    Node u = insert(h.children[j++].next,key,val,ht-1);
                    if (u == null) return null;
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }
        for (int i = h.m; i >j ; i--) 
            h.children[i] = h.children[i-1];
        h.children[j] = t;
        h.m++;
        if (h.m<M) return null;
        else  return split(h);


    }

    private Node split(Node h) {
        Node t = new Node(M/2);
        h.m = M/2;
        for (int j = 0; j <M/2 ; j++)
            t.children[j] = h.children[M/2+j];
        return t;
    }

    private boolean less(Key key, Key key1) {
        return key.compareTo(key1) < 0;
    }

    private boolean eq(Key key, Key key1) {
        return key.compareTo(key1) == 0;
    }
}
