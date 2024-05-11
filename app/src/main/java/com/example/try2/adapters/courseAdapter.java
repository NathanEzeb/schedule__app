package com.example.try2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.try2.R;
import com.example.try2.UI.courseDetails;
import com.example.try2.entities.course;

import java.util.List;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.courseViewHolder> {
    private List<course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;


    class courseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;
        private final TextView courseItemView2;

        private courseViewHolder(View itemView){
            super(itemView);
            courseItemView = itemView.findViewById(R.id.textview6);
            courseItemView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                final course current = mCourses.get(position);
                Intent intent = new Intent(context, courseDetails.class);
                intent.putExtra("courseID", current.getCourseID());
                intent.putExtra("name", current.getCourseName());
                intent.putExtra("insName", current.getInstructorName());
                intent.putExtra("insEmail", current.getInstructorEmail());
                intent.putExtra("insPhone", current.getInstructorPhone());
                intent.putExtra("status", current.getCourseStatus());
                intent.putExtra("termID", current.getTermID());
                intent.putExtra("courseNote", current.getCourseNote());
                intent.putExtra("endDate", current.getEndDate());
                intent.putExtra("startDate", current.getStartDate());
                context.startActivity(intent);
            }
        });
    }}
    public courseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    public courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
                return new courseViewHolder(itemView);
    }
    public void onBindViewHolder(@NonNull courseViewHolder holder, int position) {
        if(mCourses!=null){
            course current=mCourses.get(position);
            String name=current.getCourseName();
            int courseID= current.getCourseID();
            holder.courseItemView.setText(name);
            holder.courseItemView2.setText(Integer.toString(courseID));
        }
        else{
            holder.courseItemView.setText("No course name");
            holder.courseItemView2.setText("No course id");
        }
    }
    public void setCourses (List<course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }
    public int getItemCount() {

        if(mCourses!=null) return mCourses.size();
        else return 0;
    }
}

