import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Credit extends Payment implements Authorization, Serializable {
    private String number;
    private String type;
    private Date expDate;
    private double amount;

    Credit(String n, String t, String d, double a){
        number = n;
        type = t;
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM");
        try {
            expDate = sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        amount = a;
    }

    Credit(){}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public void authorized(){
        System.out.println("Authorizing...");
        System.out.println("Approved!");
    }
}
