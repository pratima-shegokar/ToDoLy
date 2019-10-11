package com.pratima.todoly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList testTaskList = new TaskList();

    @BeforeEach
    void simulateReadFromFile(){
        testTaskList.addTask("testTask", "2019-11-01 00:00");
        testTaskList.addTask("dummyTask", "");
        testTaskList.addTask("moreTask", "2019-12-01 00:00");
    }

    @Test
    void shouldAddToList() {
        testTaskList.addTask("someOtherTestTask", "2019-11-02 00:00");
        assertEquals(4, testTaskList.getTasksList().size());
        Task testTask = testTaskList.getTask("someOtherTestTask");
        assertEquals("someOtherTestTask", testTask.getTaskName());
        assertEquals(LocalDateTime.of(2019, 11, 2, 0, 0), testTask.getCompletionTime());
        assertFalse(testTask.isFinished());
    }

    @Test
    void shouldMarkATaskFinished() {
        boolean done = testTaskList.markFinished("testTask");
        assertTrue(done);
        Task testTask = testTaskList.getTask("testTask");
        assertTrue(testTask.isFinished());
    }

    @Test
    void shouldRemoveTaskFromList() {
        testTaskList.removeTask("moreTask");
        assertEquals(2, testTaskList.getTasksList().size());
        assertNull(testTaskList.getTask("moreTask"));
    }

    @Test
    void shouldPrintPretty() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        testTaskList.printAllTasks();
        assertEquals("testTask - 21 days left for this task.\n" +
                "dummyTask - 23 hours left for this task.\n" +
                "moreTask - 51 days left for this task.\n", outContent.toString());
    }
}