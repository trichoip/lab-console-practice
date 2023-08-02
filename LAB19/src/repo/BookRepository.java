package repo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.Book;
import tools.Utils;

public class BookRepository extends ArrayList<Book> {

    public boolean checkContainID(String id) {
        boolean check = false;
        for (Book b : this) {
            if (b.getId().equalsIgnoreCase(id)) {
                check = true;
            }
        }
        return check;
    }

    public int findBookById(String id) {
        return this.indexOf(new Book(id));
    }

    public void saveToFile() throws IOException {
        File file = new File("Book.dat");
        FileWriter fw = null;
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        for (Book i : this) {
            fw.write(i.getId() + "," + i.getName() + "," + i.getPrice() + "," + i.getQuantity() + ","
                    + i.getPublisherId() + ","
                    + i.getStatus() + "\n");
        }
        System.out.println("Save Successfully!");
        fw.close();
    }

    public void loadFromFile() throws IOException {
        this.clear();
        Book book;
        File file = new File("Book.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            book = new Book();
            StringTokenizer stk = new StringTokenizer(line, ",", false);
            while (stk.hasMoreTokens()) {
                book.setId(stk.nextToken());
                book.setName(stk.nextToken());
                book.setPrice(Double.parseDouble(stk.nextToken()));
                book.setQuantity(Integer.parseInt(stk.nextToken()));
                book.setPublisherId(stk.nextToken());
                book.setStatus(stk.nextToken());
            }
            this.add(book);
        }
        reader.close();
    }

    public void sortByQuantity() {
        this.sort((p1, p2) -> {
            if (p1.getQuantity() > p2.getQuantity()) {
                return -1;
            } else if (p1.getQuantity() < p2.getQuantity()) {
                return 1;
            } else {
                return p1.getPrice() > p2.getPrice() ? 1 : -1;
            }

        });
    }
    
    
}
