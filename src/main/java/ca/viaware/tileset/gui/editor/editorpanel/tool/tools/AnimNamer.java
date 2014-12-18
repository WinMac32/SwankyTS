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
package ca.viaware.tileset.gui.editor.editorpanel.tool.tools;

import ca.viaware.tileset.gui.editor.editorpanel.tool.Tool;
import ca.viaware.tileset.obj.Region;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AnimNamer extends Tool {

    private ArrayList<Region> animation;

    public AnimNamer() {
        super("Animation namer");

        animation = new ArrayList<Region>();
    }

    @Override
    public void activate() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean handleClick(MouseEvent event, Region region) {
        animation.add(region);
        getGraphicsPanel().repaint();
        return false;
    }

    @Override
    public boolean handleDrag(MouseEvent event, Region region) {
        return false;
    }

    @Override
    public void renderOverlay() {
        getRenderer().setColor(0xFF00FF);
        for (Region region : animation) {
            getRenderer().fillRect(region);
        }
    }

}
