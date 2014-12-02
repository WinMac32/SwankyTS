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
package ca.viaware.tileset.gui.editor.mouse;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.gui.editor.panel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.render.Viewport;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EditorMouseMotionListener implements MouseMotionListener {

    private EditorGraphicsPanel graphicsPanel;
    private Tileset tileset;
    private MouseInfo mouseInfo;
    private Viewport viewport;

    public EditorMouseMotionListener(Tileset tileset, EditorGraphicsPanel graphicsPanel, MouseInfo mouseInfo, Viewport viewport) {
        this.tileset = tileset;
        this.graphicsPanel = graphicsPanel;
        this.mouseInfo = mouseInfo;
        this.viewport = viewport;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
            graphicsPanel.deleteRectAt(mouseEvent.getPoint());
        } else if ((mouseEvent.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) == MouseEvent.SHIFT_DOWN_MASK) {
            //TODO Drag image around
            if (mouseInfo.getLastDrag() != null) {
                int dx = mouseInfo.getLastDrag().x - mouseEvent.getPoint().x;
                int dy = mouseInfo.getLastDrag().y - mouseEvent.getPoint().y;
                viewport.x = viewport.x - dx;
                viewport.y = viewport.y - dy;
                graphicsPanel.repaint();
            }
            mouseInfo.setLastDrag(mouseEvent.getPoint());
        } else {
            mouseInfo.setMouseUpPoint(tileset.confine(Utils.adjustToGrid(tileset, Utils.adjustToNormal(new Point(mouseEvent.getX(), mouseEvent.getY()), mouseInfo.getZoomLevel()))));
            graphicsPanel.repaint();
            graphicsPanel.getParent().repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
