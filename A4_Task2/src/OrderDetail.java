import java.io.Serializable;
import java.util.List;

public class OrderDetail implements Serializable{
    private int quantity;
    static private double taxStatus = 0.05;
    static private List<Item> list;
    private Item item;

    OrderDetail(int quantity, int juiceNo){
        this.quantity = quantity;
        matchJuice(juiceNo, list);
    }

    OrderDetail(){}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(double taxStatus) {
        this.taxStatus = taxStatus;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public static List<Item> getList() {
        return list;
    }

    public static void setList(List<Item> list) {
        OrderDetail.list = list;
    }

    private void matchJuice(int juiceNo, List <Item>list){
        for (int i = 0; i < list.size(); i++) {
            if (juiceNo == list.get(i).getJuiceNo()) {
                item = list.get(i);
                break;
            }
        }
    }

    void printOrderList(){
        System.out.printf("%-10s%-15s%-10s%-15s%-10s%n", item.getJuiceNo(), item.getDescription(),
                quantity,item.getPriceForQuantity(), item.getWeight());
    }

    void inputTaxRate(){
        System.out.println("Input the tax rate:");
        setTaxStatus(CheckInput.inputDouble());
    }

    double calcSubTotal(){
        return item.getPriceForQuantity() * quantity;
    }

    double calcWeight(){

        return quantity * item.getWeight() ;
    }

}
