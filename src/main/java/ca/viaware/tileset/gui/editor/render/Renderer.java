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
