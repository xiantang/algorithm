package Chapter_2;

//import org.w3c.dom.Node;

public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.value = val;
            this.next = next;
        }


    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.value;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) x.value = val;
            return;
        }
        first = new Node(key, val, first);
    }

    public int size() {
        int i = 0;
        for (Node x = first; x != null; x = x.next) {
            i += 1;
        }
        return i;
    }

    public void delete(Key key) {
        Node y = null;
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                if (y == null) {
                    first = x.next;
                } else y.next = x.next;
            }
            y = x;
        }
    }

    public static void main(String[] args) {
        SequentialSearchST sequentialSearchST = new SequentialSearchST<String, Integer>();
        sequentialSearchST.put("qq",1);
        sequentialSearchST.put("qqq",1);
        sequentialSearchST.put("qqqa",1);
        sequentialSearchST.delete("qq");
        System.out.println(sequentialSearchST.get("qq"));
        sequentialSearchST.delete("qqqa");

    }
}
