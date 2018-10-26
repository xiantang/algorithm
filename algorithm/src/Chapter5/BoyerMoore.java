package Chapter5;

import algs4.ST;

public class BoyerMoore {
    private int[] right;
    private String pat;
    BoyerMoore(String pat){
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        //初始化 全部置为-1
        for (int c = 0; c <R ; c++) {
            right[c] = -1;
        }
        //从左到右开始，不断更新每个字母最右边所在的索引
        for (int j = 0; j <M ; j++) {
            right[pat.charAt(j)]=j;
        }
        
    }
    public int search(String txt){
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <=N-M ; i+=skip) {
            skip = 0; //初始化为0
            for (int j = M-1; j >=0 ; j--) { // 模式指针向后退
                // 如果不匹配就进入if语句块
                if (pat.charAt(j)!=txt.charAt(i+j)){
                    skip = j- right[txt.charAt(i+j)]; // 跳跃的长度
                    if (skip<1)skip = 1;
                    break;
                }
            }
            // 如果跳跃的步数是0 就表明匹配成功
            if (skip == 0) return i;
        }
        return  N; //没有找到匹配
    }
}
