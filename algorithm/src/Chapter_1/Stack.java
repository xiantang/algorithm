package Chapter_1;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private class  Node{
        Item item;
        Node next;
    }
    private Node first;
    private int N;
    public boolean isEmpty(){
        return first == null;
    }
    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        Item pop_item = first.item;
        first = first.next;
        N--;
        return pop_item;
    }
    public class IteratorStack implements Iterator{
        private int i = N-1;
        private Node itFirst = first;
        @Override
        public boolean hasNext() {
            return i != 0;
        }

        @Override
        public Object next() {
            Node nextItem = itFirst.next;
            itFirst = nextItem;
            i--;

            return nextItem.item;

        }
    }
    @Override
    public Iterator<Item> iterator() {
        return new IteratorStack();
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        s.push("dd");
        s.push("dd");
        s.push("dd");
        s.push("dd");

        for (String i :s
             ) {
            System.out.println(i);
        }
    }
}
