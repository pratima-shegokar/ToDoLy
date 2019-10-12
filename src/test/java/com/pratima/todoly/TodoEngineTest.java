package com.pratima.todoly;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TodoEngineTest {
    private TodoEngine todoEngineTest = new TodoEngine();

    @Test
    void shouldShowMenu(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        todoEngineTest.showMenu();
        assertTrue(outContent.toString().contains("1.View All Task"));
        System.setOut(originalOut);
    }

    @Test
    void shouldExitOnChoosingOption6(){

    }

}