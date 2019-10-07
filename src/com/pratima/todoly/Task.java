package com.pratima.todoly;

import java.time.LocalDateTime;

public class Task {
    private String taskName;
    private LocalDateTime completionTime;
    private LocalDateTime creationTime;

    public Task(String taskName, LocalDateTime completionTime, LocalDateTime creationTime) {
        this.taskName = taskName;
        this.completionTime = completionTime;
        this.creationTime = creationTime;
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

    @Override
    public String toString() {
        return "taskName='" + taskName + '\'' +
                ", completionTime=" + completionTime +
                ", creationTime=" + creationTime;
    }
}
