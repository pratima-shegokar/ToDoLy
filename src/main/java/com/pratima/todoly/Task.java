package com.pratima.todoly;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
    private static AtomicInteger count = new AtomicInteger(0);
    private int taskId;

    //Constructor for Task
    public Task(String project, String taskName, LocalDateTime completionTime, LocalDateTime creationTime) {
        this.taskId = count.incrementAndGet();
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

    public static void initializeCounter(int initialValue) {
        count = new AtomicInteger(initialValue);
    }

    public String getProject() {
        return project;
    }

    public int getTaskId() {
        return taskId;
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
        if(isFinished)
            return String.format(Color.GREEN + "%-3s %-20s %-50s %s" + Color.DEFAULT, taskId, project, taskName, timeLeft(completionTime));
        return String.format(Color.RED + "%-3s %-20s %-50s %s" + Color.DEFAULT, taskId, project, taskName, timeLeft(completionTime));
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
        if(now.isAfter(dateTime)) {
            return dateTime.until(dateTime, ChronoUnit.HOURS) + " hours elapsed.";
        }
        long until = now.until(dateTime, ChronoUnit.DAYS);
        if (until <= 1) {
            return now.until(dateTime, ChronoUnit.HOURS) + " hours left for this task.";
        }
        return until + " days left for this task.";
    }
}
