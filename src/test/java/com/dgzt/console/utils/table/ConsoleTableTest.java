package com.dgzt.console.utils.table;

import com.strongjoshua.console.Console;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ConsoleTableTest {

    private static final String TABLE_WITH_HEADER_EXPECTED_RESULT =
            "+----------+----------+\n" +
            "| Header 1 | Header 2 |\n" +
            "+----------+----------+\n" +
            "| row 11   | row 12   |\n" +
            "| row 21   | row 22   |\n" +
            "+----------+----------+\n";

    private static final String TABLE_WITHOUT_HEADER_EXPECTED_RESULT =
            "| row 11 | row 12              |\n" +
            "| row 21 | row 22              |\n" +
            "| row 21 | extra length row 22 |\n";

    private Console console;

    private ConsoleTable consoleTable;

    private String printedText;

    @Before
    public void setup() {
        console = Mockito.mock(Console.class);
        Mockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(final InvocationOnMock invocation) {
                final Object[] args = invocation.getArguments();
                printedText += args[0] + "\n";
                return null;
            }
        }).when(console).log(Mockito.anyString());

        consoleTable = new ConsoleTable();
        printedText = "";
    }

    @Test
    public void tableWithHeader() {
        consoleTable.setHeaders("Header 1", "Header 2");
        consoleTable.addRow("row 11", "row 12");
        consoleTable.addRow("row 21", "row 22");

        consoleTable.print(console);

        Assert.assertEquals(printedText, TABLE_WITH_HEADER_EXPECTED_RESULT);
    }

    @Test
    public void tableWithoutHeader() {
        consoleTable.addRow("row 11", "row 12");
        consoleTable.addRow("row 21", "row 22");
        consoleTable.addRow("row 21", "extra length row 22");

        consoleTable.print(console);

        Assert.assertEquals(printedText, TABLE_WITHOUT_HEADER_EXPECTED_RESULT);
    }
}
