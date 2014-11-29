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

import ca.viaware.tileset.Region;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class RawImageWindow extends JFrame {

    private SidebarPanel sidebarPanel;
    private RawImagePanel imagePanel;

    private ArrayList<Region> regions;

    public RawImageWindow(BufferedImage image, ArrayList<Region> regions) {
        setTitle("ViaWare Tileset Creator");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.regions = regions;
        
        imagePanel = new RawImagePanel(image, regions);
        sidebarPanel = new SidebarPanel(imagePanel, regions);

        final JScrollPane scroll = new JScrollPane(imagePanel);

        scroll.getViewport().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //Linux fix
                scroll.repaint();
            }
        });

        add(sidebarPanel, BorderLayout.LINE_START);
        add(scroll, BorderLayout.CENTER);
    }

}
