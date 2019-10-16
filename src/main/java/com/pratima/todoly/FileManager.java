package com.pratima.todoly;

import java.io.*;
import java.util.ArrayList;

/***
 * Reads and Writes to/from file.
 * Uses object serialization format to write and read.
 * This also creates singleton for reuse of the file handlers.
 * @author pratimashegokar
 * @version 1.0.0
 *
 */
public class FileManager {
    private static FileManager single_instance = null;
    private static final String FILE_NAME="todoly";
    public static FileManager getInstance() {
        if (single_instance == null) {
            single_instance = new FileManager();
        }
        return single_instance;
    }

    /***
     * Writes to a file.
     * @param taskList list of tasks to write.
     */
    public void write(TaskList taskList) {
        try {
            new ObjectOutputStream(new FileOutputStream(FILE_NAME)).writeObject(taskList.getTasksList());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing the file. Cannot save data.");
        }
    }

    /***
     * Reads tasklist from the file.
     * @return Reads and returns a list of tasks that were written earlier.
     */
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
