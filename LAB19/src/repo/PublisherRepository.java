/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.Publisher;

/**
 *
 * @author admin
 */
public class PublisherRepository extends ArrayList<Publisher> {

    public boolean checkContainID(String id) {
        boolean check = false;
        for (Publisher p : this) {
            if (p.getId().equalsIgnoreCase(id)) {
                check = true;
            }
        }
        return check;
    }

    public Publisher getPublisherByID(String id) {

        return this.get(this.indexOf(new Publisher(id)));
    }

    public void saveToFile() throws IOException {
        File file = new File("Publisher.dat");
        FileWriter fw = null;
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        for (Publisher i : this) {
            fw.write(i.getId() + "," + i.getName() + "," + i.getPhoneNumber() + "\n");
        }
        System.out.println("Save Successfully!");
        fw.close();
    }

    public void loadFromFile() throws IOException {
        this.clear();
        Publisher publisher;
        File file = new File("Publisher.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            publisher = new Publisher();
            StringTokenizer stk = new StringTokenizer(line, ",", false);
            while (stk.hasMoreTokens()) {
                publisher.setId(stk.nextToken());
                publisher.setName(stk.nextToken());
                publisher.setPhoneNumber(stk.nextToken());
            }
            this.add(publisher);
        }
        reader.close();
    }

    public void sortByName() {
        this.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
    }

}
