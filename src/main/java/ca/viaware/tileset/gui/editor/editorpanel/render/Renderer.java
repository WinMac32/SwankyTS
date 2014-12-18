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
along with Project Sierra.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.gui.editor.editorpanel.render;

import ca.viaware.tileset.gui.editor.editorpanel.EditorGraphicsPanel;
import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;
import ca.viaware.tileset.gui.editor.editorpanel.mouse.MouseInfo;

import java.awt.*;

public class Renderer {

    private Tileset tileset;
    private MouseInfo mouseInfo;
    private EditorGraphicsPanel graphicsPanel;
    private Viewport viewport;

    private Graphics2D g2d;

    public Renderer(Tileset tileset, MouseInfo mouseInfo, Viewport viewport, EditorGraphicsPanel graphicsPanel) {
        this.tileset = tileset;
        this.mouseInfo = mouseInfo;
        this.graphicsPanel = graphicsPanel;
        this.viewport = viewport;
    }

    public void setContext(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void renderGrid() {
        if (tileset.isAlignToGrid() && tileset.isShowingGrid()) {
            int gridX = tileset.getGridConfig().x;
            int gridY = tileset.getGridConfig().y;
            int gridWidth = tileset.getGridConfig().width;
            int gridHeight = tileset.getGridConfig().height;

            int width = tileset.getImage().getWidth();
            int height = tileset.getImage().getHeight();

            g2d.setPaint(Color.GREEN);

            for (int i = 0; i < (width / gridWidth); i++) {
                int x = i * gridWidth + gridX;
                renderLine(x, 0, x, height);
            }

            for (int i = 0; i < (height / gridHeight); i++) {
                int y = i * gridHeight + gridY;
                renderLine(0, y, width, y);
            }
        }
    }

    public void renderRegions() {
        g2d.setPaint(Color.WHITE);
        for (Region region : tileset.getRegions()) {
            renderRect(region);
            Rectangle transReg = viewport.originToScreen(region);
            g2d.drawString(Utils.adjustToWidth(g2d.getFont(), g2d.getFontRenderContext(), region.getName(), transReg.width), transReg.x, transReg.y + g2d.getFontMetrics().getHeight());
        }
    }

    public void renderImage() {
        Rectangle orig = new Rectangle(0, 0, tileset.getImage().getWidth(), tileset.getImage().getHeight());
        Rectangle transformed = viewport.originToScreen(orig);
        g2d.drawImage(tileset.getImage(), transformed.x, transformed.y, transformed.width, transformed.height, graphicsPanel);
    }

    public void renderSelection() {
        if (mouseInfo.isMouseDown()) {
            g2d.setPaint(Color.RED);
            Rectangle rect = Utils.formRegion(mouseInfo.getMouseDownPoint(), mouseInfo.getMouseUpPoint(), "");
            renderRect(rect);
        }
    }

    public void renderRect(Rectangle rect) {
        rect = viewport.originToScreen(rect);
        g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void fillRect(Rectangle rect) {
        rect = viewport.originToScreen(rect);
        g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void renderLine(int x1, int y1, int x2, int y2) {
        Point p1 = viewport.originToScreen(new Point(x1, y1));
        Point p2 = viewport.originToScreen(new Point(x2, y2));
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public void setColor(Color color) {
        g2d.setColor(color);
    }

    public void renderText(String text, int x, int y, boolean scale) {
        int fSize = g2d.getFont().getSize();
        if (scale) g2d.setFont(g2d.getFont().deriveFont((float)(fSize * viewport.heightScaleRatio())));
        Point point = viewport.originToScreen(new Point(x, y));
        g2d.drawString(text, point.x, point.y);
        g2d.setFont(g2d.getFont().deriveFont((float)fSize));
    }

}
