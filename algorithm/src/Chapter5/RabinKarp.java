package Chapter5;

public class RabinKarp {
    private String pat; // 模式字符串
    private long patHash; // 模式字符串的散列值
    private int M; // 模式字符串的长度
    private long Q = 997; // 一个很大的素数
    private int R = 256; // 字母表的大小
    private long RM; //R^(M-1)%Q

    public RabinKarp(String pat) {
        this.pat = pat;  //保存字符串
        this.M = pat.length();
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M); // 将模式字符串进行hash
    }

    // 获得hash的值
    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    // 检查模式与txt(i...i-M+1)是否匹配
    public boolean check(int i){
        return true;
    }

    private int search(String txt){
        int N = txt.length();
        long txtHash = hash(txt,M);
        if (patHash == txtHash&&check(0))return 0; // 一开始就匹配成功
        for (int i = M; i <N ; i++) {
            //减去第一个字母
            txtHash = (txtHash+Q-RM*txt.charAt(i-M)%Q)%Q;
            //加上最后一个字母
            txtHash = (txtHash*R+txt.charAt(i))%Q;
            if (txtHash == patHash)return i-M+1; //找到匹配了
        }
        return N; // 没有找到匹配

    }

    public static void main(String[] args) {
        RabinKarp boyerMoore = new RabinKarp("QWE");
        System.out.println(boyerMoore.search("dqwdwdQWE"));
    }
}
