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

import ca.viaware.tileset.gui.editor.ActionExecutor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EditorMouseMotionListener implements MouseMotionListener {

    private ActionExecutor actionExecutor;

    public EditorMouseMotionListener(ActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
            actionExecutor.removeRegionAt(mouseEvent.getPoint());
        } else if ((mouseEvent.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) == MouseEvent.SHIFT_DOWN_MASK) {
            actionExecutor.dragViewport(mouseEvent.getPoint());
        } else {
            actionExecutor.resizeRegion(mouseEvent.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
