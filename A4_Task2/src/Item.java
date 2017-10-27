import java.io.Serializable;


public class Item  implements Serializable {
    private double shippingWeight;
    private String description;
    private double priceForQuantity;
    private int juiceNo;


    public void setShippingWeight(double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPriceForQuantity() {
        return priceForQuantity;
    }

    public void setPriceForQuantity(double priceForQuantity) {
        this.priceForQuantity = priceForQuantity;
    }


    public double getWeight(){
        return shippingWeight;
    }

    public int getJuiceNo() {
        return juiceNo;
    }

    public void setJuiceNo(int juiceNo) {
        this.juiceNo = juiceNo;
    }


}
