package com.example.listadetarefasoficial.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefasoficial.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDAO {

    private SQLiteDatabase write;// write in our tables
    private SQLiteDatabase read; //read our tables

    public TaskDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        write = helper.getWritableDatabase(); // allow us to save on database
        read = helper.getReadableDatabase(); // allow us read data on table
    }

    @Override
    public boolean insert(Task task) {

        ContentValues cv = new ContentValues();
        cv.put("name", task.getTaskDescription());

        try {

            write.insert(DbHelper.TABLE_NAME, null, cv);
            Log.i("Info Db: ", "Data inserted on table");
        } catch (Exception e) {
            Log.i("False: ", "Error saving data " + e.getMessage());
            return false;
        }


        return true;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public boolean remove(Task task) {
        return false;
    }

    @Override
    public List<Task> list() {
        String sql = "SELECT * FROM " + DbHelper.TABLE_NAME;

        List<Task> taskItems = new ArrayList<>();

        Cursor cursor = read.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            Task task = new Task();

            Integer id = cursor.getInt(cursor.getColumnIndex("id_task"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            task.setTaskId(id);
            task.setTaskDescription(name);

            taskItems.add(task);

        }


        return taskItems;
    }
}
