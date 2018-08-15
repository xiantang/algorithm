package Chapter_1;

/**
 * @创建人:xiantang
 * @创建时间:15/08/18
 * @email:zhujingdi1998@gmail.com
 * @github:github.com/xiantang
 * @blog:zhanshengpipidi.cn/blog
 * @描述:
 **/
public class Selection extends Example{
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i <N ; i++) {
            int min = i;
            for (int j = i+1; j <N ; j++) {
                if (less(a[j],a[min])) min = j;
            }
            exch(a,i,min);
        }
    }

    public static void main(String[] args) {
        Integer[] a= new Integer[10];
        for (int i = 0; i <a.length ; i++) {
            a[i] = i;
        }
        new Selection().sort(a);
        for (int i:a
             ) {
            System.out.println(i);
        }
    }
}
