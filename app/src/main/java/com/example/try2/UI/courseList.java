package com.example.try2.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.try2.adapters.courseAdapter;
import com.example.try2.adapters.termAdapter;
import com.example.try2.database.repository;
import com.example.try2.R;
import com.example.try2.entities.course;
import com.example.try2.entities.term;

import java.util.ArrayList;
import java.util.List;

public class courseList extends AppCompatActivity {
    String name;
    String startDate;
    String endDate;
    String alert;
    int termID;
    EditText editName;
    EditText editStartDate;
    EditText editEndDate;
    EditText editAlert;
    private repository Repository;
    term currentTerm;
    int numCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = findViewById(R.id.termNameET);
        editStartDate = findViewById(R.id.startDateET);
        editEndDate = findViewById(R.id.endDateET);
        editAlert = findViewById(R.id.alertET);

        name = getIntent().getStringExtra("title");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        alert = getIntent().getStringExtra("alert");
        termID = getIntent().getIntExtra("termID", -1);


        editName.setText(name);
        editStartDate.setText(startDate);
        editEndDate.setText(endDate);
        editAlert.setText(alert);

        RecyclerView recyclerView = findViewById(R.id.courseRecycler);
        Repository= new repository(getApplication());
        List<course> allCourses=Repository.getmAllCourses();
        final courseAdapter CourseAdapter = new courseAdapter(this);
        recyclerView.setAdapter(CourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<course> filteredCourses = new ArrayList<>();
        for (course c : Repository.getmAllCourses()) {
            if (c.getTermID() == termID) filteredCourses.add(c);
        }
        CourseAdapter.setCourses(filteredCourses);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_course_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.termDelete){
            for(term t:Repository.getmAllTerms()){
                if(t.getTermID()==termID)currentTerm=t;
            }
            numCourses=0;
            for(course Course: Repository.getmAllCourses()){
                if(Course.getTermID()==termID)++numCourses;
            }
            if(numCourses==0){
                Repository.delete(currentTerm);
                Toast.makeText(courseList.this, currentTerm.getTermTitle() + " was deleted",Toast.LENGTH_LONG).show();
                courseList.this.finish();
            }
            else{
                Toast.makeText(courseList.this, "Can't delete a term with courses",Toast.LENGTH_LONG).show();
            }
        }
        if (item.getItemId()==R.id.termSave) {
            term Term;
            if (termID == -1) {
                if (Repository.getmAllTerms().size() == 0) termID = 1;
                else termID = Repository.getmAllTerms().get(Repository.getmAllTerms().size() - 1).getTermID() + 1;
                Term = new term(termID, editName.getText().toString(), editStartDate.getText().toString(),
                        editEndDate.getText().toString(), editAlert.getText().toString());
                Repository.insert(Term);
                this.finish();
            }
            else {
                Term = new term(termID, editName.getText().toString(), editStartDate.getText().toString(),
                        editEndDate.getText().toString(), editAlert.getText().toString());
                Repository.update(Term);
                this.finish();
            }

        }
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
    public void goToCourseDetails(View v) {
        Intent i = new Intent(this, courseDetails.class);
        i.putExtra("termID", termID);
        startActivity(i);
    }
    @Override
    protected void onResume() {

        super.onResume();
        List<course> allCourses=Repository.getmAllCourses();
        RecyclerView recyclerView=findViewById(R.id.courseRecycler);
        final courseAdapter CourseAdapter=new courseAdapter(this);
        recyclerView.setAdapter(CourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<course> filteredCourses = new ArrayList<>();
        for (course c : Repository.getmAllCourses()) {
            if (c.getTermID() == termID) filteredCourses.add(c);
        }
        CourseAdapter.setCourses(filteredCourses);
    }
}