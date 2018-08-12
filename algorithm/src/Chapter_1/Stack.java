package Chapter_1;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private class  Node{
        Item item;
        Node next;
        Node(){

        }
        Node(Node x) {
            /**
            * @Description: 吊的飞起  用构造函数递归
            * @Param: [x]
            * @return:
            * @Author: Mr.Zhu
            * @Date: 18-8-12
            */
            item = x.item;
            if (x.next != null) next = new Node(x.next);

        }

    }
    public Stack(){

    }
    public Stack(Stack<Item> s,int N) {
        first = new Node(s.first);
        this.N = N;
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
        private int i = N;
        private Node itFirst = first;
        @Override
        public boolean hasNext() {
            return i != 0;
        }

        @Override
        public Object next() {
            Item nextItem = itFirst.item;
            itFirst = itFirst.next;
            i--;

            return nextItem;

        }
    }
    @Override
    public Iterator<Item> iterator() {
        return new IteratorStack();
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        int N = 50;
        while (N>0){
            s.push(N%2);
            N = N/2;
        }
        Stack<Integer> t = new Stack<Integer>(s,s.N);

        for (int i:t
             ) {
            System.out.println(i);
        }
    }
}
