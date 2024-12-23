package com.example.exam1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassAdapter extends  RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{

    private List<ClassItem> classList;

    public ClassAdapter(List<ClassItem> classList) {
        this.classList = classList;
    }

    @NonNull
    @Override

    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassItem currentItem = classList.get(position);
        holder.className.setText(currentItem.getName());
        holder.classTime.setText(currentItem.getTime());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView className;
        public TextView classTime;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.class_name);
            classTime = itemView.findViewById(R.id.class_time);
        }
    }
}
