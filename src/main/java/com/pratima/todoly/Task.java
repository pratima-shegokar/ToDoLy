package com.pratima.todoly;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/***
 * The class Task implements Serializable interface.
 * Also It will do getter and setter for fields.
 * Override some methods according to implementation.
 * @author pratimashegokar
 */
public class Task implements Serializable {
    private String project;
    private String taskName;
    private LocalDateTime completionTime;
    private LocalDateTime creationTime;
    private boolean isFinished;
    private static final int PRIME_FOR_HASH_CODE = 14389;

    //Constructor for Task
    public Task(String project, String taskName, LocalDateTime completionTime, LocalDateTime creationTime) {
        this.project = project;
        this.taskName = taskName;
        this.completionTime = completionTime;
        this.creationTime = creationTime;
        this.isFinished = false;
    }
    //Constructor for Task
    public Task(String taskName) {
        this.taskName = taskName;
        this.completionTime = LocalDateTime.MAX;
        this.creationTime = LocalDateTime.now();
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
    /**
     * After task marked.
     * @return boolean value.
     */
    public boolean isFinished() {
        return isFinished;
    }
    /**
     * After task marked.
     * @return TRUE value.
     */
    public boolean markFinished() {
        return isFinished = true;
    }
    /**
     * Overriding toString() method
     * Returns a String representation of this ToDoList.
     * Specifically, "TODOLY"
     */
    @Override
    public String toString() {
        return "Task{" +
                "project='" + project + '\'' +
                ", taskName='" + taskName + '\'' +
                ", completionTime=" + completionTime +
                ", creationTime=" + creationTime +
                ", isFinished=" + isFinished +
                '}';
    }

    /**
     * This function print pretty way task info.
     * @return taskName with time or message.
     */

    public String prettyPrint() {
        String returnValue = taskName + " - ";
        if(!isFinished) {
            returnValue += timeLeft(completionTime);
        }
        else {
            returnValue += " the task is completed!";
        }
        return returnValue;
    }
    /**
     * Overriding equals() method
     * @param obj In the method using object of task
     * @return taskName
     */
    @Override
    public boolean equals(Object obj) {
        Task aTask = (Task) obj;
        return taskName.equalsIgnoreCase(aTask.getTaskName());
    }

    /**
     * Overriding hashcode() method
     * @return hashcode value for the object.
     */
    @Override
    public int hashCode() {
        return PRIME_FOR_HASH_CODE * taskName.length();
    }

    /**
     * This function calculate time remaining for complete the task.
     * @param dateTime Encapsulate a Date and Time. The datetime is stored as a
     *                 timestamp and may be persisted as a timestamp.
     *
     * See Also:
     * @return Hours which left for the task.
     */
    private String timeLeft(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        long until = now.until(dateTime, ChronoUnit.DAYS);
        if (until <= 1) {
            return now.until(dateTime, ChronoUnit.HOURS) + " hours left for this task.";
        }
        return until + " days left for this task.";
    }


}
