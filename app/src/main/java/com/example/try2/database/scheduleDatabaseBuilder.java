package com.example.try2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.try2.dao.assessmentDAO;
import com.example.try2.dao.courseDAO;
import com.example.try2.dao.termDAO;
import com.example.try2.entities.assessment;
import com.example.try2.entities.course;
import com.example.try2.entities.term;

@Database(entities = {course.class, term.class, assessment.class}, version = 14, exportSchema = false)
public abstract class scheduleDatabaseBuilder extends RoomDatabase {
    public abstract courseDAO courseDAO();
    public abstract termDAO termDAO();
    public abstract assessmentDAO assessmentDAO();
    private static volatile scheduleDatabaseBuilder INSTANCE;

    static scheduleDatabaseBuilder getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (scheduleDatabaseBuilder.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),scheduleDatabaseBuilder.class,
                    "myScheduleDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
