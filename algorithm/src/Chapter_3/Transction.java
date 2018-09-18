package Chapter_3;

import javax.xml.crypto.Data;
import java.util.Date;

public class Transction {
    private final  String who;
    private final Date when;
    private final double amount;
    private final int hash;

    public Transction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
        hash = hashCode();

    }

    public  int hashCode(){
        int hash = 17;
        hash = 31*hash + who.hashCode();
        hash = 31*hash+when.hashCode();
        hash = 31*hash+((Double)amount).hashCode();
        return hash;
    }

    public static void main(String[] args) {
        Date date =new Date();
        Transction transction = new Transction("DD",date,10.1);
        System.out.println(transction.hashCode());
        System.out.println(transction.hash);
    }
}
