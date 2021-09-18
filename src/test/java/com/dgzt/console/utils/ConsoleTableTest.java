package com.dgzt.console.utils;

import com.strongjoshua.console.Console;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ConsoleTableTest {

    private Console console;

    private ConsoleTable consoleTable;

    @Before
    public void setup() {
        console = Mockito.mock(Console.class);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                final Object[] args = invocation.getArguments();
                System.out.println(args[0]);
                return null;
            }
        }).when(console).log(Mockito.anyString());

        consoleTable = new ConsoleTable();
    }

    @Test
    public void tableWithHeader() {
        consoleTable.setHeaders("Header 1", "Header 2");
        consoleTable.addRow("row 11", "row 12");
        consoleTable.addRow("row 21", "row 22");

        consoleTable.print(console);
    }

    @Test
    public void tableWithoutHeader() {
        consoleTable.addRow("row 11", "row 12");
        consoleTable.addRow("row 21", "row 22");

        consoleTable.print(console);
    }
}
