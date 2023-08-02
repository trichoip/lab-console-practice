package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author minh tri
 */
public class Grade {

    private String studentID;
    private String subjectID;
    private Double labsPoint;
    private Double progressTestsPoint;
    private Double finalExamPoint;

    public Grade() {
    }

    public Grade(String studentID, String subjectID, Double labsPoint, Double progressTestsPoint, Double finalExamPoint) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.labsPoint = labsPoint;
        this.progressTestsPoint = progressTestsPoint;
        this.finalExamPoint = finalExamPoint;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public Double getLabsPoint() {
        return labsPoint;
    }

    public void setLabsPoint(Double labsPoint) {
        this.labsPoint = labsPoint;
    }

    public Double getProgressTestsPoint() {
        return progressTestsPoint;
    }

    public void setProgressTestsPoint(Double progressTestsPoint) {
        this.progressTestsPoint = progressTestsPoint;
    }

    public Double getFinalExamPoint() {
        return finalExamPoint;
    }

    public void setFinalExamPoint(Double finalExamPoint) {
        this.finalExamPoint = finalExamPoint;
    }

    @Override
    public String toString() {
        return studentID + "," + subjectID + "," + labsPoint + "," + progressTestsPoint + "," + finalExamPoint;
    }

    public void showInfor() {
        System.out.printf("| %-9s|%8s  |%4s |   %-5s|%5s  |\n", studentID, subjectID, labsPoint, progressTestsPoint, finalExamPoint);
    }

}
