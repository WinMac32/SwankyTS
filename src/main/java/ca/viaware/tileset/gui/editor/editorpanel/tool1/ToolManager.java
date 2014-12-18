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
package ca.viaware.tileset.gui.editor.editorpanel.tool1;

import ca.viaware.tileset.gui.editor.editorpanel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.editorpanel.mouse.MouseInfo;
import ca.viaware.tileset.gui.editor.editorpanel.render.Renderer;
import ca.viaware.tileset.gui.editor.editorpanel.render.Viewport;

public class ToolManager {

    private Tool activeTool;

    private Renderer renderer;
    private Viewport viewport;
    private MouseInfo mouseInfo;
    private EditorGraphicsPanel graphicsPanel;

    public ToolManager(Renderer renderer, Viewport viewport, MouseInfo mouseInfo, EditorGraphicsPanel graphicsPanel) {
        this.renderer = renderer;
        this.viewport = viewport;
        this.mouseInfo = mouseInfo;
        this.graphicsPanel = graphicsPanel;
    }

    public boolean isActiveTool() {
        return activeTool != null;
    }

    public Tool getActiveTool() {
        return activeTool;
    }

    public void stopTool() {
        if (isActiveTool()) {
            activeTool.destroy();
        }
        activeTool = null;
    }

    public void startTool(Tool tool) {
        if (isActiveTool()) {
            activeTool.destroy();
        }
        activeTool = tool;

        tool.setGraphicsPanel(graphicsPanel);
        tool.setMouseInfo(mouseInfo);
        tool.setRenderer(renderer);
        tool.setViewport(viewport);
        tool.setToolManager(this);

        tool.activate();
    }

}
