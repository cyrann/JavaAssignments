import java.io.Serializable;

public class Check extends Payment implements Authorization, Serializable{
    private String name;
    private String bankID;

    Check(String n, String b, double a){
        name = n;
        bankID = b;
        amount = a;
    }

    Check(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public void authorized(){
        System.out.println("Authorizing...");
        System.out.println("Approved!");
    }
}
