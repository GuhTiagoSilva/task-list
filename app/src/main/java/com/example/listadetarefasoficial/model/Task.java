package com.example.listadetarefasoficial.model;

import java.io.Serializable;

public class Task implements Serializable {

    private Integer taskId;
    private String taskDescription;

    public Task() {
    }
    public Task(String taskDescription){
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription(){
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }
    public Integer getTaskId(){
        return taskId;
    }
    public void setTaskId(Integer id){
        this.taskId = id;
    }

}
