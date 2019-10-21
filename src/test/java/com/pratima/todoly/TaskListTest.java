package com.pratima.todoly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList testTaskList = new TaskList();

    @BeforeEach
    void simulateReadFromFile(){
        testTaskList.addTask( "testTask", "2019-11-01 00:00", "project1");
        testTaskList.addTask("dummyTask", "", "testProject");
        testTaskList.addTask("moreTask", "2019-12-01 00:00", "testProject");
    }

    @Test
    void shouldAddToListTest() {
        testTaskList.addTask("someOtherTestTask", "2019-11-02 00:00", "project");
        assertEquals(4, testTaskList.getTasksList().size());
    }

    @Test
    void shouldMarkATaskFinishedTest() {
        boolean done = testTaskList.markFinished(1);
        assertTrue(done);
        Task testTask = testTaskList.getTask(1);
        assertTrue(testTask.isFinished());
    }

    @Test
    void shouldRemoveTaskFromListTest() {
        Task task = testTaskList.removeTask(3);
        assertFalse(testTaskList.getTasksList().contains(task));
    }

    @Test
    void shouldPrintPrettyTest() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        testTaskList.printAllTasks();
        assertTrue(outContent.toString().contains("moreTask"));
    }
}