/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author minh tri
 */
public class Doctor {

    private String doctorID;
    private String name;
    private String sex;
    private String address;
    private String departmentID;
    private String createDate;
    private String lastUpdateDate;

    public Doctor() {
    }

    public Doctor(String doctorID, String name, String sex, String address, String departmentID, String createDate) {
        this.doctorID = doctorID;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.departmentID = departmentID;
        this.createDate = createDate;
    }

    public Doctor(String doctorID, String name, String sex, String address, String departmentID, String createDate, String lastUpdateDate) {
        this.doctorID = doctorID;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.departmentID = departmentID;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return doctorID + "|" + name + "|" + sex + "|" + address + "|" + departmentID + "|" + createDate + "|" + lastUpdateDate;
    }

    public void showInfor() {
        System.out.printf("|  %-7s| %-13s| %-7s| %-50s|    %-9s| %-12s|    %-14s|\n", doctorID, name, sex, address, departmentID, createDate, lastUpdateDate);
    }

}
