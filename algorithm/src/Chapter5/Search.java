package Chapter5;

public class Search {
    public static int search(String pat,String txt){
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i <N-M ; i++) {
            int j;
            for (j = 0; j <M ; j++) {
                if (txt.charAt(i+j)!=pat.charAt(j))break;

            }
            if(j==M){
                return i;
            }

        }
        return N;
    }
    public static int pointSearch(String pat,String txt){
        int i,M = pat.length();
        int j,N = txt.length();
        for (i = 0,j=0; i <M&&j<N ; j++) {
            if (pat.charAt(i)==txt.charAt(j))i++;
            else {
                i=0;
                j-=i;
            }
        }
        if (i==M) return j-M;
        else return N;


    }
    public static void main(String[] args) {

        String d= "asqqqqqq";
        String b ="q";
        System.out.println(pointSearch(b,d));
    }
}
