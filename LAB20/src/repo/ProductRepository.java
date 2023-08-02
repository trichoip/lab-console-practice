package repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Product;

public class ProductRepository extends ArrayList<Product> implements ICrud<String, Product> {

    @Override
    public boolean create(Product newItem) {

        if (this.add(newItem)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {

        for (Product product : this) {
            if (product.getProductID().equals(id)) {
                this.remove(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public Product details(String id) {

        for (Product product : this) {
            if (product.getProductID().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Product> read() {

        return this;
    }

    @Override
    public boolean update(Product editItem) {

        for (Product product : this) {
            if (product.getProductID().equals(editItem.getProductID())) {
                product.setProductID(editItem.getProductID());
                product.setProductName(editItem.getProductName());
                product.setUnit(editItem.getUnit());
                product.setOrigin(editItem.getOrigin());
                product.setPrice(editItem.getPrice());
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Product> readFromFile() {
        this.clear();
        try {
            Scanner sc = new Scanner(new File("products.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                this.add(new Product(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
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
            File file = new File("products.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            for (Product product : this) {
                writer.write(product.getProductID() + "," + product.getProductName() + "," + product.getUnit() + ","
                        + product.getOrigin() + "," + product.getPrice() + "/n");
            }
            writer.close();
            System.out.println("products saved successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
