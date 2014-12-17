/*
Copyright 2014 Seth Traverse

This file is part of SwankyTS.

SwankyTS is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SwankyTS is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SwankyTS.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.gui.editor.editorpanel;

import ca.viaware.tileset.obj.Tileset;

import javax.swing.*;
import java.awt.*;

public class EditorPanel extends JPanel {

    private Tileset tileset;

    private EditorSidebarPanel sidebarPanel;
    private EditorGraphicsPanel editor;

    public EditorPanel(Tileset tileset) {
        this.tileset = tileset;

        this.editor = new EditorGraphicsPanel(tileset);
        this.sidebarPanel = new EditorSidebarPanel(tileset, editor, editor.getToolManager());

        setLayout(new BorderLayout());
        add(sidebarPanel, BorderLayout.LINE_START);
        add(editor, BorderLayout.CENTER);
    }

    public Tileset getTileset() {
        return tileset;
    }

}
