package Chapter_3;

public class RedBlackTree<Key extends Comparable<Key>,Value> {
    private  static final boolean RED=true;
    private  static final boolean BLACK=false;


    private  class Node{
        Key key;
        Value value;
        Node left,right;
        int N;
        boolean color;
        Node(Key key,Value val,int N,boolean color){
            this.key = key;
            this.value = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if (x == null){
            return false;
        }
        return x.color == RED;
    }




}
