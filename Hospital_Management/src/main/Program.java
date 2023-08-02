package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gui.Menu;
import data.HospitalManagement;
import java.io.IOException;

/**
 *
 * @author minh tri
 */
public class Program {

    public static void main(String[] args) throws IOException {

        HospitalManagement hopmg = new HospitalManagement();

        Menu mainMenu = new Menu();
        mainMenu.addItem("\n╔===HOSPITAL MANAGEMENT===╗");
        mainMenu.addItem("║ 1.  Show information    ║");
        mainMenu.addItem("║ 2.  Add new             ║");
        mainMenu.addItem("║ 3.  Update information  ║");
        mainMenu.addItem("║ 4.  Delete              ║");
        mainMenu.addItem("║ 5.  Search information  ║");
        mainMenu.addItem("║ 6.  Store data to file  ║");
        mainMenu.addItem("║ Others.  Quit           ║");
        mainMenu.addItem("╚=========================╝");

        Menu menuShowInfor = new Menu();
        menuShowInfor.addItem("════SHOW INFORMATION════");
        menuShowInfor.addItem("1. Show doctor list");
        menuShowInfor.addItem("2. Show department list");

        Menu menuAdd = new Menu();
        menuAdd.addItem("═════════ADD═══════");
        menuAdd.addItem("1. Add new doctor");
        menuAdd.addItem("2. Add new department");

        Menu menuUpdate = new Menu();
        menuUpdate.addItem("══════UPDATE══════");
        menuUpdate.addItem("1. Update doctor");
        menuUpdate.addItem("2. Update department");

        Menu menuDelete = new Menu();
        menuDelete.addItem("══════DELETE══════");
        menuDelete.addItem("1. Delete doctor");
        menuDelete.addItem("2. Delete department");

        Menu menuSearchInfor = new Menu();
        menuSearchInfor.addItem("══════SEARCH════════");
        menuSearchInfor.addItem("1. Search doctor by name");
        menuSearchInfor.addItem("2. Search department by ID");

        boolean check = true;
        int choice;

        while (check) {
            mainMenu.showMenu();
            choice = mainMenu.getChoice();
            switch (choice) {

                case 1:
                    menuShowInfor.showMenu();
                    choice = menuShowInfor.getChoice();
                    switch (choice) {
                        case 1:
                            hopmg.showAllDataDoctor();
                            break;
                        case 2:
                            hopmg.showAllDataDepartment();
                            break;
                    }
                    break;

                case 2:
                    menuAdd.showMenu();
                    choice = menuAdd.getChoice();
                    switch (choice) {
                        case 1:
                            hopmg.addDoctor();
                            break;
                        case 2:
                            hopmg.addDepartment();
                            break;
                    }
                    break;

                case 3:
                    menuUpdate.showMenu();
                    choice = menuUpdate.getChoice();
                    switch (choice) {
                        case 1:
                            hopmg.updateDoctor();
                            break;
                        case 2:
                            hopmg.updateDepartment();
                            break;
                    }
                    break;

                case 4:
                    menuDelete.showMenu();
                    choice = menuDelete.getChoice();
                    switch (choice) {
                        case 1:
                            hopmg.deleteDoctor();
                            break;
                        case 2:
                            hopmg.deleteDepartment();
                            break;
                    }
                    break;

                case 5:
                    menuSearchInfor.showMenu();
                    choice = menuSearchInfor.getChoice();
                    switch (choice) {
                        case 1:
                            hopmg.searchDoctorByName();
                            break;
                        case 2:
                            hopmg.searchDepartmentByID();
                            break;
                    }
                    break;

                case 6:
                    hopmg.storeAllDateToFile();
                    break;
                default:

                    check = false;

            }
        }

    }
}
