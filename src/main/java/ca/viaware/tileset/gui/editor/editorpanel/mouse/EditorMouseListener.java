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
package ca.viaware.tileset.gui.editor.editorpanel.mouse;

import ca.viaware.tileset.gui.editor.editorpanel.ActionExecutor;
import ca.viaware.tileset.gui.editor.editorpanel.tool.ToolManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorMouseListener implements MouseListener {

    private ActionExecutor actionExecutor;
    private ToolManager toolManager;

    public EditorMouseListener(ActionExecutor actionExecutor, ToolManager toolManager) {
        this.actionExecutor = actionExecutor;
        this.toolManager = toolManager;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (toolManager.isActiveTool() && toolManager.getActiveTool().handleClick(mouseEvent, actionExecutor.getRegionAt(mouseEvent.getPoint()))) {
            return;
        }
        if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
            actionExecutor.removeRegionAt(mouseEvent.getPoint());
        } else if ((mouseEvent.getModifiersEx() & MouseEvent.ALT_DOWN_MASK) == MouseEvent.ALT_DOWN_MASK) {
            actionExecutor.renameRegionAt(mouseEvent.getPoint());
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (toolManager.isActiveTool() && toolManager.getActiveTool().handleDown(mouseEvent, actionExecutor.getRegionAt(mouseEvent.getPoint()))) {
            return;
        }
        if ((mouseEvent.getModifiersEx() & (MouseEvent.CTRL_DOWN_MASK | MouseEvent.SHIFT_DOWN_MASK)) == 0) {
            actionExecutor.startRegion(mouseEvent.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (toolManager.isActiveTool() && toolManager.getActiveTool().handleUp(mouseEvent, actionExecutor.getRegionAt(mouseEvent.getPoint()))) {
            return;
        }
        actionExecutor.finishRegion(mouseEvent.getPoint());
        actionExecutor.finishDraggingViewport();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
