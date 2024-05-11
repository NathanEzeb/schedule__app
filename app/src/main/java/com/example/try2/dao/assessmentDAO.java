package com.example.try2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.try2.entities.assessment;

import java.util.List;
@Dao
public interface assessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(assessment a);

    @Update
    void update(assessment a);

    @Delete
    void delete(assessment a);

    @Query("SELECT * FROM ASSESSMENT ORDER BY ASSESSMENTID ASC")
    List<assessment> getAllAssessments();

    @Query("SELECT * FROM ASSESSMENT WHERE assessmentID=:currentAssessment ORDER BY AssessmentID ASC ")
    List<assessment> getAllAssessmentInfo(int currentAssessment);
}
