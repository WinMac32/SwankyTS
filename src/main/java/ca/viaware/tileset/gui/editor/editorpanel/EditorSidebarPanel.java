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

import ca.viaware.api.gui.base.VButton;
import ca.viaware.api.utils.StringUtils;
import ca.viaware.tileset.gui.LabelAndTextPanel;
import ca.viaware.tileset.gui.editor.editorpanel.listeners.SidebarListener;
import ca.viaware.tileset.gui.editor.editorpanel.tool.ToolManager;
import ca.viaware.tileset.obj.Tileset;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class EditorSidebarPanel extends JPanel {

    private LabelAndTextPanel gridxPanel;
    private LabelAndTextPanel gridyPanel;
    private LabelAndTextPanel gridWidthPanel;
    private LabelAndTextPanel gridHeightPanel;

    public EditorSidebarPanel(Tileset tileset, EditorGraphicsPanel editor, ToolManager toolManager) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(210, 200));

        SidebarListener sidebarListener = new SidebarListener(tileset, editor, this, toolManager);

        add(new VButton("Align to Grid", sidebarListener, "ENABLE_GRID"));
        add(new VButton("Show the Grid", sidebarListener, "SHOW_GRID"));

        gridxPanel = new LabelAndTextPanel("Grid X", "0");
        gridyPanel = new LabelAndTextPanel("Grid Y", "0");
        gridWidthPanel = new LabelAndTextPanel("Grid Width", "32");
        gridHeightPanel = new LabelAndTextPanel("Grid Height", "32");

        add(gridxPanel);
        add(gridyPanel);
        add(gridWidthPanel);
        add(gridHeightPanel);

        add(new VButton("Anim Select", sidebarListener, "ANIM_SELECT"));

        JTextArea infoArea = new JTextArea();
        infoArea.setPreferredSize(new Dimension(180, 200));
        infoArea.setEditable(false);

        infoArea.setFont(infoArea.getFont().deriveFont(8));

        infoArea.setText("Keybinds:\n" +
            "Ctrl-click: Delete region\n" +
            "Ctrl-drag: Delete regions\n" +
            "Alt-click: Rename region\n" +
            "Shift-drag: Pan camera\n");

        add(infoArea);

    }

    public Rectangle getGridSettings() {
        int x = Integer.parseInt(StringUtils.cleanNumber(gridxPanel.getField().getText()));
        int y = Integer.parseInt(StringUtils.cleanNumber(gridyPanel.getField().getText()));
        int width = Integer.parseInt(StringUtils.cleanNumber(gridWidthPanel.getField().getText()));
        int height = Integer.parseInt(StringUtils.cleanNumber(gridHeightPanel.getField().getText()));

        return new Rectangle(x, y, width, height);
    }

}
