package com.dgzt.console.utils.tree;

import com.strongjoshua.console.Console;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ConsoleTreeTest {

    private static final String EXPECTED_RESULT =
            "+---Line 1\n" +
            "|   +---Inner line 11\n" +
            "|   +---Inner line 12\n" +
            "|   |   +---Inner line 121\n" +
            "|   |       +---Inner line 1211\n" +
            "|   +---Inner line 13\n" +
            "+---Line 2\n";

    private Console console;

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

        printedText = "";
    }

    @Test
    public void tree() {
        final ConsoleTree consoleTree = new ConsoleTree();

        final TreeNode line1 = consoleTree.addRow("Line 1");
        consoleTree.addRow(line1, "Inner line 11");
        final TreeNode line12 = consoleTree.addRow(line1, "Inner line 12");
        final TreeNode line121 = consoleTree.addRow(line12, "Inner line 121");
        consoleTree.addRow(line121, "Inner line 1211");
        consoleTree.addRow(line1, "Inner line 13");
        consoleTree.addRow("Line 2");

        consoleTree.print(console);

        Assert.assertEquals(printedText, EXPECTED_RESULT);
    }
}
