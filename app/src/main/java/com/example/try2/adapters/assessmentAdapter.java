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
import com.example.try2.UI.assessmentDetails;
import com.example.try2.UI.courseList;
import com.example.try2.entities.assessment;

import java.util.List;

public class assessmentAdapter extends RecyclerView.Adapter<assessmentAdapter.assessmentViewHolder> {
    private List<assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;



    class assessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;

        private assessmentViewHolder(View itemView){
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textview13);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //what happens when you click on an item? coded here
                    int position = getAdapterPosition();

                    final assessment current = mAssessments.get(position);
                    //going to course details
                    Intent i = new Intent(context, assessmentDetails.class);
                    //add more fields later on
                    i.putExtra("assessmentID", current.getAssessmentID());
                    i.putExtra("title", current.getAssessTitle());
                    i.putExtra("courseID", current.getCourseID());
                    i.putExtra("endDate", current.getEndDate());
                    i.putExtra("startDate", current.getStartDate());
                    i.putExtra("asType", current.getType());
                    context.startActivity(i);
                }
            });
        }}
    public assessmentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        }
    @NonNull
    @Override
    public assessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new assessmentViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull assessmentViewHolder holder, int position) {
        if(mAssessments != null){
            assessment current=mAssessments.get(position);
            String name=current.getAssessTitle();
            holder.assessmentItemView.setText(name);
        }
        else{
            holder.assessmentItemView.setText("No course name");
        }
    }
    public void setAssessments (List<assessment> assessments){
        mAssessments = assessments;
        notifyDataSetChanged();
    }
    public int getItemCount() {

        if(mAssessments!=null)
            return mAssessments.size();
        else return 0;
    }
}

