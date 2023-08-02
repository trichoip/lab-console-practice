/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.util.Map;
import gui.Menu;
import tools.Utils;

/**
 *
 * @author minh tri
 */
public class StudentManagement {

    StudentList stl;
    SubjectList sbl;
    GradeList gl;

    public StudentManagement() throws IOException {
        this.stl = new StudentList();
        this.sbl = new SubjectList();
        this.gl = new GradeList();
        stl.readStudentFromFile();
        sbl.readSubjectFromFile();
        gl.readGradeFromFile();
        stl.showInfor();
        sbl.showInfor();
        gl.showInfor();
    }

//ADD STUDENT
    public void addStudent() throws IOException {
        boolean check = true;
        while (check) {
            String id = Utils.getString("\nInput Students ID: ").toUpperCase();
            if (stl.checkContainID(id) == true) {
                System.out.println("\nDuplicate ID!!!!!\n");
                check = Utils.confirmYesNo("Do you want continue(Y/N)? ");
            } else {
                String firstName = Utils.getString("Input First Name: ");
                String lastName = Utils.getString("Input Last Name: ");
                String gender = stl.checkGenderValid(id);
                String dateOfBirth = stl.checkDateValid(id);
                String email = stl.checkEmailValid(id);
                String phoneNumber = stl.checkPhoneValid(id);
                stl.put(id, new Student(id, firstName, lastName, gender, dateOfBirth, email, phoneNumber));
                stl.writeStudentToFile();
                stl.showIdInput(id);
                System.out.println("Add Successfully!!!");
                check = Utils.confirmYesNo("Do you want continue(Y/N)? ");

            }
        }
    }

//  UPDATE STUDENT
    public void updateInfoStudent(String id) throws IOException {
        if (stl.checkContainID(id) == true) {
            String firstName = Utils.updateString("\nInput First Name: ", stl.get(id).getFirstName());
            String lastName = Utils.updateString("Input Last Name: ", stl.get(id).getLastName());
            String gender = stl.checkGenderValid(id);
            String dateOfBirth = stl.checkDateValid(id);
            String email = stl.checkEmailValid(id);
            String phoneNumber = stl.checkPhoneValid(id);
            stl.replace(id, new Student(id, firstName, lastName, gender, dateOfBirth, email, phoneNumber));
            stl.writeStudentToFile();
            stl.showIdInput(id);
            System.out.println("Update Successfully!!!");
        } else {
            stl.showIdInput(id);
            System.out.println("Update Failed!!!");
        }
    }

//  REMOTE STUDENT
    public void remoteStudent(String id) throws IOException {
        if (gl.checkStudentInGradeList(id) == true) {
            System.out.println("Student already has graded so can not remote: " + id);
        } else {

            if (stl.checkContainID(id) == true) {
                stl.showIdInput(id);
                boolean checkRemote = Utils.confirmYesNo("Do you want remote student(Y/N)? ");
                if (checkRemote == true) {
                    stl.remove(id);
                    stl.writeStudentToFile();
                    stl.showIdInput(id);
                    System.out.println("Remote Successfully!!!");
                } else {
                    System.out.println("Remote failed!!!");
                }
            } else {
                stl.showIdInput(id);
                System.out.println("Remote failed!!!");
            }

        }

    }

// MENU UPDATE STUDENT
    public void updateStudent() throws IOException {
        boolean val = true;
        boolean check = true;

        Menu menuUpdate = new Menu();
        menuUpdate.addItem("╔══STUDENT═══╗");
        menuUpdate.addItem(" 1.Update Information Student");
        menuUpdate.addItem(" 2.Delete Student");
        menuUpdate.addItem(" Others.Back main menu");
        int choice;
        while (val) {
            stl.showInfor();
            String id = Utils.getString("Input Students ID Want Update: ").toUpperCase();
            if (stl.checkContainID(id) == true) {

                while (check) {
                    menuUpdate.showMenu();
                    choice = menuUpdate.getChoice();
                    switch (choice) {
                        case 1:
                            this.updateInfoStudent(id);
                            break;
                        case 2:
                            this.remoteStudent(id);
                            break;
                        default:
                            check = false;
                            val = false;
                    }
                }
            } else {
                System.out.println("\nStudent does not exist!!!\n");
                val = menuUpdate.confirmYesNo("Do you want continue(Y/N)? ");
            }
        }

    }

// AAD SUBJECT
    public void addSubject() throws IOException {
        boolean check = true;
        while (check) {

            String id = Utils.getString("\nInput Subject ID: ").toUpperCase();
            if (sbl.checkContainID(id) == true) {
                System.out.println("\nDuplicate ID!!!!!\n");
                check = Utils.confirmYesNo("Do you want continue(Y/N)? ");
            } else {
                String subjectName = sbl.inputName(id);
                int credit = Utils.getInt("Input credit: ", 0, 100);
                sbl.put(id, new Subject(id, subjectName, credit));
                sbl.writeSubjectToFile();
                sbl.showIdInput(id);
                check = Utils.confirmYesNo("Do you want continue(Y/N)? ");
            }
        }
    }

// UPDATE SUBJECT
    public void updateInfoSubject(String id) throws IOException {
        if (sbl.checkContainID(id) == true) {
            String subjectName = sbl.inputName(id);
            int credit = Utils.updateInt("Input credit: ", 0, 100, sbl.get(id).getCredit());
            sbl.replace(id, new Subject(id, subjectName, credit));
            sbl.writeSubjectToFile();
            sbl.showIdInput(id);
            System.out.println("Update Successfully!!!");
        } else {
            sbl.showIdInput(id);
            System.out.println("Update Failed!!!");
        }
    }

// REMOTE SUBJECT
    public void remoteSubject(String id) throws IOException {
        if (gl.checkSubjectInGradeList(id) == true) {
            System.out.println("Subject already has graded for student so can not remote: " + id);
        } else {

            if (sbl.checkContainID(id) == true) {
                sbl.showIdInput(id);
                boolean checkRemote = Utils.confirmYesNo("Do you want remote student(Y/N)? ");
                if (checkRemote == true) {
                    sbl.remove(id);
                    sbl.writeSubjectToFile();
                    sbl.showIdInput(id);
                    System.out.println("Remote Successfully!!!");
                } else {

                    System.out.println("Remote failed!!!");
                }
            } else {
                sbl.showIdInput(id);
                System.out.println("Remote failed!!!");
            }
        }

    }

// MENU UPDATE SUBJECT
    public void updateSubject() throws IOException {
        boolean val = true;
        boolean check = true;
        Menu menuUpdate = new Menu();
        menuUpdate.addItem("╔══SUBJECT══╗");
        menuUpdate.addItem(" 1.Update Information Subject");
        menuUpdate.addItem(" 2.Delete Subject");
        menuUpdate.addItem(" Others.Back main menu");
        int choice;
        while (val) {
            sbl.showInfor();
            String id = Utils.getString("Input Subject ID Want Update: ").toUpperCase();
            if (sbl.checkContainID(id) == true) {
                while (check) {
                    menuUpdate.showMenu();
                    choice = menuUpdate.getChoice();
                    switch (choice) {
                        case 1:
                            this.updateInfoSubject(id);
                            break;
                        case 2:
                            this.remoteSubject(id);
                            break;
                        default:
                            check = false;
                            val = false;
                    }
                }
            } else {
                System.out.println("\nSubject does not exist!!!\n");
                val = menuUpdate.confirmYesNo("Do you want continue(Y/N)? ");
            }
        }
    }

// ENTER GRADED
    public void enterGrade() throws IOException {
        boolean check = true;
        while (check) {
            boolean val = true;
            stl.showInfor();
            String idStudent = Utils.getString("Input student ID: ").toUpperCase();
            if (stl.checkContainID(idStudent) == true) {
                while (val) {
                    sbl.showInfor();
                    String idSubject = Utils.getString("Input subject ID: ").toUpperCase();
                    if (sbl.checkContainID(idSubject) == true) {
                        gl.inputGrade(idStudent, idSubject);
                        check = Utils.confirmYesNo("Do you want continue(Y/N)? ");
                        val = false;
                    } else {
                        System.out.println("Subject does not exist!!!!!!!!!!");
                        val = Utils.confirmYesNo("Do you want input subject ID aganst(Y/N)? ");
                        if (val == false) {
                            check = false;
                        }
                    }
                }
            } else {
                System.out.println("Student does not exist!!!!!!!!!!!!");
                check = Utils.confirmYesNo("Do you want continue(Y/N)? ");
            }
        }
    }

// STUDENT REPORT
    public void stundentGradeReport() {
        boolean check = true;
        while (check) {
            stl.showInfor();
            String id = Utils.getString("Input ID student: ").toUpperCase();
            if (stl.checkContainID(id) == true) {
                System.out.println("\n- Student ID: " + id);
                System.out.println("- Student Name: " + stl.get(id).getFirstName() + " " + stl.get(id).getLastName());
                System.out.println("List of subject sort by Subject Name:");
                System.out.println("+------+-----------------------------+-------------+--------------+");
                System.out.println("|  No  |        Subject name         |Average mark |    Status    |");
                System.out.println("+------+-----------------------------+-------------+--------------+");

                if (gl.checkStudentInGradeList(id) == true) {
                    this.showInfoSubjectSortNameByStudentId(id);

                    System.out.println("+------+-----------------------------+-------------+--------------+");
                } else {
                    System.out.println("|          Student does not have any grades in any subject!!!!    |");
                    System.out.println("+-----------------------------------------------------------------+");
                }
                check = false;
            } else {
                System.out.println("Student does not exist!!!");
                check = Utils.confirmYesNo("Do you want continue(Y/N)? ");
            }
        }
    }

    //SHOW INFORMATION SUBJECT AFTER INPUT STUDENT ID
    public void showInfoSubjectSortNameByStudentId(String id) {
        int i = 1;
        for (Map.Entry<String, Subject> entry : sbl.sort().entrySet()) {
            for (Grade gra : gl) {
                if (gra.getStudentID().equalsIgnoreCase(id) == true && gra.getSubjectID().equalsIgnoreCase(entry.getKey()) == true) {
                    String nameSort = entry.getValue().getSubjectName();
                    double average = gl.averageMark(gra.getLabsPoint(), gra.getProgressTestsPoint(), gra.getFinalExamPoint());
                    String checkPass = gl.checkPass(gra.getLabsPoint(), gra.getProgressTestsPoint(), gra.getFinalExamPoint());
                    System.out.printf("|%2s%-4d|   %-26s|    %-9.1f|   %-11s|\n", "", i++, nameSort, average, checkPass);
                }
            }
        }
    }

    // SUBJECT REPORT
    public void subjectGradeReport() {
        boolean check = true;
        while (check) {
            sbl.showInfor();
            String id = Utils.getString("Input ID subject: ").toUpperCase();
            if (sbl.checkContainID(id) == true) {
                System.out.println("\n- Subject ID: " + id);
                System.out.println("- Subject Name: " + sbl.get(id).getSubjectName());
                System.out.println("List of student sort by Student Name:");
                System.out.println("+------------------+-------------------+--------------------+--------------+");
                System.out.println("|    Student ID    |   Student name    |    Average mark    |    Status    |");
                System.out.println("+------------------+-------------------+--------------------+--------------+");
                if (gl.checkSubjectInGradeList(id) == true) {
                    this.showInfoStudentSortNameBySubjectId(id);
                    System.out.println("+------------------+-------------------+--------------------+--------------+");
                } else {
                    System.out.println("|        Any Student does not have any grades for this Subject!!!!         |");
                    System.out.println("+--------------------------------------------------------------------------+");
                }
                check = false;
            } else {
                System.out.println("Subject does not exist!!!");
                check = Utils.confirmYesNo("Do you want continue(Y/N)? ");
            }
        }
    }

    //SHOW INFORMATION STUDENT AFTER INPUT SUBJECT ID
    public void showInfoStudentSortNameBySubjectId(String id) {

        for (Map.Entry<String, Student> entry : stl.sort().entrySet()) {
            for (Grade gra : gl) {
                if (gra.getSubjectID().equalsIgnoreCase(id) == true && gra.getStudentID().equalsIgnoreCase(entry.getKey()) == true) {
                    String nameSort = entry.getValue().getFirstName() + " " + entry.getValue().getLastName();
                    double average = gl.averageMark(gra.getLabsPoint(), gra.getProgressTestsPoint(), gra.getFinalExamPoint());
                    String checkPass = gl.checkPass(gra.getLabsPoint(), gra.getProgressTestsPoint(), gra.getFinalExamPoint());
                    System.out.printf("|%5s%-13s|   %-16s|        %-12.1f|   %-11s|\n", "", gra.getStudentID(), nameSort, average, checkPass);
                }
            }
        }
    }
}
