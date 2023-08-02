/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import tools.Utils;

/**
 *
 * @author minh tri
 */
public class HospitalManagement {

    DoctorList docList;
    DepartmentList deptList;

    public HospitalManagement() throws IOException {
        docList = new DoctorList();
        deptList = new DepartmentList();
        docList.readDoctorFromFile();
        deptList.readDepartmentFromFile();

    }


// ADD DOCTOR
    public void addDoctor() throws IOException {
        boolean check = true;
        while (check) {
            String id = Utils.getString("Input doctor ID: ").toUpperCase();
            if (docList.checkContainDoctorID(id) == true) {
                System.out.println("Duplicate!!!");
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            } else {
                String name = Utils.getString("Input name doctor: ");
                String sex = docList.inputSexValid(id);
                String address = Utils.getString("Input address doctor: ");
                String deptID = deptList.inputDeptValid();
                String createDate = Utils.getDateCurrent();
                docList.put(id, new Doctor(id, name, sex, address, deptID, createDate));
                docList.showInput(id);
                System.out.println("Add successfully!!!");
                docList.writeDoctorToFile();
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            }
        }

    }

// UPDATE DOCTOR
    public void updateDoctor() throws IOException {
        boolean check = true;
        Doctor doctor;
        while (check) {
            this.showAllDataDoctor();
            String id = Utils.getString("Input doctor ID: ").toUpperCase();

            if (docList.checkContainDoctorID(id) == true) {

                docList.get(id).setName(Utils.updateString("Input name doctor: ", docList.get(id).getName()));
                docList.get(id).setSex(docList.inputSexValid(id));
                docList.get(id).setAddress(Utils.updateString("Input address doctor: ", docList.get(id).getAddress()));
                docList.get(id).setDepartmentID(this.inputDeptValidUpdate(id));
                docList.get(id).setCreateDate(docList.get(id).getCreateDate());
                docList.get(id).setLastUpdateDate(Utils.getDateCurrent());
                docList.showInput(id);
                System.out.println("Update successfully!!!");
                docList.writeDoctorToFile();
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            } else {
                System.out.println("Doctor does not exist!!!");
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            }
        }
    }

//input department valid update
    public String inputDeptValidUpdate(String id) {
        String dept = "";
        boolean check = true;
        while (check) {
            dept = Utils.updateString("Input department ID: ", docList.get(id).getDepartmentID()).toUpperCase();
            if (deptList.checkContainDeptID(dept) == true) {
                check = false;
            } else {
                System.out.println("Department does not exist!!!");
                System.out.println("Input again!!!");
            }
        }
        return dept;
    }

// DELETE DOCTOR
    public void deleteDoctor() throws IOException {
        boolean check = true;
        while (check) {
            this.showAllDataDoctor();
            String id = Utils.getString("Input doctor ID: ").toUpperCase();
            if (docList.checkContainDoctorID(id) == true) {
                docList.showInput(id);
                boolean comfirmDelete = Utils.confirmYesNo("Do you want remote it(Y/N)? ");
                if (comfirmDelete == true) {
                    docList.remove(id);
                    docList.writeDoctorToFile();

                    System.out.println("Remote successfully!!!");
                    check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                } else {
                    System.out.println("Remote failed!!!");
                    check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                }

            } else {
                System.out.println("Doctor does not exist!!!");
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            }
        }

    }


// ADD DEPARTMENT
    public void addDepartment() throws IOException {
        boolean check = true;
        while (check) {
            String id = Utils.getString("Input Department ID: ").toUpperCase();
            if (deptList.checkContainDeptID(id) == true) {
                System.out.println("Duplicate!!!");
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            } else {
                String name = Utils.getString("Input name department: ");
                String createDate = Utils.getDateCurrent();
                deptList.put(id, new Department(id, name, createDate));
                deptList.showInput(id);
                System.out.println("Add successfully!!!");
                deptList.writeDepartmentToFile();
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            }
        }
    }

// UPDATE DEPARTMENT
    public void updateDepartment() throws IOException {
        boolean check = true;
        while (check) {
            this.showAllDataDepartment();
            String id = Utils.getString("Input department ID: ").toUpperCase();
            if (deptList.checkContainDeptID(id) == true) {

                deptList.get(id).setName(Utils.updateString("Input name department: ", deptList.get(id).getName()));
                deptList.get(id).setCreateDate(deptList.get(id).getCreateDate());
                deptList.get(id).setLastUpdateDate(Utils.getDateCurrent());

                System.out.println("Update successfully!!!");
                deptList.showInput(id);
                deptList.writeDepartmentToFile();
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            } else {
                System.out.println("Department does not exist!!!");
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            }
        }
    }

// DELETE DEPARTMENT
    public void deleteDepartment() throws IOException {
        boolean check = true;
        while (check) {
            this.showAllDataDepartment();
            String id = Utils.getString("Input department ID: ").toUpperCase();
            if (deptList.checkContainDeptID(id) == true) {
                if (this.checkDeptIdInDoctorList(id) == true) {
                    System.out.println("Doctor already exists in this department so it cannot be deleted!!!");
                    check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                } else {
                    deptList.showInput(id);
                    boolean comfirmDelete = Utils.confirmYesNo("Do you want remote it(Y/N)? ");
                    if (comfirmDelete == true) {
                        deptList.remove(id);
                        deptList.writeDepartmentToFile();
                        System.out.println("Remote successfully!!!");
                        check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                    } else {
                        System.out.println("Remote failed!!!");
                        check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
                    }

                }

            } else {
                System.out.println("Department does not exist!!!");
                check = Utils.confirmYesNo("Do you want to continue(Y/N)? ");
            }
        }
    }

    // CHECK DEPARTMENT IN DOCTOR LIST
    public boolean checkDeptIdInDoctorList(String id) {
        boolean check = false;
        for (Doctor value : docList.values()) {
            if (value.getDepartmentID().equalsIgnoreCase(id) == true) {
                check = true;
            }
        }
        return check;
    }

    // SHOW ALL DOCTOR
    public void showAllDataDoctor() {
        if (docList.isEmpty() == true) {
            System.out.println("List empty!!!");
        } else {
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                    DOCTOR LIST                                                                     |");
            System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
            System.out.println("|Doctor ID| Name Doctor  |  Sex   |                     Address                       |Department ID| Create Date | Last Update Date |");
            System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
            for (Doctor value : docList.values()) {
                value.showInfor();
            }
            System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
        }
    }

// SHOW ALL DEPARTMENT
    public void showAllDataDepartment() {
        if (deptList.isEmpty() == true) {
            System.out.println("List empty!!!");
        } else {
            System.out.println("+--------------------------------------------------------------------+");
            System.out.println("|                        DEPARTMENT LIST                             |");
            System.out.println("+-------------+---------------------+-------------+------------------+");
            System.out.println("|Department ID|        Name         | Create Date | Last Update Date |");
            System.out.println("+-------------+---------------------+-------------+------------------+");

            for (Department value : deptList.values()) {
                value.showInfor();
            }
            System.out.println("+-------------+---------------------+-------------+------------------+");
        }
    }

// SEARCH ID DEPARTMENT
    public void searchDepartmentByID() {
        boolean check = true;
        String id = Utils.getString("Input department ID: ").toUpperCase();
        System.out.println("+-------------+---------------------+-------------+------------------+");
        System.out.println("|Department ID|        Name         | Create Date | Last Update Date |");
        System.out.println("+-------------+---------------------+-------------+------------------+");
        if (deptList.checkContainDeptID(id) == true) {
            for (Department value : deptList.values()) {
                if (value.getDepartmentID().equalsIgnoreCase(id) == true) {
                    value.showInfor();
                }
            }
        } else {
            System.out.println("|           Department ID \"" + id + "\" NOT FOUND!!!");

        }
        System.out.println("+-------------+---------------------+-------------+------------------+");
    }

    // SEARCH NAME DOCTOR
    public void searchDoctorByName() {
        boolean check = true;
        String name = Utils.getString("Input name doctor: ");
        System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
        System.out.println("|Doctor ID| Name Doctor  |  Sex   |                     Address                       |Department ID| Create Date | Last Update Date |");
        System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
        for (Doctor value : docList.values()) {
            if (value.getName().contains(name) == true) {
                value.showInfor();
            }
        }
        System.out.println("+---------+--------------+--------+---------------------------------------------------+-------------+-------------+------------------+");
    }

//======================================================================================================================================================
    public void storeAllDateToFile() throws IOException {
        docList.writeDoctorToFile();
        System.out.println("Store list doctor successfully!!!");
        deptList.writeDepartmentToFile();
        System.out.println("Store list department successfully!!!");

    }

}
