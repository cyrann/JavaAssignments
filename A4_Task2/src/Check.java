import java.io.Serializable;

public class Check extends Payment implements Authorization, Serializable{
    private String name;
    private String bankID;
    private double amount;

    Check(String n, String b, double a){
        name = n;
        bankID = b;
        amount = a;
    }

    Check(){}

    public void authorized(){
        System.out.println("Authorizing...");
        System.out.println("Approved!");
    }
}
