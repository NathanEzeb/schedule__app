package com.example.try2.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.example.try2.database.repository;
import com.example.try2.entities.assessment;
import com.example.try2.entities.course;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class assessmentDetails extends AppCompatActivity {
    String startDate;
    String endDate;
    String title;
    String type;
    assessment currentAssessment;
    int courseID;
    int assessmentID;
    TextView editDate;
    TextView editDate2;
    DatePickerDialog.OnDateSetListener startDate2;
    DatePickerDialog.OnDateSetListener endDate2;
    final Calendar myCalendarStart = Calendar.getInstance();



    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    EditText editType;
    private repository Repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        Repository=new repository(getApplication());

        Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = getIntent().getStringExtra("title");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        courseID = getIntent().getIntExtra("courseID", -1);
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        type = getIntent().getStringExtra("asType");

        editTitle = findViewById(R.id.assessmentTitleEV);
        editType = findViewById(R.id.assessmentTypeEV);
        editDate = findViewById(R.id.startDate9tv);
        editDate2 = findViewById(R.id.endDate12tv);

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
                new DatePickerDialog(assessmentDetails.this, startDate2, myCalendarStart
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
                new DatePickerDialog(assessmentDetails.this, endDate2, myCalendarStart
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

        editTitle.setText(title);
        editType.setText(type);
        if (startDate == null){
            editDate.setText("Set Start Date");
        }
        else editDate.setText(startDate);
        if (endDate == null){
            editDate2.setText("Set End Date");
        }
        else editDate2.setText(endDate);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessment_details_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.assessmentDelete){
            for(assessment a:Repository.getmAllAssessments()){
                if(a.getAssessmentID()==assessmentID)currentAssessment=a;
            }
            Repository.delete(currentAssessment);
            Toast.makeText(assessmentDetails.this, currentAssessment.getAssessTitle() + " was deleted",Toast.LENGTH_LONG).show();
            assessmentDetails.this.finish();
        }

        if (item.getItemId()==R.id.assessmentSave) {
            assessment Assessment;
            if (assessmentID == -1) {
                if (Repository.getmAllAssessments().size() == 0)
                    assessmentID = 1;
                else
                    assessmentID = Repository.getmAllAssessments().get(Repository.getmAllAssessments().size() - 1).getAssessmentID() + 1;
                Assessment = new assessment(assessmentID, editDate.getText().toString(), editDate2.getText().toString(),
                        editType.getText().toString(), editTitle.getText().toString(), courseID);
                Repository.insert(Assessment);
                this.finish();
            } else {
                Assessment = new assessment(assessmentID, editDate.getText().toString(), editDate2.getText().toString(),
                        editType.getText().toString(), editTitle.getText().toString(), courseID);
                Repository.update(Assessment);
                this.finish();
            }
            return true;
        }
        if (item.getItemId() == R.id.aStartDateNotify) {
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
            Intent intent = new Intent(assessmentDetails.this, MyReceiver3.class);
            intent.putExtra("key3", title +" starts today!");
            PendingIntent sender=PendingIntent.getBroadcast(assessmentDetails.this,++MainActivity.numAlert, intent,PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger,sender);
            return true;
        }

        if (item.getItemId() == R.id.aEndDateNotify) {
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
            Intent intent = new Intent(assessmentDetails.this, MyReceiver4.class);
            intent.putExtra("key4", title +" ends today!");
            PendingIntent sender=PendingIntent.getBroadcast(assessmentDetails.this,++MainActivity.numAlert, intent,PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger,sender);
            return true;
        }


        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}