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

import ca.viaware.tileset.Globals;
import ca.viaware.tileset.gui.SidebarPanel;
import ca.viaware.tileset.utils.FileUtils;
import ca.viaware.tileset.utils.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarListener implements ActionListener {

    private SidebarPanel sidebar;

    public SidebarListener(SidebarPanel sidebar) {
        this.sidebar = sidebar;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();

        if (cmd.equals("ENABLE_GRID")) {
            if (!Globals.isGrid) {
                Rectangle gridConfig = sidebar.getGridSettings();
                Utils.setGridConfig(gridConfig.x, gridConfig.y, gridConfig.width, gridConfig.height);
            } else {
                Utils.disableGrid();
            }
        }

        if (cmd.equals("SHOW_GRID")) {
            Globals.showingGrid = !Globals.showingGrid;
        }

        if (cmd.equals("GENERATE_REGIONS")) {
            if (Globals.isGrid) sidebar.getRegions().addAll(Utils.generateRegionsFromGrid(sidebar.getImagePanel().getImageDimensions().width / Utils.getGrid().width, sidebar.getImagePanel().getImageDimensions().height / Utils.getGrid().height));
        }
        
        if (cmd.equals("SAVE")) {
            FileUtils.saveRegions(Globals.dataFile, sidebar.getRegions());
        }

        sidebar.getImagePanel().repaint();
        sidebar.getParent().repaint();

    }
}
