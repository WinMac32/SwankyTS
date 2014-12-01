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
package ca.viaware.tileset.gui.editor.render;

import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;

import java.awt.*;

public class Renderer {

    private Tileset tileset;
    private Graphics2D g2d;

    private int width;
    private int height;
    private int zoom;

    public Renderer(Tileset tileset, Graphics2D g2d, int width, int height, int zoom) {
        this.tileset = tileset;
        this.g2d = g2d;

        this.width = width;
        this.height = height;
        this.zoom = zoom;
    }

    public void renderGrid() {
        if (tileset.isAlignToGrid() && tileset.isShowingGrid()) {
            int gridX = tileset.getGridConfig().x;
            int gridY = tileset.getGridConfig().y;
            int gridWidth = tileset.getGridConfig().width;
            int gridHeight = tileset.getGridConfig().height;

            g2d.setPaint(Color.GREEN);

            for (int i = 0; i < (width / (gridWidth * zoom)); i++) {
                int x = i * (gridWidth * zoom) + (gridX * zoom);
                g2d.drawLine(x, 0, x, height);
            }

            for (int i = 0; i < (height / (gridHeight * zoom)); i++) {
                int y = i * (gridHeight * zoom) + (gridY * zoom);
                g2d.drawLine(0, y, width, y);
            }
        }
    }

    public void renderRegions() {
        for (Region region : tileset.getRegions()) {
            g2d.drawRect(region.x * zoom, region.y * zoom, region.width * zoom, region.height * zoom);
            g2d.drawString(Utils.adjustToWidth(g2d.getFont(), g2d.getFontRenderContext(), region.getName(), region.width * zoom), region.x * zoom, region.y * zoom + g2d.getFontMetrics().getHeight());
        }
    }

}
