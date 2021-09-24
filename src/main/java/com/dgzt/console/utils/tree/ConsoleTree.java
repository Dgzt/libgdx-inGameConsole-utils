package com.dgzt.console.utils.tree;

import com.strongjoshua.console.Console;

public class ConsoleTree {

    private final TreeNode root;

    public ConsoleTree() {
        root = new TreeNode();
    }

    public TreeNode addRow(final String line) {
        return addRow(root, line);
    }

    public TreeNode addRow(final TreeNode node, final String line) {
        final TreeNode newNode = new TreeNode(line);
        node.addChild(newNode);
        return newNode;
    }

    public void print(final Console console) {
        root.print(console);
    }
}
