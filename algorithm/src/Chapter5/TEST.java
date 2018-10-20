package Chapter5;

import algs4.Alphabet;

public class TEST {
    public static void main(String[] args) {
//        String s = "HFHFKJHGKJBGKHGKJHGKJG";
//        String c = s.substring(3,6);
//
//        System.out.println(c);
        Alphabet alphabet = new Alphabet("ACGT");
        int R = alphabet.radix();
        int a [] = alphabet.toIndices("AAAAA");
        for (int i = 0; i <a.length ; i++) {
            System.out.println(i);
        }
        System.out.println(alphabet.toIndex('A'));
        System.out.println(R);
        System.out.println(alphabet.lgR());
//        System.out.println(R);

    }

}
