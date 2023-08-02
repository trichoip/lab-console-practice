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
public class SubjectList extends HashMap<String, Subject> {

    public boolean checkContainID(String id) {
        return this.containsKey(id);
    }

    public String inputName(String id) {
        boolean check = true;
        String subjectName = "";
        if (this.checkContainID(id) == true) {
            while (check) {
                subjectName = Utils.updateString("\nInput subject name: ", this.get(id).getSubjectName());
                if (this.checkDuplicateName(subjectName) == true) {
                    check = false;
                } else {
                    if (this.get(id).getSubjectName().equalsIgnoreCase(subjectName)) {
                        check = false;
                    } else {
                        System.out.println("Duplicate name!!!");
                    }
                }
            }
        } else {
            while (check) {
                subjectName = Utils.getString("Input subject name: ");
                if (this.checkDuplicateName(subjectName) == true) {
                    check = false;
                } else {
                    System.out.println("Duplicate name!!!");
                }
            }
        }
        return subjectName;
    }

    public boolean checkDuplicateName(String subname) {
        boolean check = true;
        for (String i : this.keySet()) {
            if (this.get(i).getSubjectName().equalsIgnoreCase(subname)) {
                check = false;
            }
        }
        return check;
    }

    public void showIdInput(String id) {
        System.out.println("+----------+----------------------------+-------+");
        System.out.println("|Subject ID|  Subject Name              |Credit |");
        System.out.println("+----------+----------------------------+-------+");
        if (this.checkContainID(id) == true) {

            this.get(id).showInfor();

        } else {

            System.out.println("| ID SUBJECT : \"" + id + "\" ---->  NOT FOUND!! ");
        }

        System.out.println("+----------+----------------------------+-------+");
    }

    public void showInfor() {

        if (this.size() == 0) {
            System.out.println("List is Empty");
        } else {
            System.out.println("+-----------------------------------------------+");
            System.out.println("|                 SUBJECT LIST                  |");
            System.out.println("+----------+----------------------------+-------+");
            System.out.println("|Subject ID|  Subject Name              |Credit |");
            System.out.println("+----------+----------------------------+-------+");

            for (String i : this.keySet()) {
                this.get(i).showInfor();
            }
            System.out.println("+----------+----------------------------+-------+");
        }
    }

    public HashMap<String, Subject> sort() {
        List<Entry<String, Subject>> subject = new ArrayList<Entry<String, Subject>>(this.entrySet());
        Collections.sort(subject, new Comparator<Entry<String, Subject>>() {
            @Override
            public int compare(Entry<String, Subject> o1, Entry<String, Subject> o2) {
                return o1.getValue().getSubjectName().compareTo(o2.getValue().getSubjectName());
            }
        });

        HashMap<String, Subject> hash = new LinkedHashMap<String, Subject>();
        for (Entry<String, Subject> entry : subject) {
            hash.put(entry.getKey(), entry.getValue());
        }
        return hash;
    }

    public void writeSubjectToFile() throws IOException {
        File file = new File("subject.txt");
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

    public void readSubjectFromFile() throws IOException {
        Subject subject;
        File file = new File("subject.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            subject = new Subject();
            StringTokenizer stk = new StringTokenizer(line, ",", false);
            while (stk.hasMoreTokens()) {
                subject.setSubjectID(stk.nextToken());
                subject.setSubjectName(stk.nextToken());
                subject.setCredit(Integer.parseInt(stk.nextToken()));
            }
            this.put(subject.getSubjectID(), subject);
        }
        reader.close();
    }
}
