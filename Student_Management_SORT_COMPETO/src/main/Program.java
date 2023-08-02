package main;

import gui.Menu;
import data.StudentManagement;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author minh tri
 */
public class Program {

    public static void main(String[] args) throws IOException {

        StudentManagement stumg = new StudentManagement();

        Menu menu = new Menu();
        menu.addItem("╔═STUDENT MANAGER MENU═╗");
        menu.addItem("   1.Add new Student");
        menu.addItem("   2.Update Student");
        menu.addItem("   3.Add new Subject");
        menu.addItem("   4.Update Subject");
        menu.addItem("   5.Enter Grade");
        menu.addItem("   6.Student Grade Report");
        menu.addItem("   7.Subject Grade Report");
        menu.addItem("  Others.out");

        int choice;
        boolean check = true;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    stumg.addStudent();
                    break;
                case 2:
                    stumg.updateStudent();
                    break;
                case 3:
                    stumg.addSubject();
                    break;
                case 4:
                    stumg.updateSubject();
                    break;
                case 5:
                    stumg.enterGrade();
                    break;
                case 6:
                    stumg.stundentGradeReport();
                    break;
                case 7:
                    stumg.subjectGradeReport();
                    break;
                default:
                    check = false;

            }

        } while (check);

    }

}
