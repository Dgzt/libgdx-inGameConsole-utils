package com.dgzt.console.utils.table;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.StringBuilder;
import com.strongjoshua.console.Console;

public class ConsoleTable {

    private static final String HORIZONTAL_SEP = "-";
    private static final String VERTICAL_SEP = "|";
    private static final String JOIN_SEP = "+";

    private String[] headers;
    private final Array<String[]> rows;

    public ConsoleTable() {
        rows = new Array<>();
    }

    public void setHeaders(final String... headers) {
        this.headers = headers;
    }

    public void addRow(final String... cells) {
        rows.add(cells);
    }

    public void print(final Console console) {
        int[] maxWidths = headers != null ? createMaxWidths(headers) : null;

        for (final String[] cells: rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }

            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
            }

            for (int i = 0; i < cells.length; ++i) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        if (headers != null) {
            printLine(maxWidths, console);
            printRow(headers, maxWidths, console);
            printLine(maxWidths, console);
        }
        for (String[] cells : rows) {
            printRow(cells, maxWidths, console);
        }
        if (headers != null) {
            printLine(maxWidths, console);
        }
    }

    private int[] createMaxWidths(final String[] headers) {
        final int[] maxWidths = new int[headers.length];

        for (int i = 0; i < maxWidths.length; ++i) {
            maxWidths[i] = headers[i].length();
        }

        return maxWidths;
    }

    private void printLine(final int[] columnWidths, final Console console) {
        final StringBuilder stringBuilder = Pools.obtain(StringBuilder.class);

        for (int i = 0; i < columnWidths.length; i++) {
            final String line = new String(new char[columnWidths[i] + VERTICAL_SEP.length() + 1]).replace("\0", HORIZONTAL_SEP);
            stringBuilder.append(JOIN_SEP).append(line).append((i == columnWidths.length - 1 ? JOIN_SEP : ""));
        }
        console.log(stringBuilder.toString());
        stringBuilder.clear();
        Pools.free(stringBuilder);
    }

    private void printRow(final String[] cells, int[] maxWidths, final Console console) {
        final StringBuilder stringBuilder = Pools.obtain(StringBuilder.class);

        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? VERTICAL_SEP : "";
            stringBuilder.append(String.format("%s %-" + maxWidths[i] + "s %s", VERTICAL_SEP, s, verStrTemp));
        }
        console.log(stringBuilder.toString());
        stringBuilder.clear();
        Pools.free(stringBuilder);
    }
}
