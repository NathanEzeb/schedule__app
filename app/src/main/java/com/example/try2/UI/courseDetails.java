package com.example.try2.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.try2.R;
import com.example.try2.adapters.assessmentAdapter;
import com.example.try2.adapters.courseAdapter;
import com.example.try2.database.repository;
import com.example.try2.entities.assessment;
import com.example.try2.entities.course;
import com.example.try2.entities.term;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class courseDetails extends AppCompatActivity {
    int courseID;
    int termID;
    String endDate;
    String startDate;
    String instructorName;
    String Status;
    String instructorEmail;
    String instructorPhone;
    String courseTitle;
    String note;
    course currentCourse;
    int numCourses;
    String endDateString;
    String startDateString;

    EditText editInsName;

    EditText editInsEmail;
    EditText editInsPhone;
    EditText editStatus;
    EditText editCourseTitle;
    EditText editNote;
    TextView editDate;
    TextView editDate2;
    DatePickerDialog.OnDateSetListener startDate2;
    DatePickerDialog.OnDateSetListener endDate2;
    final Calendar myCalendarStart = Calendar.getInstance();

    private repository Repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Repository = new repository(getApplication());

        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instructorName = getIntent().getStringExtra("insName");
        Status = getIntent().getStringExtra("status");
        instructorEmail = getIntent().getStringExtra("insEmail");
        instructorPhone = getIntent().getStringExtra("insPhone");
        courseTitle = getIntent().getStringExtra("name");
        endDate = getIntent().getStringExtra("endDate");
        startDate = getIntent().getStringExtra("startDate");
        termID = getIntent().getIntExtra("termID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);
        note = getIntent().getStringExtra("courseNote");


        editCourseTitle = findViewById(R.id.courseTitleET);
        editInsEmail = findViewById(R.id.insEmailET);
        editInsName = findViewById(R.id.insNameET);
        editInsPhone = findViewById(R.id.insPhoneNumET);
        editStatus = findViewById(R.id.courseStatusET);
        editNote = findViewById(R.id.courseNoteET);
        editDate = findViewById(R.id.editDateTV);
        editDate2 = findViewById(R.id.editDate2);

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=editDate.getText().toString();
                if(info.equals(""))info="07/01/23";
                try{
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(courseDetails.this, startDate2, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editDate2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=editDate2.getText().toString();
                if(info.equals(""))info="07/01/23";
                try{
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(courseDetails.this, endDate2, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startDate2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        endDate2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart2();
            }
        };




        editCourseTitle.setText(courseTitle);
        editInsName.setText(instructorName);
        editInsEmail.setText(instructorEmail);
        editStatus.setText(Status);
        editInsPhone.setText(instructorPhone);
        editNote.setText(note);
        if(endDate == null){
            editDate.setText("Set End Date");
        }
        else editDate.setText(endDate);

        if (startDate != null){
            editDate2.setText(startDate);
        }
        else editDate2.setText("Set Start Date");


        RecyclerView recyclerView = findViewById(R.id.assessmentRecycler);
        Repository = new repository(getApplication());
        List<assessment> allAssessments = Repository.getmAllAssessments();
        final assessmentAdapter AssessmentAdapter = new assessmentAdapter(this);
        recyclerView.setAdapter(AssessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<assessment> filteredAssessments = new ArrayList<>();
        for (assessment a : Repository.getmAllAssessments()) {
            if (a.getCourseID() == courseID) filteredAssessments.add(a);
        }
        AssessmentAdapter.setAssessments(filteredAssessments);
    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(myCalendarStart.getTime()));
    };
    private void updateLabelStart2() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate2.setText(sdf.format(myCalendarStart.getTime()));
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.courseSave) {
            course Course;
            if (courseID == -1) {
                    if (Repository.getmAllCourses().size() == 0)
                        courseID = 1;
                    else
                        courseID = Repository.getmAllCourses().get(Repository.getmAllCourses().size() - 1).getCourseID() + 1;
                    Course = new course(courseID, editDate2.getText().toString(), editDate.getText().toString(),
                            editNote.getText().toString(), editCourseTitle.getText().toString(),
                            editStatus.getText().toString(), editInsName.getText().toString(),
                            editInsEmail.getText().toString(), editInsPhone.getText().toString(), termID);
                    Repository.insert(Course);
                    this.finish();
                } else {
                    Course = new course(courseID, editDate2.getText().toString(), editDate.getText().toString(),
                            editNote.getText().toString(), editCourseTitle.getText().toString(),
                            editStatus.getText().toString(), editInsName.getText().toString(),
                            editInsEmail.getText().toString(), editInsPhone.getText().toString(), termID);
                    Repository.update(Course);
                    this.finish();
                }
                return true;
            }

        if (item.getItemId() == R.id.noteShare) {
            Intent sentIntent= new Intent();
            sentIntent.setAction(Intent.ACTION_SEND);
            sentIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString()+ "(sent from app)");
            sentIntent.putExtra(Intent.EXTRA_TITLE, editNote.getText().toString()+ "course note");
            sentIntent.setType("text/plain");
            Intent shareIntent=Intent.createChooser(sentIntent,null);
            startActivity(shareIntent);
            return true;
        }


        if (item.getItemId() == R.id.startDateNotify) {
            String dateFromScreen = editDate.getText().toString();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger = myDate.getTime();
            Intent intent = new Intent(courseDetails.this, MyReceiver.class);
            intent.putExtra("key", courseTitle +" begins today!");
            PendingIntent sender=PendingIntent.getBroadcast(courseDetails.this,++MainActivity.numAlert, intent,PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger,sender);
            return true;
        }


        if (item.getItemId() == R.id.endDateNotify) {
            String dateFromScreen = editDate2.getText().toString();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger = myDate.getTime();
            Intent intent = new Intent(courseDetails.this, MyReceiver2.class);
            intent.putExtra("key2", courseTitle +" ends today!");
            PendingIntent sender=PendingIntent.getBroadcast(courseDetails.this,++MainActivity.numAlert, intent,PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger,sender);
            return true;
        }

        if(item.getItemId()== R.id.courseDelete){
                for(course c:Repository.getmAllCourses()){
                    if(c.getCourseID()==courseID)currentCourse=c;
                }
                Repository.delete(currentCourse);
            Toast.makeText(courseDetails.this, currentCourse.getCourseName() + " was deleted",Toast.LENGTH_LONG).show();
            courseDetails.this.finish();
        }



        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void goToAssessmentDetails(View v) {
        Intent i = new Intent(this, assessmentDetails.class);
        i.putExtra("courseID", courseID);
        startActivity(i);
    }
    @Override
    protected void onResume() {

        super.onResume();
        List<assessment> allAssessments=Repository.getmAllAssessments();
        RecyclerView recyclerView=findViewById(R.id.assessmentRecycler);
        final assessmentAdapter AssessmentAdapter=new assessmentAdapter(this);
        recyclerView.setAdapter(AssessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<assessment> filteredAssessments = new ArrayList<>();
        for (assessment a : Repository.getmAllAssessments()) {
            if (a.getCourseID() == courseID) filteredAssessments.add(a);
        }
        AssessmentAdapter.setAssessments(filteredAssessments);
    }
}