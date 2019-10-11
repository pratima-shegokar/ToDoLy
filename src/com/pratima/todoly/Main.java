package com.pratima.todoly;

public class Main {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        TodoEngine todoEngine = new TodoEngine();
        todoEngine.initializeEngine(fileManager);
        todoEngine.displayMenu();
    }
}
