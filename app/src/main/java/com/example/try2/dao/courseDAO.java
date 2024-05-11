package com.example.try2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.try2.entities.course;

import java.util.List;

@Dao
public interface courseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(course c);

    @Update
    void update(course c);

    @Delete
    void delete(course c);

    @Query("SELECT * FROM COURSE ORDER BY COURSEID ASC")
    List<course> getallcourse();

    @Query("SELECT * FROM COURSE WHERE courseid=:currentCourse ORDER BY COURSEID ASC ")
    List<course> getallcourseInfo(int currentCourse);
}
