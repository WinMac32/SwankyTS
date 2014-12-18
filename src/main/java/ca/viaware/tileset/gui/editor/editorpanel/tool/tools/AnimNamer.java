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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AnimNamer extends Tool {

    private ArrayList<Region> animation;
    private boolean stop;

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
    public boolean handleKeyType(KeyEvent event) {
        return false;
    }

    @Override
    public boolean handleKeyDown(KeyEvent event) {
        if (!stop && event.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            stop = true;
            String base = JOptionPane.showInputDialog(null, "Enter root name including trailing dash", "Root name");
            if (!(base == null || base.equals(""))) {
                int i = 0;
                for (Region region : animation) {
                    region.setName(base + (i++));
                }
                getGraphicsPanel().repaint();
            }
            getToolManager().stopTool();
        }
        return !stop;
    }

    @Override
    public boolean handleKeyUp(KeyEvent event) {
        return true;
    }

    @Override
    public boolean handleDown(MouseEvent event, Region region) {
        return true;
    }

    @Override
    public boolean handleUp(MouseEvent event, Region region) {
        if ((event.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) == MouseEvent.SHIFT_DOWN_MASK) return false;
        return true;
    }

    private void addRegion(Region region) {
        if (region == null) return;
        for (int i = 0; i < animation.size(); i++) {
            if (animation.get(i).equals(region)) {
                animation.remove(i);
                break;
            }
        }
        animation.add(region);
        getGraphicsPanel().repaint();
    }

    @Override
    public boolean handleClick(MouseEvent event, Region region) {
        addRegion(region);
        return true;
    }

    @Override
    public boolean handleDrag(MouseEvent event, Region region) {
        if ((event.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) == MouseEvent.SHIFT_DOWN_MASK) return false;
        addRegion(region);
        return true;
    }

    @Override
    public void renderOverlay() {
        int i = 0;
        for (Region region : animation) {
            getRenderer().setColor(new Color(0x80FF00FF, true));
            getRenderer().fillRect(region);
            getRenderer().setColor(new Color(0));
            getRenderer().renderText("" + (i++), region.x + (region.width / 2) - 5, region.y + (region.height / 2) + 5, true);
        }
    }

}
