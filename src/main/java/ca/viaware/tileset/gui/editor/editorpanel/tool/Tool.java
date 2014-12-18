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
package ca.viaware.tileset.gui.editor.editorpanel.tool;

import ca.viaware.tileset.gui.editor.editorpanel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.editorpanel.mouse.MouseInfo;
import ca.viaware.tileset.gui.editor.editorpanel.render.Renderer;
import ca.viaware.tileset.gui.editor.editorpanel.render.Viewport;
import ca.viaware.tileset.obj.Region;

import java.awt.event.MouseEvent;

public abstract class Tool {

    private Renderer renderer;
    private Viewport viewport;
    private MouseInfo mouseInfo;
    private EditorGraphicsPanel graphicsPanel;
    private ToolManager toolManager;

    private String name;

    protected Tool(String name) {
        this.name = name;
    }

    public abstract void activate();
    public abstract void destroy();
    public abstract boolean handleClick(MouseEvent event, Region region);
    public abstract boolean handleDrag(MouseEvent event, Region region);
    public abstract void renderOverlay();

    protected Renderer getRenderer() {
        return renderer;
    }

    protected Viewport getViewport() {
        return viewport;
    }

    protected MouseInfo getMouseInfo() {
        return mouseInfo;
    }

    protected ToolManager getToolManager() {
        return toolManager;
    }

    protected EditorGraphicsPanel getGraphicsPanel() {
        return graphicsPanel;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public void setMouseInfo(MouseInfo mouseInfo) {
        this.mouseInfo = mouseInfo;
    }

    public void setGraphicsPanel(EditorGraphicsPanel graphicsPanel) {
        this.graphicsPanel = graphicsPanel;
    }

    public void setToolManager(ToolManager toolManager) {
        this.toolManager = toolManager;
    }
}
