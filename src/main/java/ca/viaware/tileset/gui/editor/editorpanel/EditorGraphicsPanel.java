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

import ca.viaware.tileset.gui.editor.editorpanel.mouse.EditorMouseListener;
import ca.viaware.tileset.gui.editor.editorpanel.mouse.EditorMouseMotionListener;
import ca.viaware.tileset.gui.editor.editorpanel.mouse.EditorMouseWheelListener;
import ca.viaware.tileset.gui.editor.editorpanel.listeners.GraphicsPanelComponentListener;
import ca.viaware.tileset.gui.editor.editorpanel.render.Viewport;
import ca.viaware.tileset.gui.editor.editorpanel.tool1.ToolManager;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.gui.editor.editorpanel.mouse.MouseInfo;
import ca.viaware.tileset.gui.editor.editorpanel.render.Renderer;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class EditorGraphicsPanel extends JPanel {

    private Renderer renderer;

    private ToolManager toolManager;
    private ActionExecutor actionExecutor;

    public EditorGraphicsPanel(Tileset tileset) {

        Viewport viewport = new Viewport(0, 0, getWidth(), getHeight(), this);
        MouseInfo mouseInfo = new MouseInfo();

        this.renderer = new Renderer(tileset, mouseInfo, viewport, this);

        this.toolManager = new ToolManager(renderer, viewport, mouseInfo, this);
        this.actionExecutor = new ActionExecutor(tileset, mouseInfo, viewport, this);

        setBackground(Color.WHITE);

        addMouseListener(new EditorMouseListener(actionExecutor, toolManager));
        addMouseMotionListener(new EditorMouseMotionListener(actionExecutor, toolManager));
        addMouseWheelListener(new EditorMouseWheelListener(actionExecutor));
        addComponentListener(new GraphicsPanelComponentListener(actionExecutor));

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        int checkerWidth = 16;
        for (int i = 0; i < getWidth() / checkerWidth; i++) {
            for (int j = 0; j < getHeight() / checkerWidth; j++) {
                g2d.setColor((j + (i % 2)) % 2 == 0 ? Color.lightGray : Color.WHITE);
                g2d.fillRect(i * checkerWidth, j * checkerWidth, checkerWidth, checkerWidth);
            }
        }

        renderer.setContext(g2d);

        renderer.renderImage();
        renderer.renderGrid();
        renderer.renderRegions();
        renderer.renderSelection();

        if (toolManager.isActiveTool()) toolManager.getActiveTool().renderOverlay();
    }

    public ToolManager getToolManager() {
        return toolManager;
    }
}
