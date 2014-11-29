/*
Copyright 2014 Seth Traverse

This file is part of Project Sierra.

Project Sierra is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Project Sierra is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Project Sierra.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.gui.listeners;

import ca.viaware.tileset.gui.editor.EditorPanel;
import ca.viaware.tileset.gui.editor.EditorSidebarPanel;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.FileUtils;
import ca.viaware.tileset.utils.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarListener implements ActionListener {

    private EditorSidebarPanel sidebar;
    private Tileset tileset;
    private EditorPanel editor;

    public SidebarListener(Tileset tileset, EditorPanel editor, EditorSidebarPanel sidebar) {
        this.tileset = tileset;
        this.sidebar = sidebar;
        this.editor = editor;
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

        if (cmd.equals("GENERATE_REGIONS")) {
            if (tileset.isAlignToGrid()) tileset.getRegions().addAll(Utils.generateRegionsFromGrid(tileset, editor.getImageDimensions().width / tileset.getGridConfig().width, editor.getImageDimensions().height / tileset.getGridConfig().height));
        }
        
        if (cmd.equals("SAVE")) {
            FileUtils.saveRegions(tileset.getDataFile(), tileset.getRegions());
        }

        editor.repaint();
        sidebar.getParent().repaint();

    }
}
