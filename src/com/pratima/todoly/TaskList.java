package com.pratima.todoly;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private List<Task> tasksList;
    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public void addTask(String taskName, String completionDateString) {
        LocalDateTime completionDate;
        //TODO:Parsing datetime to be done by Task
        try {
            completionDate = LocalDateTime.parse(completionDateString, formatter);
        } catch (DateTimeParseException e) {
            completionDate = LocalDateTime.now().plusDays(1);
        }
        tasksList.add(new Task(taskName, completionDate, LocalDateTime.now()));
    }
    //TODO: Write getTask method
    public void printAllTasks() {
        for (Task aTask:tasksList) {
            System.out.println(aTask);
        }
    }
    public boolean removeTask(String taskName){
        return tasksList.remove(new Task(taskName));
    }

    public boolean markFinished(String taskName) {
        //TODO: Use getTask
        int taskIndex = tasksList.indexOf(new Task(taskName));
        if(taskIndex != -1){
            tasksList.get(taskIndex).markFinished();
            return true;
        }
        return false;
    }

    public boolean updateTask(String taskName) {
        //TODO: Use getTask
        Scanner input = new Scanner(System.in);
        int taskIndex = tasksList.indexOf(new Task(taskName));
        if(taskIndex != -1){
            Task currentTask = tasksList.get(taskIndex);
            System.out.print("Enter new title(default:"
                    + currentTask.getTaskName() + "): ");
            String newTaskName = input.nextLine().trim();
            if(newTaskName.length() == 0) {
                newTaskName = currentTask.getTaskName();
            }
            System.out.print("Enter new finish date(default:"
                    + currentTask.getCompletionTime() + ")(format:yyyy-MM-dd HH:mm): ");
            String newTaskCompletionTimeString = input.nextLine().trim();
            LocalDateTime newTaskCompletionTime;
            //TODO:Completion Date formatting should be delegated to Task
            try {
                newTaskCompletionTime =
                        LocalDateTime.parse(newTaskCompletionTimeString, formatter);
            } catch (DateTimeParseException e) {
                newTaskCompletionTime = currentTask.getCompletionTime();
            }
            currentTask.setTaskName(newTaskName);
            //TODO:String to be passed to setCompletionTime
            currentTask.setCompletionTime(newTaskCompletionTime);
            return true;
        }
        return false;
    }
}
