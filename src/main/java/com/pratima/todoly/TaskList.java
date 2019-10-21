package com.pratima.todoly;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/***
 *  Provides a very simple ToDoList(todoly) with Project,TaskName,Date and time.
 *  New items can be added at the end and removed from any other position.
 *  Update the existing data field in the 'todoly'. Mark the finished task.
 *  Save and exit the task.
 * @author pratimashegokar
 * @version 1.0.0
 */
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

    /***
     *
     * @return getter for TasksList
     */
    public List<Task> getTasksList() {
        return tasksList;
    }

    /***
     * Adds task to the list. This method also converts the string date to LocalDateTime
     * @param taskName The name of the task to be added.
     * @param completionDateString String which contains date and time for the end date.
     * @param project The name of the project.
     */
    public void addTask(String taskName, String completionDateString, String project) {
        LocalDateTime completionDate;
        try {
            completionDate = LocalDateTime.parse(completionDateString, formatter);
        } catch (DateTimeParseException e) {
            completionDate = LocalDateTime.now().plusDays(1);
        }
        tasksList.add(new Task(project, taskName, completionDate, LocalDateTime.now()));
    }

    /***
     * Print all tasks from the list
     */
    public void printAllTasks() {
        for (Task aTask:tasksList) {
            System.out.println(aTask);
        }
    }

    /***
     * Remove specific task from the list.
     * @param taskId The id of the task that want to remove from list.
     * @return boolean value whether task removed or not.
     */
    public Task removeTask(int taskId){
        Task taskToDelete = getTask(taskId);
        if(tasksList.remove(taskToDelete)) {
            return taskToDelete;
        }
        return null;
    }

    /***
     * Mark specific task from the list as Finished.
     * @param taskId The name of the task that want to Marked.
     * @return boolean value whether task marked or not.
     */
    public boolean markFinished(int taskId) {
        Task taskToBeMarkedAsFinished = getTask(taskId);
        if (taskToBeMarkedAsFinished != null) {
            return taskToBeMarkedAsFinished.markFinished();
        }
        return false;
    }

    /***
     * Update specific task from the list which name is matching with taskName.
     * or just update one field.
     * @param taskId The name of the task that which should update.
     * @return boolean value whether task updated or not.
     */
    public boolean updateTask(int taskId) {
        Scanner input = new Scanner(System.in);
        Task taskToUpdate = getTask(taskId);
        if(taskToUpdate != null){
            //Update taskName
            System.out.print("Enter new title(default:" + taskToUpdate.getTaskName() + "): ");
            String newTaskName = input.nextLine().trim();
            if(newTaskName.length() == 0) {
                newTaskName = taskToUpdate.getTaskName();
            }
            //Update Project
            System.out.print("Enter new project(default:" + taskToUpdate.getProject() + "): ");
            String newProject = input.nextLine().trim();
            if(newProject.length() == 0) {
                newProject = taskToUpdate.getProject();
            }
            //Update Date and Time
            System.out.print("Enter new finish date(default:" + taskToUpdate.getCompletionTime() + ")(format:yyyy-MM-dd HH:mm): ");
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
            taskToUpdate.setProject(newProject);
            return true;
        }
        return false;
    }

    /***
     * Get task by taskName in the list.
     * @param taskId the name of the task which we are finding in the list.
     * @return Task if found else null
     */
    public Task getTask(int taskId){
        return tasksList.stream()
                .filter(task -> taskId == task.getTaskId())
                .findAny()
                .orElse(null);
    }

    public int findMaxTaskCount() {
        return tasksList.stream()
                .max(Comparator.comparing(Task::getTaskId))
                .map(Task::getTaskId)
                .orElse(0);
    }
}
