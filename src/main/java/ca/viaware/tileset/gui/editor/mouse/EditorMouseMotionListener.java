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
along with Project Sierra.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.gui.editor.mouse;

import ca.viaware.tileset.gui.editor.panel.EditorGraphicsPanel;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EditorMouseMotionListener implements MouseMotionListener {

    private EditorGraphicsPanel owner;
    private Tileset tileset;
    private MouseInfo mouseInfo;

    public EditorMouseMotionListener(Tileset tileset, EditorGraphicsPanel owner, MouseInfo mouseInfo) {
        this.tileset = tileset;
        this.owner = owner;
        this.mouseInfo = mouseInfo;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
            owner.deleteRectAt(mouseEvent.getPoint());
        } else if ((mouseEvent.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) == MouseEvent.SHIFT_DOWN_MASK) {
            //TODO Drag image around
        } else {
            mouseInfo.setMouseUpPoint(owner.confine(Utils.adjustToGrid(tileset, Utils.adjustToNormal(new Point(mouseEvent.getX(), mouseEvent.getY()), owner.getZoomlevel()))));
            owner.repaint();
            owner.getParent().repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
