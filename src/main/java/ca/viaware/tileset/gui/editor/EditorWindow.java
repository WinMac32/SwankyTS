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
package ca.viaware.tileset.gui.editor;

import ca.viaware.api.gui.base.VMenuItem;
import ca.viaware.tileset.gui.editor.listeners.MenuListener;
import ca.viaware.tileset.gui.editor.editorpanel.EditorPanel;
import ca.viaware.tileset.obj.Tileset;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class EditorWindow extends JFrame {

    private JTabbedPane editorTabs;

    public EditorWindow() {
        setTitle("SwankyTS");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        MenuListener menuListener = new MenuListener(this);
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new VMenuItem("Open", menuListener, "FILE_OPEN"));
        fileMenu.add(new VMenuItem("Save", menuListener, "FILE_SAVE"));
        menuBar.add(fileMenu);

        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.add(new VMenuItem("Generate to grid", menuListener, "TOOLS_GEN_GRID"));
        menuBar.add(toolsMenu);

        getContentPane().add(menuBar, BorderLayout.PAGE_START);

        this.editorTabs = new JTabbedPane();
        getContentPane().add(editorTabs, BorderLayout.CENTER);
    }

    public void addEditor(String name, Tileset tileset) {
        EditorPanel editor = new EditorPanel(tileset);
        editorTabs.addTab(null, editor);
        editorTabs.setTabComponentAt(editorTabs.getTabCount() - 1, new TabPanel(name, editorTabs));
    }

    public EditorPanel getSelectedEditor() {
        return (EditorPanel) editorTabs.getSelectedComponent();
    }

}
