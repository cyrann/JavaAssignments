import java.io.*;
import java.util.*;

public class Menu{
    List<Customer> customerList = new ArrayList();
    List<Item> itemList = new ArrayList();
    List<Order> orderList = new ArrayList();
//    int countCustomer;
//    int countItem;
//    int countOrder;

    Menu(){
        readCustomer();
        readItem();
        readOrder();
        System.out.println("\nInformation of customer, item and order loaded.\n");
    }

    void displayReceipt(Order order){
        order.printOrder();
    }

    void displayOrderSummary(){
        for(Order order: orderList){
            order.summarizeOrder();
        }
    }

    void displayMenu(){
        System.out.println("-----Fruit Juice Management System-----");
        System.out.println("1.Register the items.\n" +
                "2.Register the customers.\n" +
                "3.Make an order.\n" +
                "4.Display order summary.\n" +
                "5.Export the customer information.\n" +
                "6.Export the item information.\n" +
                "7.Export the order information.\n" +
                "8.Quit the system.\n");
        takeCommand();
    }

    void takeCommand(){
        System.out.println("Input the number of your command:");
        int command = CheckInput.inputInt(1,8);
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
                exportCustomer(customerList);
                displayMenu();
                break;
            case 6:
                exportItem(itemList);
                displayMenu();
                break;
            case 7:
                exportOrder(orderList);
                displayMenu();
                break;
            case 8:
                break;
        }
    }

    void makeOrder(){
        int id;
        List oList = new ArrayList();
        String conOrder;

        Order.setCustomerList(customerList);
        OrderDetail.setList(itemList);

        do {
            System.out.println("Input the customer ID from the index:");
            System.out.printf("%-5s%-15s\n", "ID", "Name");
            for (Customer c : customerList){
                System.out.printf("%-5s%-15s\n", c.getId(), c.getName());
            }
            id = CheckInput.inputInt();

            Order order = new Order(new Date(), id);
            order.addOrderDetail();
            order.addPayment();

            oList.add(order);
            orderList.add(order);

            displayReceipt(order);

            System.out.println("Make the next order?(y/n)");
            conOrder = CheckInput.inputString("n", "y");

        }
        while (conOrder.equals("y"));

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
        }
        System.out.println("Order information exported.");
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void readOrder(){
        String fileName = "order.txt";
        File file = new File(fileName);
        if (file.exists()){
            ObjectInputStream input = null;
            try {
                input = new ObjectInputStream(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Order o = null;

            boolean flag = true;
            while (flag){
                try {
                    o = (Order) input.readObject();
                    orderList.add(o);
                } catch (EOFException e) {
                    flag = false;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    void registerCustomer(){

        int id;
        String name;
        String address;
        List cList = new ArrayList();

        String conReg;
        boolean repeat = false;
        do {
            Customer customer = new Customer();

            System.out.println("Input the customer id:");
            id = CheckInput.inputInt();
            do {
                for (int i = 0; i < customerList.size(); i++) {
                    if (id == customerList.get(i).getId()) {
                        repeat = true;
                        System.out.println("ID already existed. Input the customer ID again:");
                        id = CheckInput.inputInt();
                        break;
                    }
                    else repeat = false;
                }
            }
            while (repeat);

            customer.setId(id);

            System.out.println("Input the customer name:");
            name = CheckInput.inputString();
            customer.setName(name);

            System.out.println("Input the customer address:");
            address = CheckInput.inputString();
            customer.setAddress(address);

            cList.add(customer);
            customerList.add(customer);

            System.out.println("Continue to register another customer?(y/n)");
            conReg = CheckInput.inputString("n", "y");

        }
        while (conReg.equals("y"));

//        exportCustomer(cList);
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
        }
        System.out.println("Customer information exported.");
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void readCustomer(){
        String fileName = "customer.txt";
        File file = new File(fileName);
        if (file.exists()){  //read the file if it exists.
            ObjectInputStream input = null;
            try {
                input = new ObjectInputStream(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Customer c = null;

            boolean flag = true;
            while(flag){
                try {
                    c = (Customer) input.readObject();
                    customerList.add(c);
                } catch (EOFException e) {
                    flag = false;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void registerItem(){

        List iList = new ArrayList();
        int no;
        double sw;
        String desc;
        double poq;
        String conReg;
        boolean repeat = false;
        do {
            Item item = new Item();


            System.out.println("Input the juice No.:");
            no = CheckInput.inputInt();
            do {
                for (int i = 0; i < itemList.size(); i++) {
                    if (no == itemList.get(i).getJuiceNo()) {
                        repeat = true;
                        System.out.println("No. already existed. Input the juice No. again:");
                        no = CheckInput.inputInt();
                        break;
                    }
                    else repeat = false;
                }
            }
            while (repeat);
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
            itemList.add(item);

            System.out.println("Continue to register another juice?(y/n)");
            conReg = CheckInput.inputString("n", "y");


        }
        while (conReg.equals("y"));
//        exportItem(iList);
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
        }
        System.out.println("Item information exported.");
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readItem(){
        String fileName = "item.txt";
        File file = new File(fileName);
        if (file.exists()){  //read the file if it exists.
            ObjectInputStream input = null;
            try {
                input = new ObjectInputStream(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Item item = null;

            boolean flag = true;
            while (flag){
                try {
                    item = (Item) input.readObject();
                    itemList.add(item);
                } catch (EOFException e) {
                    flag = false;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
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
