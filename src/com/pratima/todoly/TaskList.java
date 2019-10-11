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
    public TaskList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }


    public List<Task> getTasksList() {
        return tasksList;
    }

    public void addTask(String taskName, String completionDateString) {
        LocalDateTime completionDate;
        try {
            completionDate = LocalDateTime.parse(completionDateString, formatter);
        } catch (DateTimeParseException e) {
            completionDate = LocalDateTime.now().plusDays(1);
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

    public boolean updateTask(String taskName) {
        Scanner input = new Scanner(System.in);
        Task taskToUpdate = getTask(taskName);
        if(taskToUpdate != null){
            System.out.print("Enter new title(default:"
                    + taskToUpdate.getTaskName() + "): ");
            String newTaskName = input.nextLine().trim();
            if(newTaskName.length() == 0) {
                newTaskName = taskToUpdate.getTaskName();
            }
            System.out.print("Enter new finish date(default:"
                    + taskToUpdate.getCompletionTime() + ")(format:yyyy-MM-dd HH:mm): ");
            String newTaskCompletionTimeString = input.nextLine().trim();
            LocalDateTime newTaskCompletionTime;
            //TODO:Completion Date formatting should be delegated to Task
            try {
                newTaskCompletionTime =
                        LocalDateTime.parse(newTaskCompletionTimeString, formatter);
            } catch (DateTimeParseException e) {
                newTaskCompletionTime = taskToUpdate.getCompletionTime();
            }
            taskToUpdate.setTaskName(newTaskName);
            taskToUpdate.setCompletionTime(newTaskCompletionTime);
            return true;
        }
        return false;
    }

    public Task getTask(String taskName){
        int taskIndex = tasksList.indexOf(new Task(taskName));
        if(taskIndex != -1){
            return tasksList.get(taskIndex);
        }
        return null;
    }
}
