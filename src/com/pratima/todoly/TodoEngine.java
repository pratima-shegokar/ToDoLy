package com.pratima.todoly;

import java.util.Scanner;
import static java.lang.System.exit;

public class TodoEngine {
    private Scanner input = new Scanner(System.in);
    TaskList taskList = new TaskList();
    public void displayMenu() {
        String chosenOption;
        do {
            showMenu();
            chosenOption = input.nextLine().trim();
            switch (chosenOption) {
                case "1":
                    taskList.printAllTasks();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    System.out.println("3");
                    break;
                case "4":
                    System.out.println("4");
                    break;
                case "5":
                    System.out.println("5");
                    break;
                case "6":
                    System.out.println("6");
                    break;
                case "7":
                    System.out.println("Thank you for using ToDoLy!");
                    exit(0);
                default:
                    System.out.println("Invalid Option Chosen!");
            }
        } while (true);
    }

    private void addTask() {
        System.out.print("Enter task name: ");
        String taskName = input.nextLine();
        System.out.print("Enter finish date(yyyy-MM-dd HH:mm): ");
        String finishDate = input.nextLine();
        taskList.addTask(taskName, finishDate);
        System.out.println("Task added successfully");
    }

    private void showMenu() {
        System.out.println("**** Welcome to the ToDoLy ****");
        System.out.println("1.View All Task");
        System.out.println("2.Add New Task");
        System.out.println("3.Remove Task");
        System.out.println("4.Edit Task Name");
        System.out.println("5.Change Date of Task");
        System.out.println("6.Change Time of Task");
        System.out.println("7.Exit");
    }
}
