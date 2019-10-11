package com.pratima.todoly;

import java.util.Scanner;
import static java.lang.System.exit;

public class TodoEngine {

    private static final Scanner STDIN = new Scanner(System.in);
    private TaskList taskList;

    public TodoEngine() {
        this.taskList = new TaskList();
    }

    public void displayMenu() {
        String chosenOption;
        do {
            showMenu();
            chosenOption = STDIN.nextLine().trim();
            switch (chosenOption) {
                case "1":
                    taskList.printAllTasks();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    removeTask();
                    break;
                case "4":
                    updateTask();
                    break;
                case "5":
                    markAsDone();
                    break;
                case "6":
                    FileManager.getInstance().write(taskList);
                    System.out.println("Thank you for using ToDoLy!");
                    exit(0);
                default:
                    System.out.println("Invalid Option Chosen!");
            }
        } while (true);
    }

    private void updateTask() {
        System.out.print("Enter task name to update: ");
        String taskName = STDIN.nextLine();
        if(taskList.updateTask(taskName)) {
            System.out.println("Task Updated.");
        }
        else {
            System.out.println("Task does not exists!");
        }
    }

    private void markAsDone() {
        System.out.print("Enter task name to mark it finished: ");
        String taskName = STDIN.nextLine();
        if(taskList.markFinished(taskName)) {
            System.out.println("Yay! You completed the task.");
        }
        else {
            System.out.println("Task does not exists!");
        }
    }

    private void addTask() {
        System.out.print("Enter task name: ");
        String taskName = STDIN.nextLine();
        System.out.print("Enter finish date(yyyy-MM-dd HH:mm)(default:24hrs): ");
        String finishDate = STDIN.nextLine();
        taskList.addTask(taskName, finishDate);
        System.out.println("Task added successfully");
    }
    private void removeTask() {
        System.out.println("Task name to delete: ");
        String taskName = STDIN.nextLine();
        if (taskList.removeTask(taskName)) {
            System.out.println("Task Deleted");
        }
        else {
            System.out.println("No matching task found");
        }

    }

    public void showMenu() {
        System.out.println("**** Welcome to the ToDoLy ****");
        System.out.println("1.View All Task");
        System.out.println("2.Add New Task");
        System.out.println("3.Remove Task");
        System.out.println("4.Update Task");
        System.out.println("5.Mark Task As Done");
        System.out.println("6.Exit");
    }

    public void initializeEngine() {
        taskList = FileManager.getInstance().read();
    }
}
