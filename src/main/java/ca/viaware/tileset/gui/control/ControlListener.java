package ca.viaware.tileset.gui.control;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.gui.editor.EditorWindow;
import ca.viaware.tileset.obj.Tileset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ControlListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd.equals("OPEN")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File imageFile = chooser.getSelectedFile();
                if (imageFile.exists()) {
                    File dataFile = new File(imageFile.getPath() + ".regions");
                    Tileset tileset = new Tileset(dataFile, imageFile);
                    try {
                        tileset.loadImage();
                        new EditorWindow(tileset).setVisible(true);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    Log.error("Could not find that image file!");
                }
            } else {
                Log.error("User cancelled.");
            }
        }

    }

}
