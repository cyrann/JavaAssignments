import java.io.Serializable;

public class Cash extends Payment implements Serializable{
    private double cashTendered;
    private double change;

    Cash(double i){
        cashTendered = i;
    }

    Cash(){}

    public double getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    void makeChange(){
        setChange(getCashTendered() - getCashTendered());
    }
}
