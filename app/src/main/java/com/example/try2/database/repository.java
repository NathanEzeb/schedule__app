package com.example.try2.database;

import android.app.Application;

import com.example.try2.dao.assessmentDAO;
import com.example.try2.dao.courseDAO;
import com.example.try2.dao.termDAO;
import com.example.try2.entities.assessment;
import com.example.try2.entities.course;
import com.example.try2.entities.term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class repository {


    private courseDAO mcourseDAO;
    private termDAO mtermDAO;
    private assessmentDAO massessmentDAO;

    private List<course> mAllCourses;
    private List<term> mAllTerms;
    private List<assessment> mAllAssessments;

    private static int number_of_threads = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(number_of_threads);

    public repository(Application application) {
        scheduleDatabaseBuilder db = scheduleDatabaseBuilder.getDatabase(application);
        mcourseDAO = db.courseDAO();
        mtermDAO = db.termDAO();
        massessmentDAO = db.assessmentDAO();
    }
    public List<term> getmAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mtermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllTerms;
    }

    public List<course> getmAllCourses() {
        databaseExecutor.execute(() -> {
            mAllCourses = mcourseDAO.getallcourse();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllCourses;
    }

    public List<assessment> getmAllAssessments() {
        databaseExecutor.execute(() -> {
            mAllAssessments = massessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return mAllAssessments;
    }
    /*
    public List<course>getAssociatedCourses(int courseID) {

        databaseExecutor.execute(() -> {
            mAllCourses = mcourseDAO.getAssociatedCourses(courseID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses
    };
*/

    public void delete(course course) {
        databaseExecutor.execute(() -> {
            mcourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update(course course) {
        databaseExecutor.execute(() -> {
            mcourseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    public void insert(course course) {
        databaseExecutor.execute(() -> {
            mcourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    public void update(term term) {
        databaseExecutor.execute(() -> {
            mtermDAO.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    public void delete(term term) {
        databaseExecutor.execute(() -> {
            mtermDAO.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    public void insert(term Term) {
        databaseExecutor.execute(() -> {
            mtermDAO.insert(Term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    public void delete(assessment assessment) {
        databaseExecutor.execute(() -> {
            massessmentDAO.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void update(assessment assessment) {
        databaseExecutor.execute(() -> {
            massessmentDAO.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void insert(assessment assessment) {
        databaseExecutor.execute(() -> {
            massessmentDAO.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}