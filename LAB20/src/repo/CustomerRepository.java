package repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Customer;

public class CustomerRepository extends ArrayList<Customer> implements ICrud<String, Customer> {

    @Override
    public boolean create(Customer newItem) {
        if (this.add(newItem)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (Customer customer : this) {
            if (customer.getCustomerID().equalsIgnoreCase(id)) {
                this.remove(customer);
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer details(String id) {
        for (Customer customer : this) {
            if (customer.getCustomerID().equalsIgnoreCase(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Customer> read() {
        return this;
    }

    @Override
    public boolean update(Customer editItem) {

        for (Customer customer : this) {
            if (customer.getCustomerID().equals(editItem.getCustomerID())) {
                customer.setCustomerName(editItem.getCustomerName());
                customer.setCustomerAddress(editItem.getCustomerAddress());
                customer.setCustomerPhone(editItem.getCustomerPhone());
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Customer> readFromFile() {
        this.clear();
        try {
            Scanner sc = new Scanner(new File("customers.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                this.add(new Customer(parts[0], parts[1], parts[2], parts[3]));
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
            File file = new File("customers.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            for (Customer customer : this) {
                writer.write(customer.getCustomerID() + "," + customer.getCustomerName() + ","
                        + customer.getCustomerAddress()
                        + "," + customer.getCustomerPhone() + "\n");
            }
            writer.close();
            System.out.println("customer saved successfully");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("customer saved failed");
        }

    }

}
