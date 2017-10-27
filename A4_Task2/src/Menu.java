import java.io.*;
import java.util.*;

public class Menu{
    List<Customer> customerList = new ArrayList();
    List<Item> itemList = new ArrayList();
    List<Order> orderList = new ArrayList();
    int countCustomer;
    int countItem;
    int countOrder;

    void displayReceipt(Order order){
        order.printOrder();
    }

    void displayOrderSummary(){
        readOrder();
        for(Order order: orderList){
            order.summarizeOrder();
        }
    }

    void displayMenu(){
        System.out.println("Fruit Juice Management System");
        System.out.println("1.Register the items.\n" +
                "2.Register the customers.\n" +
                "3.Make an order.\n" +
                "4.Display order summary.\n" +
                "5.Quit");
        takeCommand();
    }

    void takeCommand(){
        System.out.println("Input the number of your command:");
        int command = CheckInput.inputInt(1,5);
        switch (command){
            case 1:
                registerItem();
                displayMenu();
                break;
            case 2:
                registerCustomer();
                displayMenu();
                break;
            case 3:
                makeOrder();
                displayMenu();
                break;
            case 4:
                displayOrderSummary();
                displayMenu();
                break;
            case 5:
                break;
        }
    }

    void makeOrder(){
        int id;
        List oList = new ArrayList();
        String conOrder;

        readCustomer();
        Order.setCustomerList(customerList);
        readItem();
        OrderDetail.setList(itemList);

        do {
            System.out.println("Input the customer id:");
            id = CheckInput.inputInt();

            Order order = new Order(new Date(), id);
            order.addOrderDetail();
            order.addPayment();

            oList.add(order);

            displayReceipt(order);

            System.out.println("Make the next order?(y/n)");
            conOrder = CheckInput.inputString("n", "y");

        }
        while (conOrder.equals("y"));

        exportOrder(oList);

    }

    void exportOrder(List orderList){
        String fileName = "order.txt";
        ObjectOutputStream output = generateOutput(fileName);
        ListIterator<Order> it = orderList.listIterator();
        while (it.hasNext()){
            Order o = it.next();
            try {
                output.writeObject(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
            countOrder += 1;
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void readOrder(){
        String fileName = "order.txt";
        File file = new File(fileName);
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Order o = null;

        for( int i = 0; i < countOrder; i ++){
            try {
                o = (Order) input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            orderList.add(o);
        }

    }

    void registerCustomer(){

        int id;
        String name;
        String address;
        List cList = new ArrayList();

        String conReg;
        do {
            Customer customer = new Customer();

            System.out.println("Input the customer id:");
            id = CheckInput.inputInt();
            customer.setId(id);

            System.out.println("Input the customer name:");
            name = CheckInput.inputString();
            customer.setName(name);

            System.out.println("Input the customer address:");
            address = CheckInput.inputString();
            customer.setAddress(address);

            cList.add(customer);

            System.out.println("Continue to register another customer?(y/n)");
            conReg = CheckInput.inputString("n", "y");

        }
        while (conReg.equals("y"));

        exportCustomer(cList);
    }

    void exportCustomer(List customerList){
        String fileName = "customer.txt";
        ObjectOutputStream output = generateOutput(fileName);

        ListIterator<Customer> it = customerList.listIterator();
        while (it.hasNext()){
            Customer c = it.next();
            try {
                output.writeObject(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
            countCustomer += 1;
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void readCustomer(){
        String fileName = "customer.txt";
        File file = new File(fileName);
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Customer c = null;

        for( int i = 0; i < countCustomer; i ++){
            try {
                c = (Customer) input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            customerList.add(c);
        }

    }

    void registerItem(){

        List iList = new ArrayList();
        int no;
        double sw;
        String desc;
        double poq;
        String conReg;
        do {
            Item item = new Item();


            System.out.println("Input the juice No.:");
            no = CheckInput.inputInt();
            item.setJuiceNo(no);

            System.out.println("Input the juice name:");
            desc = CheckInput.inputString();
            item.setDescription(desc);

            System.out.println("Input the shipping weight of the juice:");
            sw = CheckInput.inputDouble();
            item.setShippingWeight(sw);

            System.out.println("Input the price for quantity:");
            poq = CheckInput.inputDouble();
            item.setPriceForQuantity(poq);

            iList.add(item);

            System.out.println("Continue to register another juice?(y/n)");
            conReg = CheckInput.inputString("n", "y");


        }
        while (conReg.equals("y"));
        exportItem(iList);
    }

    void exportItem(List itemList){
        String fileName = "item.txt";
        ObjectOutputStream output = generateOutput(fileName);
        ListIterator<Item> it = itemList.listIterator();
        while (it.hasNext()){
            Item i = it.next();
            try {
                output.writeObject(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            countItem += 1;
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readItem(){
        String fileName = "item.txt";
        File file = new File(fileName);
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Item item = null;

        for( int i = 0; i < countItem; i ++){
            try {
                item = (Item) input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            itemList.add(item);
        }

    }

    ObjectOutputStream generateOutput(String fileName){
        File file = new File(fileName);
        ObjectOutputStream output = null;

        if (file.length() < 1){
            try {
                output = new ObjectOutputStream(new FileOutputStream(file, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                output = new MyObjectOutputStream(new FileOutputStream(file, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }


}
