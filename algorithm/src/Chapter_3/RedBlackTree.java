package Chapter_3;

import java.util.concurrent.BlockingDeque;

public class RedBlackTree<Key extends Comparable<Key>,Value> {
    private  static final boolean RED=true;
    private  static final boolean BLACK=false;
    private  Node root;

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
    private  int size(){
        return size(root);
    }
    private int size(Node x){
        if (x == null)return 0;
        else  return x.N;


    }
    private boolean isRed(Node x){
        if (x == null){
            return false;
        }
        return x.color == RED;
    }

    public void  put(Key key,Value val){
        if (root ==null){
            root = new Node(key,val,0,BLACK);
        }
        else root = put(root,key,val);
    }

    public Node put(Node x , Key key, Value val){
        if (x== null){
            return new Node(key,val,0,BLACK);
        }
        int cmp = key.compareTo(x.key);
        if (cmp>0) x.right = put(x.right,key,val);
        else if (cmp<0) x.left = put(x.left,key,val);
        else x.value = val;
        if(isRed(x))

        x.N = size(x.left)+size(x.right)+1;
    }


}
