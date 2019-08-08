package com.example.listadetarefasoficial.helper;

import com.example.listadetarefasoficial.model.Task;

import java.util.List;

public interface ITaskDAO {

    boolean insert(Task task);

    boolean update(Task task);

    boolean remove(Task task);

    List<Task> list();

}
