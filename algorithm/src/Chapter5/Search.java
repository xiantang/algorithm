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

    public static void main(String[] args) {
        String d= "asqqqqqq";
        String b ="q";
        System.out.println(search(b,d));
    }
}
