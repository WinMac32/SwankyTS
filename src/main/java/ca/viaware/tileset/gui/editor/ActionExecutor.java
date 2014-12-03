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
package ca.viaware.tileset.gui.editor;

import ca.viaware.tileset.gui.editor.mouse.MouseInfo;
import ca.viaware.tileset.gui.editor.panel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.render.Viewport;
import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class ActionExecutor {

    private Tileset tileset;
    private Viewport viewport;
    private EditorGraphicsPanel graphicsPanel;
    private MouseInfo mouseInfo;

    /**
     * Main entry point for UI listeners to perform actions
     * @param tileset
     * @param viewport
     * @param graphicsPanel
     * @param mouseInfo
     */
    public ActionExecutor(Tileset tileset, MouseInfo mouseInfo, Viewport viewport, EditorGraphicsPanel graphicsPanel) {
        this.tileset = tileset;
        this.viewport = viewport;
        this.graphicsPanel = graphicsPanel;
        this.mouseInfo = mouseInfo;
    }

    /**
     * Removes region at point if it exists
     * @param point Point in screen space
     */
    public void removeRegionAt(Point point) {
        for (int i = tileset.getRegions().size() - 1; i >= 0; i--) {
            if (tileset.getRegions().get(i).contains(viewport.screenToOrigin(point))) {
                tileset.getRegions().remove(i);
                graphicsPanel.repaint();
                break;
            }
        }
    }

    /**
     * Renames region at point if it exists.<br />
     * Will block the main UI thread for user input
     * @param point Point in screen space
     */
    public void renameRegionAt(Point point) {
        for (Region region : tileset.getRegions()) {
            if (region.contains(viewport.screenToOrigin(point))) {
                String newName = JOptionPane.showInputDialog(graphicsPanel.getParent(), "Enter new region name", region.getName());
                if (newName != null) {
                    region.setName(newName);
                    graphicsPanel.repaint();
                }
                break;
            }
        }
    }

    /**
     * Create new region formed between 2 points.<br />
     * Will block the main UI thread for user input
     * @param p1
     * @param p2
     */
    public void createNewRegion(Point p1, Point p2) {
        if (Math.abs(p1.x - p2.x) > 1 && Math.abs(p1.y - p2.y) > 1) {
            String name = JOptionPane.showInputDialog(graphicsPanel.getParent(), "Enter region name", "REGION");
            if (name != null) {
                tileset.getRegions().add(Utils.formRegion(p1, p2, name));
            }
        }
        graphicsPanel.repaint();
    }

    /**
     * Drags the viewport to a point<br />
     * Uses the delta location between 2 consecutive calls to modify the view
     * @param point Point in screen space
     */
    public void dragViewport(Point point) {
        if (mouseInfo.getLastDrag() != null) {
            int dx = mouseInfo.getLastDrag().x - point.x;
            int dy = mouseInfo.getLastDrag().y - point.y;
            viewport.x = viewport.x - dx;
            viewport.y = viewport.y - dy;
            graphicsPanel.repaint();
        }
        mouseInfo.setLastDrag(point);
    }

    /**
     * Must call after no longer dragging viewport
     */
    public void finishDraggingViewport() {
        mouseInfo.setLastDrag(null);
    }

    private void recalculateViewportZoom() {
        viewport.width = viewport.getViewWidth() / mouseInfo.getZoomLevel();
        viewport.height = viewport.getViewHeight() / mouseInfo.getZoomLevel();
    }

    /**
     * Zoom the viewport around a point
     * @param zoomChange x > 0 zooms in |x| factor, x < 0 zooms out |x| factor
     * @param point Point in 'who knows' space. TODO Not implemented.
     */
    public void zoom(int zoomChange, Point point) {
        mouseInfo.setZoomLevel(mouseInfo.getZoomLevel() + zoomChange);

        //TODO Scale around mouse pointer

        recalculateViewportZoom();

        graphicsPanel.repaint();
    }

    /**
     * Start drawing a region at point<br />
     * @param p Point in screen space
     */
    public void startRegion(Point p) {
        mouseInfo.setMouseDown(true);
        mouseInfo.setMouseDownPoint(tileset.confine(tileset.adjustToGrid(viewport.screenToOrigin(p))));
    }

    /**
     * If a region is being drawn, will create the region<br />
     * @param p Point in screen space where region finished
     */
    public void finishRegion(Point p) {
        Point point = tileset.confine(tileset.adjustToGrid(viewport.screenToOrigin(p)));

        if (mouseInfo.isMouseDown()) {
            mouseInfo.setMouseDown(false);
            mouseInfo.setMouseUpPoint(point);
            createNewRegion(mouseInfo.getMouseUpPoint(), mouseInfo.getMouseDownPoint());
        }
    }

    /**
     * Resizes the in-progress region
     * @param p Point in screen space for new region corner
     */
    public void resizeRegion(Point p) {
        mouseInfo.setMouseUpPoint(tileset.confine(tileset.adjustToGrid(viewport.screenToOrigin(p))));
        graphicsPanel.repaint();
        graphicsPanel.getParent().repaint();
    }

    /**
     * Resize the viewport viewing area
     * @param width
     * @param height
     */
    public void resizeViewport(int width, int height) {
        viewport.setViewWidth(width);
        viewport.setViewHeight(height);
        recalculateViewportZoom();
        graphicsPanel.repaint();
    }

}
