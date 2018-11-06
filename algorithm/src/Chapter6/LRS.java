package Chapter6;

import algs4.StdOut;
import algs4.SuffixArray;

public class LRS {
    public static void main(String[] args) {
        String txt = "ABC launched as a radio network on October 12, 1943, serving as the successor to the NBC Blue Network, which had been purchased by Edward J. Noble. It extended its operations to television in 1948, following in the footsteps of established broadcast networks CBS and NBC. In the mid-1950s, ABC merged with United Paramount Theatres, a chain of movie theaters that formerly operated as a subsidiary of Paramount Pictures. Leonard Goldenson, who had been the head of UPT, made the new television network profitable by helping develop and greenlight many successful series. In the 1980s, after purchasing an 80% interest in cable sports channel ESPN, the network's corporate parent, American Broadcasting Companies, Inc., merged with Capital Cities Communications, owner of several print publications, and television and radio stations. In 1996, most of Capital Cities/ABC's assets were purchased by The Walt Disney Company.\n" +
                "\n" +
                "The television network has eight owned-and-operated and over 232 affiliated television stations throughout the United States and its territories. ABC News provides news and features content for select radio stations owned by Citadel Broadcasting, which purchased the ABC Radio properties in 2007 (however relaunched in 2014).";
        int N = txt.length();
        SuffixArray sa = new SuffixArray(txt);
        String lrs = "";
        for (int i = 1; i <N ; i++) {
            int length = sa.lcp(i);
            if (length>lrs.length())
                lrs = sa.select(i).substring(0,length);
        }
        StdOut.println(lrs);
    }
}
