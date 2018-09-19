package Chapter1;

import algs4.StdOut;

import java.util.Iterator;

public class FixedCapatityStack<Item> implements Iterable<Item> {
    private Item[] a;
    //类似一个参数
    private int N;

    public FixedCapatityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public  class  ReveseArrayIterator implements Iterator<Item>{
        private int i = N;
        public boolean hasNext(){
            return i>1;
        }

        public Item next(){
            return a[i--];
        }
        public void remove(){

        }

    }



    @Override
    public Iterator<Item> iterator() {
        return new ReveseArrayIterator();
    }

    public Item pop(){
        Item item = a[--N];
        a[N] = null;
        if(N>0 && N==a.length/4) resize(a.length/2);
        return item;
    }

    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < max; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void push(Item item){
        if (N == a.length) resize(2*a.length);
        else a[N++] = item;
    }
    public static void main(String[] args) {
        FixedCapatityStack<String> s;
        s = new FixedCapatityStack<String>(100);
        for (int i = 0; i <10 ; i++) {
            s.push("dddd");
        }
        s.pop();
        for (String a: s){
            StdOut.println(a);
        }
    }
}
