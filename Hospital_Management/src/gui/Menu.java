package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import tools.Utils;

/**
 *
 * @author minh tri
 */
public class Menu extends ArrayList<String> {

    public void addItem(String s) {
        this.add(s);
    }

    public void showMenu() {
        for (String o : this) {
            System.out.println(o);
        }
    }

    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        result = Utils.confirmYesNo(welcome);
        return result;
    }

    public int getChoice() {
        return Utils.getInt("Input your choice: ", 1, 100);
    }
}
