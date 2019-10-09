package com.pratima.todoly;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Task {
    private String taskName;
    private LocalDateTime completionTime;
    private LocalDateTime creationTime;
    private boolean isFinished;
    private static final int PRIME_FOR_HASH_CODE = 14389;

    public Task(String taskName, LocalDateTime completionTime, LocalDateTime creationTime) {
        this.taskName = taskName;
        this.completionTime = completionTime;
        this.creationTime = creationTime;
        this.isFinished = false;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.completionTime = LocalDateTime.MAX;
        this.creationTime = LocalDateTime.now();
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

    public boolean isFinished() {
        return isFinished;
    }

    public void markFinished() {
        isFinished = true;
    }

    @Override
    public String toString() {
        String returnValue = taskName + " - ";
        if(!isFinished) {
            returnValue += timeLeft(completionTime);
        }
        else {
            returnValue += " the task is completed!";
        }
        return returnValue;
    }

    @Override
    public boolean equals(Object obj) {
        Task aTask = (Task) obj;
        return taskName.equalsIgnoreCase(aTask.getTaskName());
    }

    @Override
    public int hashCode() {
        return PRIME_FOR_HASH_CODE * taskName.length();
    }

    private String timeLeft(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        long until = now.until(dateTime, ChronoUnit.DAYS);
        if (until <= 1) {
            return now.until(dateTime, ChronoUnit.HOURS) + " hours left for this task.";
        }
        return until + " days left for this task.";
    }
}
