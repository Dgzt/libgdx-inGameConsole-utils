package com.dgzt.console.utils.tree;

import com.badlogic.gdx.utils.Array;
import com.strongjoshua.console.Console;

import java.util.Iterator;

public class TreeNode {

    private final String name;
    private final Array<TreeNode> children;

    public TreeNode() {
        this("");
    }

    public TreeNode(final String name) {
        this.name = name;
        children = new Array<>();
    }

    public void addChild(final TreeNode child) {
        children.add(child);
    }

    public void print(final Console console) {
        print(console, "", "");
    }

    public void print(final Console console, final String prefix, final String childrenPrefix) {
        if (!prefix.equals("")) {
            console.log( prefix + name);
        }

        for (final Iterator<TreeNode> it = children.iterator(); it.hasNext();) {
            final TreeNode child = it.next();
            if (it.hasNext()) {
                child.print(console, childrenPrefix + "├---", childrenPrefix + "|   ");
            } else {
                child.print(console, childrenPrefix + "└---", childrenPrefix + "    ");
            }
        }
    }
}
