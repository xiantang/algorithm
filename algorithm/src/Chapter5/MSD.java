package Chapter5;

import Chapter1.Insertion;

public class MSD {
    private static int R=256;   //基数
    private static final int M = 15;  //小数组切换阀值
    private static String[] aux;  //数据分类的辅助数组
    private static int charAt(String s,int d){
        if (d<s.length()){
            return s.charAt(d);
        }else return -1;
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a,0,N-1,0);
    }
    public static void sort(String[] a,int lo,int hi,int d){
        if (hi<lo){
            return;
        }
        int count[] = new int[R+2];

        for (int i =lo; i <=hi ; i++) {

            int index = charAt(a[i],d)+2;
            count[index]++;  //计算频率
        }
        for (int r = 0; r <R+1 ; r++) {
            count[r+1] += count[r];
        }
        for (int i = lo; i <=hi ; i++) {
            aux[count[charAt(a[i],d)+1]++] = a[i];
        }
        for (int i = lo; i <=hi ; i++) {
            a[i] = aux[i-lo];
        }
        for (int r = 0; r <R ; r++) {

            sort(a,lo+count[r],lo+count[r+1]-1,d+1);
        }
    }

    public static void main(String[] args) {
        String string[] = {"QASD","CDSB","QWER","CDSA","EDFS"};

        sort(string);
        for (String s
                :string
        ) {
            System.out.println(s);
        }
    }
}
