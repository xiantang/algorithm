package Chapter_1;

import algs4.StdIn;
import algs4.StdOut;

public class FixedCapacityStackOfString {
    private String[] a;
    private int N;
    public FixedCapacityStackOfString(int cap) {
        /**
          创建一个容量为cap的空栈
         **/
        a = new String[cap];
    }
    public boolean isEmpty(){
        return N==0;
    }
    public boolean isFull(){
        /**
        * @Description:
        * @Param: []
        * @return: boolean
        * @Author: Mr.Zhu
        * @Date: 18-8-12
        */
        return N==a.length;
    }
    public void push(String item){

        a[N++] = item;
    }
    public int size(){
        return N;
    }
    public String pop(){

        return a[--N];
    }


    public static void main(String[] args) {
        FixedCapacityStackOfString s;
        s = new FixedCapacityStackOfString(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }
            else if (!s.isEmpty()){
                StdOut.print(s.pop()+" ");
            }
        }
        StdOut.print("("+s.size()+"left on stack");
    }

}
