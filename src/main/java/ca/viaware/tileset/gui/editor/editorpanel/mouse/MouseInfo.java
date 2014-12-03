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

import java.awt.*;

public class MouseInfo {

    private boolean mouseDown;
    private Point mouseDownPoint;
    private Point mouseUpPoint;
    private int zoomLevel;

    private Point lastDrag;

    public MouseInfo() {
        this.mouseDown = false;
        this.mouseDownPoint = new Point(0,0);
        this.mouseUpPoint = new Point(0,0);
        this.zoomLevel = 1;
    }

    public boolean isMouseDown() {
        return mouseDown;
    }

    public void setMouseDown(boolean mouseDown) {
        this.mouseDown = mouseDown;
    }

    public Point getMouseDownPoint() {
        return mouseDownPoint;
    }

    public void setMouseDownPoint(Point mouseDownPoint) {
        this.mouseDownPoint = mouseDownPoint;
    }

    public Point getMouseUpPoint() {
        return mouseUpPoint;
    }

    public void setMouseUpPoint(Point mouseUpPoint) {
        this.mouseUpPoint = mouseUpPoint;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel < 1 ? 1 : zoomLevel;
    }

    public Point getLastDrag() {
        return lastDrag;
    }

    public void setLastDrag(Point lastDrag) {
        this.lastDrag = lastDrag;
    }
}
