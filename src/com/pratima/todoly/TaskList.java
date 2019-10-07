package com.pratima.todoly;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public void addTask(String taskName, String completionDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime completionDate = LocalDateTime.parse(completionDateString, formatter);
        tasksList.add(new Task(taskName, completionDate, LocalDateTime.now()));
    }

    public void printAllTasks() {
        for (Task aTask:tasksList) {
            System.out.println(aTask);
        }
    }
}
