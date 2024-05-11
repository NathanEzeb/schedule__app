package com.example.try2.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment")
public class assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private  String type;
    private String assessTitle;
    private int courseID;
    private String startDate;
    private String endDate;



    public assessment(int assessmentID, String startDate, String endDate, String type, String assessTitle, int courseID) {
        this.assessmentID = assessmentID;
        this.courseID = courseID;
        this.assessTitle = assessTitle;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getAssessTitle() {
        return assessTitle;
    }
    public void setAssessTitle(String assessTitle) {
        this.assessTitle = assessTitle;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
