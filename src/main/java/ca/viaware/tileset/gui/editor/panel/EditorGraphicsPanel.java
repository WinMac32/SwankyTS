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
package ca.viaware.tileset.gui.editor.panel;

import ca.viaware.tileset.gui.editor.ActionExecutor;
import ca.viaware.tileset.gui.editor.mouse.EditorMouseListener;
import ca.viaware.tileset.gui.editor.mouse.EditorMouseMotionListener;
import ca.viaware.tileset.gui.editor.mouse.EditorMouseWheelListener;
import ca.viaware.tileset.gui.editor.panel.listeners.GraphicsPanelComponentListener;
import ca.viaware.tileset.gui.editor.render.Viewport;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.gui.editor.mouse.MouseInfo;
import ca.viaware.tileset.gui.editor.render.Renderer;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class EditorGraphicsPanel extends JPanel {

    private Renderer renderer;

    private ActionExecutor actionExecutor;

    public EditorGraphicsPanel(Tileset tileset) {

        Viewport viewport = new Viewport(0, 0, getWidth(), getHeight(), this);
        MouseInfo mouseInfo = new MouseInfo();

        this.renderer = new Renderer(tileset, mouseInfo, viewport, this);
        this.actionExecutor = new ActionExecutor(tileset, mouseInfo, viewport, this);

        addMouseListener(new EditorMouseListener(actionExecutor));
        addMouseMotionListener(new EditorMouseMotionListener(actionExecutor));
        addMouseWheelListener(new EditorMouseWheelListener(actionExecutor));
        addComponentListener(new GraphicsPanelComponentListener(actionExecutor));

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        renderer.setContext(g2d);

        renderer.renderImage();

        renderer.renderGrid();

        renderer.renderRegions();

        renderer.renderSelection();
    }

}
