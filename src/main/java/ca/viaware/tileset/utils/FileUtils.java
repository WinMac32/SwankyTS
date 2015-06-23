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
package ca.viaware.tileset.utils;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.file.FileInterface;
import ca.viaware.tileset.file.FileManager;
import ca.viaware.tileset.obj.Tileset;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static Tileset loadTileset() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File sourceFile = chooser.getSelectedFile();
            if (sourceFile.exists()) {
                try {
                    FileInterface loader = FileManager.getInterfaceForExtension(sourceFile.getName().split("[.]")[1]);
                    Tileset tileset;
                    if (loader != null) {
                        Log.info("Loading existing tileset");
                        tileset = loader.runImport(sourceFile);
                    } else {
                        Log.info("Loading new tileset as raw image");
                        tileset = new Tileset(sourceFile);
                    }

                    tileset.loadImage();
                    return tileset;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                Log.error("Could not find that image file!");
            }
        } else {
            Log.error("User cancelled.");
        }
        return null;
    }

    public static void saveTileset(Tileset tileset) {
        if (tileset.getDataFile() != null) {
            FileManager.getInterfaceForExtension(tileset.getDataFile().getName().split("[.]")[1]).runExport(tileset, tileset.getDataFile().getAbsolutePath());
        } else {
            JFileChooser chooser = new JFileChooser(tileset.getImageFile().getParentFile().getAbsolutePath());
            for (FileInterface fileInterface : FileManager.getInterfaces()) {
                chooser.addChoosableFileFilter(new FileNameExtensionFilter(fileInterface.getName(), fileInterface.getExtensions()));
            }
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File dataFile = chooser.getSelectedFile();
                Log.info("Selected file %0 of type %1", dataFile.getName(), chooser.getFileFilter().getDescription());
                FileInterface fileInterface = FileManager.getInterface(chooser.getFileFilter().getDescription());
                if (fileInterface != null) {
                    fileInterface.runExport(tileset, dataFile.getAbsolutePath() + "." + fileInterface.getExtensions()[0]);
                } else {
                    Log.error("Unknown file type!");
                }
            } else {
                Log.error("User cancelled");
            }
        }
    }

}
