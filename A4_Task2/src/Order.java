import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
    private Date date;
    private String status = "processing";
    private Customer customer;
    private Cash cash;
    private Check check;
    private Credit credit;
    static private List<Customer> customerList;
    List<OrderDetail> orderDetailList = new ArrayList();  // Store the OrderDetails, one for each kind of juice

    Order(Date date, int customerId){
        this.date = date;
        matchCustomer(customerId);
    }

    Order(){}



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static void setCustomerList(List<Customer> customerList) {
        Order.customerList = customerList;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    void summarizeOrder(){
        System.out.printf("%-6s%-10s%-6.2f%-%6.2f", "Date", "Customer", "Total", "Tax");
        System.out.printf("%-6s%-10s%-6.2f%-%6.2f", date.toString().substring(4,10), customer.getName(), calcTotal(), calcTax());
    }

    void printOrder(){
        System.out.println("Time: " + date.toString() +
                "\nCustomer ID: " + customer.getId() +
                "\nCustomer Name: " + customer.getName() +
                "\nPayment: ");
        if (cash != null){
            System.out.println("Cash:" + cash.getCashTendered());
        }
        if (check != null){
            System.out.println("Check" + check.getAmount());
        }
        if (credit != null){
            System.out.println("Credit" + credit.getAmount());
        }
        System.out.println("\nPurchase List\n");
        System.out.printf("%-10s%-15s%-10s%-15s%-10s%n", "Juice No.", "Name", "Quantity", "Unit Price", "Unit Weight(kg)");
        for (OrderDetail o : orderDetailList){
            o.printOrderList();
        }
        System.out.printf("Total Weight: " + calcTotalWeight() + "kg");
        System.out.printf("\nSubTotal: %4.2fAUD",calcSubTotal());
        System.out.printf("\nTax: %4.2fAUD",calcTax());
        System.out.printf( "\nTotal: %4.2fAUD\n",calcTotal());
    }

    double calcTax(){
        double tax = 0;
        OrderDetail od;
        for (int i = 0; i < orderDetailList.size() ; i++) {
            od = orderDetailList.get(i);
            tax += od.calcSubTotal() * od.getTaxStatus();
        }
        return tax;
    }

    double calcSubTotal(){
        double subTotal = 0;
        OrderDetail od;
        for (int i = 0; i < orderDetailList.size() ; i++) {
            od = orderDetailList.get(i);
            subTotal += od.calcSubTotal();
        }
        return subTotal;
    }

    double calcTotal(){
        return calcTax() + calcSubTotal();
    }

    double calcTotalWeight(){
        double totalWeight = 0;
        OrderDetail od;
        for (int i = 0; i < orderDetailList.size(); i++) {
            od = orderDetailList.get(i);
            totalWeight += od.calcWeight();
        }
        return totalWeight;
    }

    void matchCustomer(int id){
        for (int i = 0; i < customerList.size(); i++) {
            if (id == customerList.get(i).getId()) {
                customer = customerList.get(i);
                break;
            }
        }
    }

    void addOrderDetail(){
        int juiceNo;
        int quantity;
        String conOrder;
        do {
            System.out.println("Input juice No.:");
            juiceNo = CheckInput.inputInt();
            System.out.println("Input juice quantity:");
            quantity = CheckInput.inputInt();
            orderDetailList.add(new OrderDetail(quantity, juiceNo));
            System.out.println("Continue ordering? (y/n)");
            conOrder = CheckInput.inputString("n", "y");
        }
        while(conOrder.equals("y"));
    }

    void addDate(){
        setDate(new Date());
    }

    void addPayment(){
        String conPay = null;
        do {
            System.out.println("Choose your payment:\n1.Cash\n2.Check\n3.Credit");
            int i = CheckInput.inputInt(1,3);
            switch (i){
                case 1:
                    System.out.println("How much do you want to pay by cash?");
                    double cashAmount = CheckInput.inputDouble();
                    cash = new Cash(cashAmount);
                    System.out.println("Continue with another payment?(y/n)");
                    conPay = CheckInput.inputString("n", "y");
                    break;
                case 2:
                    System.out.println("How much do you want to pay by check?");
                    double checkAmount = CheckInput.inputDouble();
                    System.out.println("Input the name:");
                    String checkName = CheckInput.inputString();
                    System.out.println("Input the bank ID：");
                    String checkBankID = CheckInput.inputString();
                    check = new Check(checkName, checkBankID, checkAmount);
                    check.authorized();
                    System.out.println("Continue with another payment?(y/n)");
                    conPay = CheckInput.inputString("n", "y");
                    break;
                case 3:
                    System.out.println("How much do you want to pay by credit?");
                    double creditAmount = CheckInput.inputDouble();
                    System.out.println("Input the card number:");
                    String creditCardNumber = CheckInput.inputString();
                    System.out.println("Input the card type:");
                    String creditCardType = CheckInput.inputString();
                    System.out.println("Input the expire date(yy-MM):");
                    String creditExpDate = CheckInput.inputString();
                    credit = new Credit(creditCardNumber, creditCardType, creditExpDate, creditAmount);
                    credit.authorized();
                    System.out.println("Continue with another payment?(y/n)");
                    conPay = CheckInput.inputString("n", "y");
                    break;

            }
        }
        while (conPay.equals("y"));
    }
}


