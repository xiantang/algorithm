package Chapter_3;

//import org.w3c.dom.Node;

public class BST<Key extends Comparable<Key>,Value> {
    private Node root;
    private  class  Node{
        Key key;
        Value value;
        private Node left = null;
        private Node right = null;
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
    private Value get(Key key){
       return get(root,key);
    }
    private Value get(Node x,Key key){
        if (x ==null)return null;
        int cmp = key.compareTo(x.key);
        if (cmp<0)return get(x.left,key);
        else  if (cmp>0) return get(x.right,key);
        else return x.value;

    }
    private void put( Key key,Value value){
        root = put(root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        if (x == null) {
            //如果只有头节点
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp<0) x.left = put(x.left,key,value);
        else if(cmp>0) x.right= put(x.right,key,value);
        else x.value = value ;
        x.N = size(x.left) +size(x.right)+1;
        return x;
    }

    public static void main(String[] args) {
        BST<String,Integer> bst = new BST<>();
        bst.put("A",1);
        bst.put("C",2);
        System.out.println(bst.get("A"));
        System.out.println(bst.get("F"));
    }


}
