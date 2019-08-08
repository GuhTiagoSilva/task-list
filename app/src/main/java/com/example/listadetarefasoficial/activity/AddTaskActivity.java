package com.example.listadetarefasoficial.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefasoficial.R;
import com.example.listadetarefasoficial.helper.TaskDAO;
import com.example.listadetarefasoficial.model.Task;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {

    private EditText textUser;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        textUser = findViewById(R.id.textUser);

        currentTask = (Task) getIntent().getSerializableExtra("selectedTask");

        if (currentTask != null) {
            textUser.setText(currentTask.getTaskDescription());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {


            case R.id.saveItem:

                TaskDAO taskDAO = new TaskDAO(getApplicationContext());

                String taskName = textUser.getText().toString();

                if (currentTask != null) {//edition
                    if (!taskName.isEmpty()) {
                        Task task = new Task();
                        task.setTaskDescription(taskName);
                        task.setTaskId(currentTask.getTaskId());

                        if(taskDAO.update(task)){
                            Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar  tarefa", Toast.LENGTH_SHORT).show();
                        }

                    }



                } else {
                    if (!taskName.isEmpty()) {

                        Task task = new Task();
                        task.setTaskDescription(taskName);

                        if (taskDAO.insert(task)) {
                            Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar  tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
