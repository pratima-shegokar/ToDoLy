package com.pratima.todoly;

import java.util.Scanner;
import static java.lang.System.exit;

/***
 *  Provides a very simple Menu Driven options for ToDoList(todoly).
 *  choose the options from the menu it can show the operation you can perform on the list.
 *  **** Welcome to the ToDoLy ****
 *  1.View All Task
 *  2.Add New Task
 *  3.Remove Task
 *  4.Update Task");
 *  5.Mark Task As Done");
 *  6.Exit"
 * @author pratimashegokar
 * @version 1.0.0
 */

public class TodoEngine {

    private static final Scanner STDIN = new Scanner(System.in);
    private TaskList taskList;
    /**
     * Constructor for class
     */
    public TodoEngine() {
        this.taskList = new TaskList();
    }
    /**
     * This function is used for displaying Menu.
     */
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
                    exit(0);//TODO:Exit the code
                default:
                    System.out.println("Invalid Option Chosen!");
            }
        } while (true);//catch input mismatch exception
    }

    /**
     * This function asks input for Update field
     */
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

    /**
     * This function asks input for mark don the task.
     */
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

    /**
     * This function asks input for Add new field.
     */
    private void addTask() {
        System.out.print("Enter task name: ");
        String taskName = STDIN.nextLine();
        System.out.println("Enter project name: ");
        String project = STDIN.nextLine();
        System.out.print("Enter finish date(yyyy-MM-dd HH:mm)(default:24hrs): ");
        String finishDate = STDIN.nextLine();
        taskList.addTask(taskName, finishDate, project);
        System.out.println("Task added successfully");
    }

    /**
     * This function asks input for remove specific task.
     */
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

    /**
     * This function just print menu when it get call.
     */
    public void showMenu() {
        System.out.println("**** Welcome to the ToDoLy ****");
        System.out.println("1.View All Task");
        System.out.println("2.Add New Task");
        System.out.println("3.Remove Task");
        System.out.println("4.Update Task");
        System.out.println("5.Mark Task As Done");
        System.out.println("6.Exit");
    }

    /**
     * Get Singleton instance of file and read.
     */
    public void initializeEngine() {
        taskList = FileManager.getInstance().read();
    }
}
