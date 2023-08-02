/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import tools.Utils;

/**
 *
 * @author minh tri
 */
public class DepartmentList extends HashMap<String, Department> {

    public boolean checkContainDeptID(String id) {
        return containsKey(id);
    }

    public void showInput(String id) {
        System.out.println("+-------------+---------------------+-------------+------------------+");
        System.out.println("|Department ID|        Name         | Create Date | Last Update Date |");
        System.out.println("+-------------+---------------------+-------------+------------------+");
        this.get(id).showInfor();
        System.out.println("+-------------+---------------------+-------------+------------------+");
    }

    public String inputDeptValid() {
        String dept = "";
        boolean check = true;
        while (check) {
            dept = Utils.getString("Input department ID: ").toUpperCase();
            if (this.checkContainDeptID(dept) == true) {
                check = false;
            } else {
                System.out.println("Department does not exist!!!");
                System.out.println("Input again!!!");
            }
        }
        return dept;
    }

    public void writeDepartmentToFile() throws IOException {
        File file = new File("department.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for (Department value : values()) {
            fw.write(value.toString() + "\n");
        }
        fw.close();
    }

    public void readDepartmentFromFile() throws IOException {
        Department dep;
        File file = new File("department.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            dep = new Department();
            StringTokenizer stk = new StringTokenizer(line, "|", false);
            while (stk.hasMoreTokens()) {
                dep.setDepartmentID(stk.nextToken());
                dep.setName(stk.nextToken());
                dep.setCreateDate(stk.nextToken());
                dep.setLastUpdateDate(stk.nextToken());

            }
            this.put(dep.getDepartmentID(), dep);

        }
        reader.close();
    }

}
