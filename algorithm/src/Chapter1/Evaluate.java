package Chapter1;

import algs4.StdIn;
import algs4.StdOut;

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("("))                   ;
            else if (s.equals("+"))   ops.push(s);
            else if (s.equals("-"))   ops.push(s);
            else if (s.equals("*"))   ops.push(s);
            else if (s.equals("/"))   ops.push(s);
            else if (s.equals("sqrt"))ops.push(s);
            else if (s.equals(")")){
                // 如果字符是")",弹出操作符和运算数,压入结果
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop()+v;
                else if (op.equals("-")) v = vals.pop()-v;
                else if (op.equals("*")) v = vals.pop()*v;
                else if (op.equals("/")) v = vals.pop()/v;
                else if (op.equals("sqrt")) v =Math.sqrt(v);
                vals.push(v);// 将double压入栈
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
