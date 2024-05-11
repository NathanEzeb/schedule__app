package com.example.try2.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.try2.R;
import com.example.try2.adapters.termAdapter;
import com.example.try2.database.repository;
import com.example.try2.entities.course;
import com.example.try2.entities.term;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class editTerms extends AppCompatActivity {
private String name;
private String current;
    private repository Repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_terms);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.termRecycler);
        Repository= new repository(getApplication());
        List<term> allTerms=Repository.getmAllTerms();
        final termAdapter TermAdapter = new termAdapter(this);
        recyclerView.setAdapter(TermAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TermAdapter.setTerms(allTerms);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home) {
            this.finish();
            return true;
        }
        return true;
    }
    public void goToCourseList(View v) {
        Intent i = new Intent(this, courseList.class);
        i.putExtra("test", "information sent");
        startActivity(i);
    }
    @Override
    protected void onResume() {

        super.onResume();
        List<term> allTerms=Repository.getmAllTerms();
        RecyclerView recyclerView=findViewById(R.id.termRecycler);
        final termAdapter TermAdapter=new termAdapter(this);
        recyclerView.setAdapter(TermAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TermAdapter.setTerms(allTerms);

        //Toast.makeText(ProductDetails.this,"refresh list",Toast.LENGTH_LONG).show();
    }
}