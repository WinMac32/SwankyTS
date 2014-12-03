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
package ca.viaware.tileset.gui.editor.listeners;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.gui.editor.EditorWindow;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.FileUtils;
import ca.viaware.tileset.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuListener implements ActionListener {

    private EditorWindow editor;

    public MenuListener(EditorWindow editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd.equals("FILE_OPEN")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File imageFile = chooser.getSelectedFile();
                if (imageFile.exists()) {
                    File dataFile = new File(imageFile.getPath() + ".regions");
                    Tileset tileset = new Tileset(dataFile, imageFile);
                    try {
                        tileset.loadImage();
                        editor.addEditor(imageFile.getName(), tileset);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    Log.error("Could not find that image file!");
                }
            } else {
                Log.error("User cancelled.");
            }
        } else if (cmd.equals("FILE_SAVE")) {
            Tileset tileset = editor.getSelectedEditor().getTileset();
            FileUtils.saveRegions(tileset.getDataFile(), tileset.getRegions());
        } else if (cmd.equals("TOOLS_GEN_GRID")) {
            Tileset tileset = editor.getSelectedEditor().getTileset();
            if (tileset.isAlignToGrid()) tileset.getRegions().addAll(Utils.generateRegionsFromGrid(tileset, tileset.getImage().getWidth() / tileset.getGridConfig().width, tileset.getImage().getHeight() / tileset.getGridConfig().height));
            editor.getSelectedEditor().repaint();
        }

    }

}
