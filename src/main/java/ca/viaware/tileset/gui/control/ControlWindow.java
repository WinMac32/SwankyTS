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
package ca.viaware.tileset.gui.control;

import ca.viaware.api.gui.base.VButton;

import javax.swing.*;
import java.awt.*;

public class ControlWindow {

    private JFrame frame;

    public ControlWindow() {
        setFrame(new JFrame("SwankyTS"));
        initFrame();
    }

    private void initFrame() {
        getFrame().setSize(400, 100);
        getFrame().setResizable(false);
        getFrame().getContentPane().setLayout(new FlowLayout());

        //TODO Verify saved
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getFrame().getContentPane().add(new VButton("Open", new ControlListener(), "OPEN"));
    }

    private JFrame getFrame() {
        return frame;
    }

    private void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void show() {
        getFrame().setVisible(true);
    }

}
