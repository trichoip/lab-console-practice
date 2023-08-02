package data;

import tools.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author minh tri
 */
public class StudentList extends HashMap<String, Student> {

    public boolean checkContainID(String id) {
        return this.containsKey(id);
    }

    public String checkGenderValid(String id) {
        boolean check = true;
        String gender = "";
        if (this.checkContainID(id) == true) {
            while (check) {
                gender = Utils.updateString("Input Gender(male or famale): ", this.get(id).getGender());
                if (Utils.checkValidRegex(gender, Utils.CHECK_GENDER_VALID)) {
                    check = false;
                }
            }
        } else {
            while (check) {
                gender = Utils.getString("Input Gender(male or famale): ");
                if (Utils.checkValidRegex(gender, Utils.CHECK_GENDER_VALID)) {
                    check = false;
                }
            }
        }
        return gender;
    }

    public String checkDateValid(String id) {
        boolean check = true;
        String dateOfBirth = "";
        Date date;
        if (this.checkContainID(id) == true) {
            while (check) {
                dateOfBirth = Utils.updateString("Input Birthdate(dd-mm-yyyy): ", this.get(id).getDateOfBirth());
                if (Utils.checkValidRegex(dateOfBirth, Utils.DATE_FORMAT_VALID)) {
                    if (Utils.inputDate(dateOfBirth) != null) {
                        check = false;
                    }
                }
            }
        } else {
            while (check) {
                dateOfBirth = Utils.getString("Input Birthdate(dd-mm-yyyy): ");
                if (Utils.checkValidRegex(dateOfBirth, Utils.DATE_FORMAT_VALID)) {
                    date = Utils.inputDate(dateOfBirth);
                    if (date != null) {
                        dateOfBirth = Utils.getDateToString(date);
                        check = false;
                    }
                }
            }
        }
        return dateOfBirth;
    }

    public String checkEmailValid(String id) {
        boolean check = true;
        String email = "";
        if (this.checkContainID(id) == true) {
            while (check) {
                email = Utils.updateString("Input email(x@xx.x): ", this.get(id).getEmail());
                if (Utils.checkValidRegex(email, Utils.CHECK_EMAIL_VALID)) {
                    check = false;
                }
            }
        } else {
            while (check) {
                email = Utils.getString("Input email(x@xx.x): ");
                if (Utils.checkValidRegex(email, Utils.CHECK_EMAIL_VALID)) {
                    check = false;
                }
            }
        }
        return email;
    }

    public String checkPhoneValid(String id) {
        boolean check = true;
        String phoneNumber = "";
        if (this.checkContainID(id) == true) {
            while (check) {
                phoneNumber = Utils.updateString("Input Phone Number(10-12): ", this.get(id).getPhoneNumber());
                if (Utils.checkValidRegex(phoneNumber, Utils.CHECK_PHONE_VALID)) {
                    check = false;
                }
            }
        } else {
            while (check) {
                phoneNumber = Utils.getString("Input Phone Number(10-12): ");
                if (Utils.checkValidRegex(phoneNumber, Utils.CHECK_PHONE_VALID)) {
                    check = false;
                }
            }
        }
        return phoneNumber;
    }

    public void showIdInput(String id) {
        System.out.println("+----------+------------+----------+-------+-----------+-----------------------+------------+");
        System.out.println("|Student ID| First Name |Last Name |Gender | Birthdate | Email                 |Phone number|");
        System.out.println("+----------+------------+----------+-------+-----------+-----------------------+------------+");
        if (this.checkContainID(id) == true) {

            this.get(id).showInfor();

        } else {

            System.out.println("|                     ID STUDENT : \"" + id + "\" ---->  NOT FOUND!!                          ");
        }
        System.out.println("+----------+------------+----------+-------+-----------+-----------------------+------------+");

    }

    public void showInfor() {
        if (this.size() == 0) {
            System.out.println("List is Empty");
        } else {
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("|                                    STUDENT LIST                                           |");
            System.out.println("+----------+------------+----------+-------+-----------+-----------------------+------------+");
            System.out.println("|Student ID| First Name |Last Name |Gender | Birthdate | Email                 |Phone number|");
            System.out.println("+----------+------------+----------+-------+-----------+-----------------------+------------+");

            for (String i : this.keySet()) {
                this.get(i).showInfor();
            }
            System.out.println("+----------+------------+----------+-------+-----------+-----------------------+------------+");

        }

    }

    public HashMap<String, Student> sort() {
        List<Entry<String, Student>> list = new ArrayList<Entry<String, Student>>(this.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Student>>() {
            @Override
            public int compare(Entry<String, Student> o1, Entry<String, Student> o2) {
                return o1.getValue().getFirstName().compareTo(o2.getValue().getFirstName());
            }
        });
        HashMap<String, Student> hash = new LinkedHashMap<String, Student>();
        for (Entry<String, Student> entry : list) {
            hash.put(entry.getKey(), entry.getValue());
        }
        return hash;
    }

    public void writeStudentToFile() throws IOException {
        File file = new File("student.txt");
        FileWriter fw = null;
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        for (String i : this.keySet()) {
            fw.write(this.get(i).toString() + "\n");

        }
        fw.close();
    }

    public void readStudentFromFile() throws IOException {
        Student student;
        File file = new File("student.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            student = new Student();
            StringTokenizer stk = new StringTokenizer(line, ",", false);
            while (stk.hasMoreTokens()) {
                student.setStudentID(stk.nextToken());
                student.setFirstName(stk.nextToken());
                student.setLastName(stk.nextToken());
                student.setGender(stk.nextToken());
                student.setDateOfBirth(stk.nextToken());
                student.setEmail(stk.nextToken());
                student.setPhoneNumber(stk.nextToken());
            }
            this.put(student.getStudentID(), student);
        }
        reader.close();

    }

}
