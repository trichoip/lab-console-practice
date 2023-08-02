/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;

import bus.BookStoreManagement;
import gui.Menu;
import repo.PublisherRepository;
import tools.Utils;

/**
 *
 * @author admin
 */
public class Program {

    public static void main(String[] args) throws IOException {
        BookStoreManagement bookStoreManagement = new BookStoreManagement();

        Menu menu = new Menu();
        menu.addItem("1. Publishers management");
        menu.addItem("2. Books management");
        menu.addItem("Others- Quit.");

        Menu menuPublish = new Menu();
        menuPublish.add("1.1. Create a Publisher");
        menuPublish.add("1.2. Delete the Publisher");
        menuPublish.add("1.3. Save the Publishers list to file");
        menuPublish.add("1.4. Print the Publisher list from the file.");
        menuPublish.add("Others- Quit.");

        Menu menuBook = new Menu();
        menuBook.add("2.1. Create a Book");
        menuBook.add("2.2. Search the Book");
        menuBook.add("2.3. Update a Book");
        menuBook.add("2.4. Delete the Book");
        menuBook.add("2.5. Save the Books list to file.");
        menuBook.add("2.6. Print the Books list from the file.");
        menuBook.add("Others- Quit.");

        int choice;
        boolean check = true;
        while (check) {
            Boolean checkCase = true;
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    while (checkCase) {
                        menuPublish.showMenu();
                        choice = menuPublish.getChoice();
                        switch (choice) {
                            case 1:
                                while (true) {
                                    bookStoreManagement.createPublisher();
                                    Boolean confirm = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                                    if (!confirm) {
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                while (true) {
                                    bookStoreManagement.deletePublisher();
                                    Boolean confirm = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                                    if (!confirm) {
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                bookStoreManagement.saveToFilePublish();
                                break;
                            case 4:
                                bookStoreManagement.displayPublish();
                                break;

                            default:
                                checkCase = false;
                        }
                    }
                    break;

                case 2:
                    while (checkCase) {
                        menuBook.showMenu();
                        choice = menuBook.getChoice();
                        switch (choice) {
                            case 1:

                                while (true) {
                                    bookStoreManagement.createBook();
                                    Boolean confirm = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                                    if (!confirm) {
                                        break;
                                    }
                                }
                                break;
                            case 2:

                                while (true) {
                                    bookStoreManagement.searchBook();
                                    Boolean confirm = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                                    if (!confirm) {
                                        break;
                                    }
                                }
                                break;
                            case 3:

                                while (true) {
                                    bookStoreManagement.editBook();
                                    Boolean confirm = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                                    if (!confirm) {
                                        break;
                                    }
                                }
                                break;
                            case 4:

                                while (true) {
                                    bookStoreManagement.deleteBook();
                                    Boolean confirm = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                                    if (!confirm) {
                                        break;
                                    }
                                }
                                break;
                            case 5:
                                bookStoreManagement.saveToFileBook();
                                break;
                            case 6:
                                bookStoreManagement.displayBook();
                                break;

                            default:
                                checkCase = false;
                        }
                    }
                    break;

                default:
                    check = false;
            }
        }

    }

}
