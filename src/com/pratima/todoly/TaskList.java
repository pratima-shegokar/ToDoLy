package com.pratima.todoly;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public void addTask(String taskName, String completionDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime completionDate = LocalDateTime.now().plusDays(1);
        if(completionDateString != null && completionDateString.trim().length() != 0) {
            try {
                completionDate = LocalDateTime.parse(completionDateString, formatter);
            } catch (DateTimeParseException e) {
                completionDate = LocalDateTime.now().plusDays(1);
            }
        }
        tasksList.add(new Task(taskName, completionDate, LocalDateTime.now()));
    }

    public void printAllTasks() {
        for (Task aTask:tasksList) {
            System.out.println(aTask);
        }
    }
    public boolean removeTask(String taskName){
        return tasksList.remove(new Task(taskName));
    }

    public boolean markFinished(String taskName) {
        int taskIndex = tasksList.indexOf(new Task(taskName));
        if(taskIndex != -1){
            tasksList.get(taskIndex).markFinished();
            return true;
        }
        return false;
    }
}
