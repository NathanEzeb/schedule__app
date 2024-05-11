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
import com.example.try2.UI.courseList;
import com.example.try2.entities.term;

import java.util.List;

public class termAdapter extends RecyclerView.Adapter<termAdapter.termViewHolder>{
    public List<term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public termAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    public class termViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
        public termViewHolder(@NonNull View itemView) {
            super(itemView);
            //might need to change textView2 because thats where it puts stuff
            termItemView=itemView.findViewById(R.id.textview4);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //what happens when you click on an item? coded here
                    int position = getAdapterPosition();

                    final term current = mTerms.get(position);
                    //going to course details
                    Intent i = new Intent(context, courseList.class);
                    //add more fields later on
                    i.putExtra("termID", current.getTermID());
                    i.putExtra("title", current.getTermTitle());
                    i.putExtra("startDate", current.getStartDate());
                    i.putExtra("endDate", current.getEndDate());
                    i.putExtra("alert", current.getActiveCourses());
                    context.startActivity(i);
                }
            });
        }

    }

    @NonNull
    @Override
    public termAdapter.termViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_item, parent, false);
        return new termViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull termAdapter.termViewHolder holder, int position) {
        //this is where we put what displays on recycler view
        if (mTerms != null) {
            term current = mTerms.get(position);
            String name= current.getTermTitle();
            holder.termItemView.setText(name);
        }
        else {
            holder.termItemView.setText("No term name");
        }
        }

    @Override
    public int getItemCount() {
        if (mTerms!=null) {
            return mTerms.size();
        }
        else return 0;
    }

    public void setTerms(List<term> term){
        mTerms = term;
        notifyDataSetChanged();
    }

}
