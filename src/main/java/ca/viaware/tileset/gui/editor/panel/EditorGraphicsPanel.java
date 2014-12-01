/*
Copyright 2014 Seth Traverse

This file is part of Project Sierra.

Project Sierra is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Project Sierra is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Project Sierra.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.gui.editor.panel;

import ca.viaware.tileset.gui.editor.mouse.EditorMouseListener;
import ca.viaware.tileset.gui.editor.mouse.EditorMouseMotionListener;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;
import ca.viaware.tileset.gui.editor.mouse.MouseInfo;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class EditorGraphicsPanel extends JPanel {

    private Tileset tileset;

    private int zoomlevel;

    private MouseInfo mouseInfo;

    public EditorGraphicsPanel(final Tileset tileset) {
        this.tileset = tileset;

        setZoomLevel(1);
        this.mouseInfo = new MouseInfo();

        addMouseListener(new EditorMouseListener(tileset, this, mouseInfo));
        addMouseMotionListener(new EditorMouseMotionListener(tileset, this, mouseInfo));
    }

    public Dimension getImageDimensions() {
        return new Dimension(tileset.getImage().getWidth(), tileset.getImage().getHeight());
    }

    public int getZoomlevel() {
        return zoomlevel;
    }

    public void deleteRectAt(Point p) {
        for (int i = tileset.getRegions().size() - 1; i >= 0; i--) {
            if (tileset.getRegions().get(i).contains(Utils.adjustToNormal(p, getZoomlevel()))) {
                tileset.getRegions().remove(i);
                repaint();
                break;
            }
        }
    }

    public void setZoomLevel(int zoomlevel) {
        this.zoomlevel = zoomlevel;
        Dimension newSize = new Dimension(tileset.getImage().getWidth() * zoomlevel, tileset.getImage().getHeight() * zoomlevel);
        setPreferredSize(newSize);
        setMinimumSize(newSize);
        setMaximumSize(newSize);
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int width = getPreferredSize().width;
        int height = getPreferredSize().height;
        int zoom = getZoomlevel();

        ca.viaware.tileset.gui.editor.render.Renderer renderer = new ca.viaware.tileset.gui.editor.render.Renderer(tileset, g2d, width, height, zoom);

        g2d.drawImage(tileset.getImage(), 0, 0, width, height, this);

        renderer.renderGrid();

        g2d.setPaint(Color.WHITE);
        renderer.renderRegions();

        if (mouseInfo.isMouseDown()) {
            g2d.setPaint(Color.RED);
            Rectangle rect = Utils.formRegion(mouseInfo.getMouseDownPoint(), mouseInfo.getMouseUpPoint(), "");
            g2d.drawRect(rect.x * zoom, rect.y * zoom, rect.width * zoom, rect.height * zoom);
        }
    }

    public Point confine(Point p) {
        int w = tileset.getImage().getWidth();
        int h = tileset.getImage().getHeight();
        return new Point((p.x > w ? w : (p.x < 0 ? 0 : p.x)), (p.y > h ? h : (p.y < 0 ? 0 : p.y)));
    }

}
