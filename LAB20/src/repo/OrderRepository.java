package repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Order;

public class OrderRepository extends ArrayList<Order> implements ICrud<String, Order> {

    @Override
    public boolean create(Order newItem) {
        if (this.add(newItem)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {

        for (Order order : this) {
            if (order.getOrderID().equalsIgnoreCase(id)) {
                this.remove(order);
                return true;
            }
        }
        return false;
    }

    @Override
    public Order details(String id) {

        for (Order order : this) {
            if (order.getOrderID().equals(id)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Order> read() {

        return this;
    }

    @Override
    public boolean update(Order editItem) {

        for (Order order : this) {
            if (order.getOrderID().equals(editItem.getOrderID())) {
                order.setOrderDate(editItem.getOrderDate());
                order.setOrderQuantity(editItem.getOrderQuantity());
                order.setStatus(editItem.isStatus());
                order.setCustomerID(editItem.getCustomerID());
                order.setProductID(editItem.getProductID());
                return true;
            }
        }
        return false;

    }

    @Override
    public ArrayList<Order> readFromFile() {
        this.clear();
        try {
            Scanner sc = new Scanner(new File("orders.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = line.split(",");
                this.add(new Order(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), fields[4],
                        Boolean.parseBoolean(fields[5])));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void saveToFile() {

        try {
            File file = new File("orders.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            for (Order order : this) {
                writer.write(order.getOrderID() + "," + order.getCustomerID() + "," + order.getProductID() + ","
                        + order.getOrderQuantity() + "," + order.getOrderDate() + "," + order.isStatus() + "/n");
            }
            writer.close();
            System.out.println("orders saved successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
