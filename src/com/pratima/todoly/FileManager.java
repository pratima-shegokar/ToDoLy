package com.pratima.todoly;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final String FILE_NAME="todoly";
    private FileWriter file;
    public FileManager() {
        try {
            file = new FileWriter(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
