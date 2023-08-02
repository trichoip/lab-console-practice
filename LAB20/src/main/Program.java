/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import bus.OrderManagement;
import gui.Menu;

/**
 *
 * @author admin
 */
public class Program {

    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagement();

        Menu menu = new Menu();
        menu.addItem("1. List all Products");
        menu.addItem("2. List all Customers");
        menu.addItem("3. Search a Customer based on his/her ID");
        menu.addItem("4. Add a Customer");
        menu.addItem("5. Update a Customer");
        menu.addItem("6. Save Customers to the file, named customers.txt");
        menu.addItem("7. List all Orders in ascending order of Customer name");
        menu.addItem("8. List all pending Orders");
        menu.addItem("9. Add an Order");
        menu.addItem("10. Update an Order");
        menu.addItem("11. Save Orders to file, named orders.txt");
        menu.addItem("Others- Quit");

        Menu menuUpdate = new Menu();
        menuUpdate.addItem("1. Update an Order based on its ID");
        menuUpdate.addItem("2. Delete an Order based on its ID");
        menuUpdate.addItem("Others- Quit");

        int choice;
        boolean check = true;
        while (check) {
            Boolean checkCaseUpdate = true;
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    while (checkCaseUpdate) {
                        menuUpdate.showMenu();
                        choice = menuUpdate.getChoice();
                        switch (choice) {

                            case 1:

                                break;
                            case 2:

                                break;

                            default:
                                checkCaseUpdate = false;
                        }
                    }
                    break;
                case 11:

                    break;

                default:
                    check = false;
            }
        }
    }
}
