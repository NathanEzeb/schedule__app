package com.example.try2.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.try2.entities.course;
import com.example.try2.entities.term;

import java.util.List;
@Dao
public interface termDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(term t);

    @Update
    void update(term t);

    @Delete
    void delete(term t);

    @Query("SELECT * FROM TERM ORDER BY TERMID ASC")
    List<term> getAllTerms();

    @Query("SELECT * FROM TERM WHERE TERMID=:currentTerm ORDER BY TERMID ASC")
    List<term> getalltermInfo(int currentTerm);
}
