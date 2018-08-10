package Chapter_1;

import algs4.StdIn;
import algs4.StdOut;

public class FixedCapatityStack<Item> {
    private Item[] a;
    //类似一个参数
    private int N;
    public FixedCapatityStack(int cap){
        a = (Item[]) new Object[cap];
    }
    public boolean isEmpty(){
        return N== 0;
    }
    public int size(){
        return N;
    }
    public void push(Item item){
        a[N++] = item;
    }
    public Item pop(){
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapatityStack<String> s;
        s=new FixedCapatityStack<String>(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }else if (!s.isEmpty()) StdOut.print(s.pop()+" ");
        }
    }
}
