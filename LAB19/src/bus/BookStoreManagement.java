package bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Book;
import model.Publisher;
import repo.BookRepository;
import repo.PublisherRepository;
import tools.Utils;

public class BookStoreManagement {

    BookRepository bookList = new BookRepository();
    PublisherRepository publisherList = new PublisherRepository();

    public void createPublisher() {
        String id = Utils.getString("Input Publisher ID (Pxxxxx): ").toUpperCase();
        if (!Utils.checkValidRegex(id, Utils.REGEX_PUBLISH_ID)) {
            System.out.println("id invalid!");
            return;
        }
        if (publisherList.checkContainID(id)) {
            System.out.println("Duplicate ID!");
            return;
        }

        String name = Utils.getString("Input Publisher name(5,30): ");
        if (name.length() < 5 || name.length() > 30) {
            System.out.println("name invalid!");
            return;
        }

        String phoneNumber = Utils.getString("Input Publisher phone number(10,12): ");
        if (!Utils.checkValidRegex(phoneNumber, Utils.REGEX_PHONE)) {
            System.out.println("phone number invalid!");
            return;
        }
        publisherList.add(new Publisher(id, name, phoneNumber));
        System.out.println("Create Successfully!");
    }

    public void deletePublisher() {
        String id = Utils.getString("Input Publisher ID: ").toUpperCase();
        if (!Utils.checkValidRegex(id, Utils.REGEX_PUBLISH_ID)) {
            System.out.println("id invalid!");
            return;
        }
        if (!publisherList.checkContainID(id)) {
            System.out.println("Publisher ID does not exist!");
            return;
        }
        publisherList.removeIf(p -> p.getId().equalsIgnoreCase(id));
        System.out.println("Delete Successfully!");

    }

    public void saveToFilePublish() throws IOException {
        publisherList.saveToFile();
    }

    public void displayPublish() throws IOException {
        publisherList.loadFromFile();
        publisherList.sortByName();
        if (publisherList.isEmpty()) {
            System.out.println("Empty!");
            return;
        }
     
        publisherList.forEach(System.out::println);
    }

    public void createBook() {
        String id = Utils.getString("Input Book ID (Bxxxxx): ").toUpperCase();
        if (!Utils.checkValidRegex(id, Utils.REGEX_BOOK_ID)) {
            System.out.println("id invalid!");
            return;
        }
        if (bookList.checkContainID(id)) {
            System.out.println("Duplicate ID!");
            return;
        }

        String name = Utils.getString("Input Book name(5,30): ");
        if (name.length() < 5 || name.length() > 30) {
            System.out.println("name invalid!");
            return;
        }

        double price = Utils.getDouble("Input Book price: ", 0, 100000000);

        int quantity = Utils.getInt("Input Book quantity: ", 0, 100000000);

        String publisherId = Utils.getString("Input Publisher ID (Pxxxxx): ").toUpperCase();
        if (!publisherList.checkContainID(publisherId)) {
            System.out.println("Publishers Id is not found");
            return;
        }

        String status = Utils.getString("Input Book status (Available, Not Available): ");
        if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Not Available")) {
            System.out.println("status invalid!");
            return;
        }

        bookList.add(new Book(id, name, price, quantity, publisherId, status));
        System.out.println("Create Successfully!");
    }

    public void deleteBook() {
        String id = Utils.getString("Input Book ID: ").toUpperCase();
        if (!Utils.checkValidRegex(id, Utils.REGEX_BOOK_ID)) {
            System.out.println("id invalid!");
            return;
        }
        if (!bookList.checkContainID(id)) {
            System.out.println("Books Name does not exist");
            return;
        }
        bookList.removeIf(p -> p.getId().equalsIgnoreCase(id));
        System.out.println("Delete Successfully!");
    }

    public void saveToFileBook() throws IOException {
        bookList.saveToFile();
    }

    public void displayBook() throws IOException {
        bookList.loadFromFile();
        publisherList.loadFromFile();
        bookList.sortByQuantity();

        bookList.forEach(b -> {
            System.out.println(
                    b.getId() + ", " + b.getName() + ", " + b.getPrice() + ", " + b.getQuantity() + ", " + b.getQuantity() * b.getPrice() + ", "
                    + publisherList.getPublisherByID(b.getPublisherId()).getName() + ", " + b.getStatus());
        });
    }

    public void editBook() {
        String id = Utils.getString("Input Book ID: ").toUpperCase();
        if (!Utils.checkValidRegex(id, Utils.REGEX_BOOK_ID)) {
            System.out.println("id invalid!");
            return;
        }
        if (!bookList.checkContainID(id)) {
            System.out.println("Books Name does not exist");
            return;
        }
        int index = bookList.findBookById(id);
        String name = Utils.updateString("Input Book name(5,30): ", bookList.get(index).getName());
        if (name.length() < 5 || name.length() > 30) {
            System.out.println("name invalid!");
            return;
        }

        double price = Utils.updateDouble("Input Book price: ", 0, 1000000000, bookList.get(index).getPrice());
        if (price < 0) {
            System.out.println("price invalid!");
            return;
        }

        int quantity = Utils.updateInt("Input Book quantity: ", 0, 1000000000, bookList.get(index).getQuantity());
        if (quantity < 0) {
            System.out.println("quantity invalid!");
            return;
        }

        String publisherId = Utils.updateString("Input Publisher ID (Pxxxxx): ", bookList.get(index).getPublisherId())
                .toUpperCase();
        if (!publisherList.checkContainID(publisherId)) {
            System.out.println("Publishers Id is not found");
            return;
        }

        String status = Utils.updateString("Input Book status (Available, Not Available): ",
                bookList.get(index).getStatus());
        if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Not Available")) {
            System.out.println("status invalid!");
            return;
        }

        bookList.get(index).setName(name);
        bookList.get(index).setPrice(price);
        bookList.get(index).setQuantity(quantity);
        bookList.get(index).setPublisherId(publisherId);
        bookList.get(index).setStatus(status);
        System.out.println("Edit Successfully!");
    }

    public void searchBook() {
        String search = Utils.getString("Input search: ");
        List<Book> list = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getName().contains(search) || b.getPublisherId().contains(search)) {
                list.add(b);
            }
        }
        if (list.isEmpty()) {
            System.out.println("Have no any Book");
            return;
        } else {
            list.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
            for (Book book : list) {
                System.out.println(book);
            }
        }

    }

}
