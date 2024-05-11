package com.example.try2.entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//view active term
@Entity(tableName = "Term")
public class term {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String termTitle;
    private String startDate;
    private String endDate;
    private String activeCourses;

    public term(int termID, String termTitle, String startDate, String endDate, String activeCourses) {
        this.termID = termID;
        this.termTitle = termTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activeCourses = activeCourses;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getActiveCourses() {
        return activeCourses;
    }

    public void setActiveCourses(String activeCourses) {
        this.activeCourses = activeCourses;
    }
}
