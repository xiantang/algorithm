package Chapter_3;

//import org.w3c.dom.Node;

public class BST<Key extends Comparable<Key>,Value> {
    private Node root;
    private  class  Node{
        Key key;
        Value value;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.N = n;
        }

    }
    public  int size(){
        return size(root);
    }
    public  int size(Node node){
        if (node==null)return 0;
        else  return node.N;
    }
    private void put( Key key,Value value){
        root = put(root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        if (root == null) {
            //如果只有头节点
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp>0) put(x.left,key,value);
        else if(cmp<0) put(x.right,key,value);
        else x.value = value ;
        x.N = size(x.left) +size(x.right)+1;
        return x;

    }



}
