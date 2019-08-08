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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        textUser = findViewById(R.id.textUser);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String taskName = textUser.getText().toString();
        switch (item.getItemId()) {


            case R.id.saveItem:
                TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                Task task = new Task();
                task.setTaskDescription(taskName);
                taskDAO.insert(task);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
