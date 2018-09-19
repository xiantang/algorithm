package Chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkArray<Item> implements Iterable{
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int N;
    public void add(Item item){
        Node oldfirst = first;
        N++;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public void delLast(){
        Node prelast = first ;
        Node last = first.next;
        if (first == null){
            throw new NoSuchElementException();
        }else  if (last == null){
            first = null;
        }else {
            while (last.next!=null){
                prelast = prelast.next;
                last = last.next;
            }
            prelast.next = null;

        }

    }
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current !=null;
        }
        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkArray<Integer> linkArray = new LinkArray<Integer>();
        linkArray.add(1);

        linkArray.delLast();
        for (Object i :linkArray) {
            System.out.println(i);
        }
    }
}
