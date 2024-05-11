package com.example.try2.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//view active course
@Entity(tableName = "Course")
public class course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private String courseStatus;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private int termID;
    private String courseNote;
    private String endDate;
    private  String startDate;


    public course(int courseID, String startDate, String endDate, String courseNote, String courseName, String courseStatus, String instructorName,
                  String instructorEmail, String instructorPhone, int termID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.termID = termID;
        this.courseNote = courseNote;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getCourseNote() { return courseNote; }
    public void setCourseNote(String courseNote) { this.courseNote = courseNote; }
    public String getCourseName() {
        return courseName;
    }
    public int getTermID() { return termID; }
    public void setTermID(int termID) { this.termID = termID; }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }
}
