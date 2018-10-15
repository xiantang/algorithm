package Chapter5;

public class LSD {
    public static void sort(String a[],int W){
        int N = a.length;
        int R = 256;

        String[] aux = new String[N];
        for (int d = W-1;d>=0;d--) {
            int[] count = new int[R + 1]; //count是一个长度256的数组
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
                //+1是为了转换为ASCII
            }
            for (int r = 0; r <R ; r++) {
                count[r+1]+=count[r];
            }//count 转换为索引

            for (int i = 0; i <N ; i++) {

                aux[count[a[i].charAt(d)]++] = a[i];
             //分类到aux
            }

            for (int i = 0; i <N ; i++) {
                a[i] = aux[i];
                //回写
            }

        }


    }

    public static void main(String[] args) {
        String string[] = {"QASD","QWER","CDSA","EDFS","CFAA"};
        int W = 4;
        sort(string,W);
        for (String s
                :string
             ) {
            System.out.println(s);
        }

    }

}
