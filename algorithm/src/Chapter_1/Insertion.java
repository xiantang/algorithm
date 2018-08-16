package Chapter_1;

public class Insertion extends Example {
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N ; i++) {
            for (int j = i; j >0 ; j--) {
                if (less(a[j],a[j-1])){
                    exch(a,j,j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a= new Integer[10];
        for (int i = 9; i>=0 ; i--) {
            a[i] = i;
        }
        new Insertion().sort(a);
        for (int i:a
        ) {
            System.out.println(i);
        }

    }
}
