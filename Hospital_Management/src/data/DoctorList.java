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
public class DoctorList extends HashMap<String, Doctor> {

    public boolean checkContainDoctorID(String id) {
        return containsKey(id);
    }

    public void showInput(String id) {
        System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
        System.out.println("|Doctor ID| Name Doctor  |  Sex   |                     Address                       |Department ID| Create Date | Last Update Date |");
        System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
        this.get(id).showInfor();
        System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
    }

    public String inputSexValid(String id) {
        String sex = "";
        boolean check = true;
        if (this.checkContainDoctorID(id) == true) {
            while (check) {
                sex = Utils.updateString("Input gender(male or famale): ", this.get(id).getSex());
                if (Utils.checkValidRegex(sex, Utils.CHECK_GENDER_VALID) == true) {
                    check = false;
                }
            }

        } else {
            while (check) {
                sex = Utils.getString("Input gender(male or famale): ");
                if (Utils.checkValidRegex(sex, Utils.CHECK_GENDER_VALID) == true) {
                    check = false;
                }
            }

        }

        return sex;

    }

    public void writeDoctorToFile() throws IOException {
        File file = new File("doctor.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for (Doctor value : values()) {
            fw.write(value.toString() + "\n");
        }
        fw.close();

    }

    public void readDoctorFromFile() throws IOException {
        Doctor doctor;
        File file = new File("doctor.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            doctor = new Doctor();
            StringTokenizer stk = new StringTokenizer(line, "|", false);
            while (stk.hasMoreElements()) {
                doctor.setDoctorID(stk.nextToken());
                doctor.setName(stk.nextToken());
                doctor.setSex(stk.nextToken());
                doctor.setAddress(stk.nextToken());
                doctor.setDepartmentID(stk.nextToken());
                doctor.setCreateDate(stk.nextToken());
                doctor.setLastUpdateDate(stk.nextToken());
            }
            this.put(doctor.getDoctorID(), doctor);

        }
        reader.close();
    }

}
