package Chapter3;


import java.util.HashMap;
import java.util.Map;

public class BinarySearchItemST<Key extends Comparable<Key>,Value> {

    private Map<Key,Value>[] item;

    public BinarySearchItemST(Map<Key,Value>[] maps){



    }




    public static void main(String[] args) {
        Map<String,Integer>[] maps= new  Map[10];
        for (int i = maps.length-1; i >0 ; i--) {
            Map<String,Integer> map = new HashMap<String, Integer>();
            map.put(""+i,i);
            maps[i] = map;
        }

        new BinarySearchItemST<String,Integer>(maps);
    }
}
