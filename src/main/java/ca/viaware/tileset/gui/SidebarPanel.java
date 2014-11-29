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
package ca.viaware.tileset.gui;

import ca.viaware.api.utils.StringUtils;
import ca.viaware.tileset.Region;
import ca.viaware.tileset.gui.listeners.SidebarListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SidebarPanel extends JPanel {

    private LabelAndTextPanel gridxPanel;
    private LabelAndTextPanel gridyPanel;
    private LabelAndTextPanel gridWidthPanel;
    private LabelAndTextPanel gridHeightPanel;

    private RawImagePanel imagePanel;
    private ArrayList<Region> regions;

    public SidebarPanel(RawImagePanel imagePanel, ArrayList<Region> regions) {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(200, 200));
        this.imagePanel = imagePanel;
        this.regions = regions;

        add(new BasicButton(new SidebarListener(this), "ENABLE_GRID", "Align to Grid"));
        add(new BasicButton(new SidebarListener(this), "SHOW_GRID", "Show the Grid"));

        gridxPanel = new LabelAndTextPanel("Grid X", "0");
        gridyPanel = new LabelAndTextPanel("Grid Y", "0");
        gridWidthPanel = new LabelAndTextPanel("Grid Width", "32");
        gridHeightPanel = new LabelAndTextPanel("Grid Height", "32");

        add(gridxPanel);
        add(gridyPanel);
        add(gridWidthPanel);
        add(gridHeightPanel);

        add(new BasicButton(new SidebarListener(this), "GENERATE_REGIONS", "Generate to Grid"));
        add(new BasicButton(new SidebarListener(this), "SAVE", "Save"));

        JTextArea infoArea = new JTextArea();
        infoArea.setPreferredSize(new Dimension(180, 200));
        infoArea.setEditable(false);

        infoArea.setText("Keybinds:\n" +
                "Ctrl-click: Delete region\n" +
                "Ctrl-drag: Delete regions\n" +
                "Shift-click: Rename region");

        add(infoArea);

    }

    public RawImagePanel getImagePanel() {
        return imagePanel;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public Rectangle getGridSettings() {
        int x = Integer.parseInt(StringUtils.cleanNumber(gridxPanel.getField().getText()));
        int y = Integer.parseInt(StringUtils.cleanNumber(gridyPanel.getField().getText()));
        int width = Integer.parseInt(StringUtils.cleanNumber(gridWidthPanel.getField().getText()));
        int height = Integer.parseInt(StringUtils.cleanNumber(gridHeightPanel.getField().getText()));

        return new Rectangle(x, y, width, height);
    }

}
