package com.example.listadetarefasoficial.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.listadetarefasoficial.R;
import com.example.listadetarefasoficial.adapter.AdapterTasks;
import com.example.listadetarefasoficial.helper.DbHelper;
import com.example.listadetarefasoficial.helper.RecyclerItemClickListener;
import com.example.listadetarefasoficial.helper.TaskDAO;
import com.example.listadetarefasoficial.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTasks;
    private AdapterTasks tasks;
    private List<Task> taskList = new ArrayList<>();
    private Task selectedTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerTasks = findViewById(R.id.recyclerTasks);

        recyclerTasks.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerTasks, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Task selectedTask = taskList.get(position);
                Intent i = new Intent (MainActivity.this, AddTaskActivity.class);
                i.putExtra("selectedTask", selectedTask);
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {

                selectedTask = taskList.get(position);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Alerta");
                alertDialog.setMessage("Deseja realmente excluir a tarefa:  " + selectedTask.getTaskDescription() + " ? ");
                alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                        if(taskDAO.remove(selectedTask)){
                            loadTasksList();
                            Toast.makeText(getApplicationContext(), "Sucesso ao excluir a tarefa ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.setNegativeButton("Não",null);

                alertDialog.create();
                alertDialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(i);

            }
        });
    }


    @Override
    protected void onStart() {
        this.loadTasksList();
        super.onStart();

    }

    public void loadTasksList() {
        TaskDAO dao = new TaskDAO(getApplicationContext());
        taskList = dao.list();

        tasks = new AdapterTasks(taskList);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerTasks.setLayoutManager(layout);
        recyclerTasks.setHasFixedSize(true);
        recyclerTasks.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerTasks.setAdapter(tasks);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
