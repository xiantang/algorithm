package Chapter_1;

import algs4.StdIn;
import algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int N;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    public boolean isEmpty(){
        return first == null;
    }
    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }
        else oldlast.next = last;
        N++;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last=null;
        N--;
        return item;
    }
    public int size(){
        return N;
    }
    public class  ListIterator<Item> implements Iterator<Item> {
        private Node current;

        public ListIterator(Node first) {
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            if(hasNext()){
                throw new NoSuchElementException();
            }
            Item item = (Item) current.item;
            current = current.next;
            return item;

        }


    }

    public static void main(String[] args) {
        Queue<String> q= new Queue<String>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                q.enqueue(item);
            }
            else if (!q.isEmpty()) StdOut.print(q.dequeue()+" ");

        }
        System.out.println("(" +q.size()+" left on queue");
    }
}
