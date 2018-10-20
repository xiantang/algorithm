package Chapter5;

public class Quick3string {
    private static int charAt(String s, int d) {
        //返回索引d 所在字母表的排序
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);// 找到第一个字符并且以它为中点
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);  //返回当前i的所指字符的大小
            if (t < v) exch(a, lt++, i++);//如果比当前字符小就到lt的位置
            else if (t > v) exch(a, i, gt--);//这里i不进行++是因为下次循环还要继续检验
            else i++;

        }

        sort(a, lo, lt - 1, d); // 排序上面当前字母 部分的
        if (v >= 0) sort(a, lt, gt, d + 1); // 排序相等的字母的下一部分
        sort(a, gt + 1, hi, d);// 排序下面当前字母的部分
    }

    private static void exch(String[] a, int lt, int i) {
        String temp = a[lt];
        a[lt] = a[i];
        a[i] = temp;
    }

    public static void main(String[] args) {
        String[] a = {"QQQQ", "QFFF", "QEEE"};
        sort(a);
        for (String s : a
        ) {
            System.out.println(s);
        }
    }
}
