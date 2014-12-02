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

import ca.viaware.tileset.gui.editor.panel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.render.Viewport;
import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorMouseListener implements MouseListener {

    private Tileset tileset;
    private EditorGraphicsPanel owner;
    private MouseInfo mouseInfo;
    private Viewport viewport;

    public EditorMouseListener(Tileset tileset, EditorGraphicsPanel owner, MouseInfo mouseInfo, Viewport viewport) {
        this.tileset = tileset;
        this.owner = owner;
        this.mouseInfo = mouseInfo;
        this.viewport = viewport;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
            for (int i = tileset.getRegions().size() - 1; i >= 0; i--) {
                if (tileset.getRegions().get(i).contains(mouseEvent.getPoint())) {
                    tileset.getRegions().remove(i);
                    owner.repaint();
                    break;
                }
            }
        } else if ((mouseEvent.getModifiersEx() & MouseEvent.ALT_DOWN_MASK) == MouseEvent.ALT_DOWN_MASK) {
            for (Region region : tileset.getRegions()) {
                if (region.contains(mouseEvent.getPoint())) {
                    String newName = JOptionPane.showInputDialog(owner.getParent(), "Enter new region name", region.getName());
                    if (newName != null) {
                        region.setName(newName);
                        owner.repaint();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiersEx() & (MouseEvent.CTRL_DOWN_MASK | MouseEvent.SHIFT_DOWN_MASK)) == 0) {
            mouseInfo.setMouseDown(true);
            mouseInfo.setMouseDownPoint(tileset.confine(tileset.adjustToGrid(viewport.screenToOrigin(new Point(mouseEvent.getX(), mouseEvent.getY())))));
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseInfo.isMouseDown()) {
            mouseInfo.setMouseDown(false);
            mouseInfo.setMouseUpPoint(tileset.confine(tileset.adjustToGrid(viewport.screenToOrigin(new Point(mouseEvent.getX(), mouseEvent.getY())))));
            if (Math.abs(mouseInfo.getMouseUpPoint().x - mouseInfo.getMouseDownPoint().x) > 1 && Math.abs(mouseInfo.getMouseUpPoint().y - mouseInfo.getMouseDownPoint().y) > 1) {
                String name = JOptionPane.showInputDialog(owner.getParent(), "Enter region name", "REGION");
                if (name != null) {
                    tileset.getRegions().add(Utils.formRegion(mouseInfo.getMouseDownPoint(), mouseInfo.getMouseUpPoint(), name));
                }
            }
            owner.repaint();
        }
        mouseInfo.setLastDrag(null);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
