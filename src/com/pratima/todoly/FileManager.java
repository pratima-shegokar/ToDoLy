package com.pratima.todoly;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static FileManager single_instance = null;
    private static final String FILE_NAME="todoly";
    public static FileManager getInstance() {
        if (single_instance == null) {
            single_instance = new FileManager();
        }
        return single_instance;
    }
    public void write(TaskList taskList) {
        try {
            new ObjectOutputStream(new FileOutputStream(FILE_NAME)).writeObject(taskList.getTasksList());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing the file. Cannot save data.");
        }
    }
    public TaskList read() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = (ArrayList<Task>)new ObjectInputStream(new FileInputStream(FILE_NAME)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file.");
        }
        return new TaskList(tasks);
    }
}
