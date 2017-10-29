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
        System.out.printf("%-10s%-10s%-10s%-10s%-10s\n", "Date", "Customer", "Total", "Tax", "Status");
        System.out.printf("%-10s%-10s%-10.2f%-10.2f%-10s\n", date.toString().substring(4,10), customer.getName(), calcTotal(), calcTax(), getStatus());
    }

    void printOrder(){
        System.out.println("----------Receipt----------");
        System.out.println("Time: " + date.toString() +
                "\nCustomer ID: " + customer.getId() +
                "\nCustomer Name: " + customer.getName() +
                "\nPayment: ");
        if (cash != null){
            System.out.println("Cash: " + cash.getCashTendered());
        }
        if (check != null){
            System.out.println("Check: " + check.getAmount());
        }
        if (credit != null){
            System.out.println("Credit: " + credit.getAmount());
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
            System.out.println("Input juice No. from the index:");
            System.out.printf("%-5s%-15s\n", "No.", "Name");
            for (Item i: OrderDetail.getList()){
                System.out.printf("%-5s%-15s\n", i.getJuiceNo(), i.getDescription());
            }
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
        boolean conPay = true;
        double amountToPay = calcTotal();
        double pAmount;  // Amount of a certain payment
        double cashAmount = 0;
        double checkAmount = 0;
        double creditAmount = 0;
        System.out.println("You have to pay " + amountToPay + " AUD.");
        do {
            System.out.println("Choose your payment:\n1.Cash\n2.Check\n3.Credit");
            int i = CheckInput.inputInt(1,3);
            switch (i){
                case 1:
                    System.out.println("How much do you want to pay by cash?");
                    pAmount = CheckInput.inputDouble();
                    if (calcPayment(amountToPay, pAmount)) {
                        conPay = false;
                        pAmount = amountToPay; //If the payment amount exceeds the amount to pay.
                    }
                    else {
                        conPay = true;
                        System.out.println("Continue making payment.");
                    }
                    amountToPay -= pAmount;
                    cashAmount += pAmount;
                    cash = new Cash(cashAmount);
                    break;
                case 2:
                    System.out.println("How much do you want to pay by check?");
                    pAmount = CheckInput.inputDouble();
                    System.out.println("Input the name:");
                    String checkName = CheckInput.inputString();
                    System.out.println("Input the bank ID：");
                    String checkBankID = CheckInput.inputString();
                    if (calcPayment(amountToPay, pAmount)) {
                        conPay = false;
                        pAmount = amountToPay;
                    }
                    else {
                        conPay = true;
                        System.out.println("Continue making payment.");
                    }
                    amountToPay -= pAmount;
                    checkAmount += pAmount;
                    check = new Check(checkName, checkBankID, checkAmount);
                    check.authorized();
                    break;
                case 3:
                    System.out.println("How much do you want to pay by credit?");
                    pAmount = CheckInput.inputDouble();
                    System.out.println("Input the card number:");
                    String creditCardNumber = CheckInput.inputString();
                    System.out.println("Input the card type:");
                    String creditCardType = CheckInput.inputString();
                    System.out.println("Input the expire date(yy-MM):");
                    String creditExpDate = CheckInput.inputString();
                    if (calcPayment(amountToPay, pAmount)) {
                        conPay = false;
                        pAmount = amountToPay;
                    }
                    else {
                        conPay = true;
                        System.out.println("Continue making payment.");
                    }
                    amountToPay -= pAmount;
                    creditAmount += pAmount;
                    credit = new Credit(creditCardNumber, creditCardType, creditExpDate, creditAmount);
                    credit.authorized();
                    break;

            }
        }
        while (conPay);
    }

    boolean calcPayment(double amountToPay, double amount){
        amountToPay -= amount;
        if (amountToPay > 0){
            System.out.println("Remaining: " + amountToPay + " AUD.");
            return false;
        }
        else if(amountToPay == 0){
            System.out.println("Payment completed. Start shipping process.");
            setStatus("Shipping");
            return true;
        }
        else{
            System.out.println("Keep the change: " + -amountToPay + " AUD.");
            System.out.println("Payment completed. Start shipping process.");
            setStatus("Shipping");
            return true;
        }
    }
}


