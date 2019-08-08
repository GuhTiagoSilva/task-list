package com.example.listadetarefasoficial.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefasoficial.R;
import com.example.listadetarefasoficial.model.Task;

import java.util.List;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks.ViewHolder> {

    private List<Task>taskList;

    public AdapterTasks(List<Task>taskList){
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tasks, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.task.setText(task.getTaskDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView task;

        public ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.textTask);

        }
    }


}
