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
along with Project Sierra.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.gui.editor.editorpanel.listeners;

import ca.viaware.tileset.gui.editor.editorpanel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.editorpanel.EditorSidebarPanel;
import ca.viaware.tileset.gui.editor.editorpanel.tool.ToolManager;
import ca.viaware.tileset.gui.editor.editorpanel.tool.tools.AnimNamer;
import ca.viaware.tileset.obj.Tileset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarListener implements ActionListener {

    private EditorSidebarPanel sidebar;
    private Tileset tileset;
    private EditorGraphicsPanel editor;
    private ToolManager toolManager;

    public SidebarListener(Tileset tileset, EditorGraphicsPanel editor, EditorSidebarPanel sidebar, ToolManager toolManager) {
        this.tileset = tileset;
        this.sidebar = sidebar;
        this.editor = editor;
        this.toolManager = toolManager;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();

        if (cmd.equals("ENABLE_GRID")) {
            tileset.setGridConfig(sidebar.getGridSettings());
            tileset.setAlignToGrid(!tileset.isAlignToGrid());
        }

        if (cmd.equals("SHOW_GRID")) {
            tileset.setShowingGrid(!tileset.isShowingGrid());
        }

        if (cmd.equals("ANIM_SELECT")) {
            toolManager.startTool(new AnimNamer());
        }

        editor.repaint();
        sidebar.getParent().repaint();

    }
}
