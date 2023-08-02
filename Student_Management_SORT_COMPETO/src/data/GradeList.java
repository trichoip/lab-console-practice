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
public class GradeList extends ArrayList<Grade> {

    public void inputGrade(String idStudent, String idSubject) throws IOException {
        if (this.checkGraded(idStudent, idSubject) == true) {
            double labs = Utils.getDouble("Input labs(0-10): ", 0, 10);
            double progressTest = Utils.getDouble("Input progress test(0-10): ", 0, 10);
            double finalTest = Utils.getDouble("Input final test(0-10): ", 0, 10);
            this.add(new Grade(idStudent, idSubject, labs, progressTest, finalTest));
            this.writeGradeToFile();
            this.showInputGraded(idStudent, idSubject);
            System.out.println("Add grade successfully!");
        } else {
            this.showInputGraded(idStudent, idSubject);
            if (Utils.confirmYesNo("you want to overwrite it or not(Y/N)? ")) {
                for (Grade gra : this) {
                    if (gra.getStudentID().equalsIgnoreCase(idStudent) == true && gra.getSubjectID().equalsIgnoreCase(idSubject) == true) {
                        gra.setLabsPoint(Utils.updateDouble("Input labs(0-10): ", 0, 10, gra.getLabsPoint()));
                        gra.setProgressTestsPoint(Utils.updateDouble("Input progress test(0-10): ", 0, 10, gra.getProgressTestsPoint()));
                        gra.setFinalExamPoint(Utils.updateDouble("Input final test(0-10): ", 0, 10, gra.getFinalExamPoint()));
                        this.writeGradeToFile();
                        this.showInputGraded(idStudent, idSubject);
                        System.out.println("Overwrite grade successfully!");
                        break;
                    }
                }
            } else {
                System.out.println("Add grade failed!!!");
            }
        }
    }

    public boolean checkGraded(String idStu, String idSub) {
        boolean check = true;
        for (Grade gra : this) {
            if (gra.getStudentID().equalsIgnoreCase(idStu) == true && gra.getSubjectID().equalsIgnoreCase(idSub) == true) {
                check = false;
            }
        }
        return check;
    }

    public boolean checkStudentInGradeList(String id) {
        boolean check = false;
        for (Grade gra : this) {
            if (gra.getStudentID().equalsIgnoreCase(id)) {
                check = true;
            }
        }

        return check;

    }

    public boolean checkSubjectInGradeList(String id) {
        boolean check = false;
        for (Grade gra : this) {
            if (gra.getSubjectID().equalsIgnoreCase(id)) {
                check = true;
            }
        }

        return check;

    }

    public String checkPass(double labs, double progress, double finalexam) {
        String check = "";
        if (averageMark(labs, progress, finalexam) >= 4) {
            return check = "PASS";
        }
        return check = "NOT PASS";
    }

    public double averageMark(double labs, double progress, double finalexam) {
        return (labs * 0.3 + progress * 0.3 + finalexam * 0.4);
    }

    public void showInputGraded(String idStu, String idSub) {
        System.out.println("+----------+----------+-----+--------+-------+");
        System.out.println("|Student ID|Subject ID|Labs |Progress| Final |");
        System.out.println("+----------+----------+-----+--------+-------+");
        for (Grade gra : this) {
            if (gra.getStudentID().equalsIgnoreCase(idStu) == true && gra.getSubjectID().equalsIgnoreCase(idSub) == true) {
                gra.showInfor();
            }
        }
        System.out.println("+----------+----------+-----+--------+-------+");
    }

    public void showInfor() {
        if (this.size() == 0) {
            System.out.println("List is Empty");
        } else {
            System.out.println("+--------------------------------------------+");
            System.out.println("|                  GRADE LIST                |");
            System.out.println("+----------+----------+-----+--------+-------+");
            System.out.println("|Student ID|Subject ID|Labs |Progress| Final |");
            System.out.println("+----------+----------+-----+--------+-------+");
            for (int i = 0; i < this.size(); i++) {
                this.get(i).showInfor();
            }

            System.out.println("+----------+----------+-----+--------+-------+");
        }
    }

    public void writeGradeToFile() throws IOException {
        File file = new File("grade.txt");
        FileWriter fw = null;
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        for (int i = 0; i < this.size(); i++) {
            fw.write(this.get(i).toString() + "\n");
        }
        fw.close();
    }

    public void readGradeFromFile() throws IOException {
        Grade grade;
        File file = new File("grade.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            grade = new Grade();
            StringTokenizer stk = new StringTokenizer(line, ",", false);
            while (stk.hasMoreTokens()) {
                grade.setStudentID(stk.nextToken());
                grade.setSubjectID(stk.nextToken());
                grade.setLabsPoint(Double.parseDouble(stk.nextToken()));
                grade.setProgressTestsPoint(Double.parseDouble(stk.nextToken()));
                grade.setFinalExamPoint(Double.parseDouble(stk.nextToken()));
            }
            this.add(grade);
        }
        reader.close();
    }
}
