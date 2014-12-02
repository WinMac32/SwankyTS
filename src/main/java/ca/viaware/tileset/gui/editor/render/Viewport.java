package ca.viaware.tileset.gui.editor.render;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.gui.editor.panel.EditorGraphicsPanel;

import java.awt.*;

public class Viewport extends Rectangle {

    private EditorGraphicsPanel graphicsPanel;

    private int origHeight;
    private int origWidth;

    public Viewport(int x, int y, int width, int height, EditorGraphicsPanel graphicsPanel) {
        super(x, y, width, height);
        this.origWidth = width;
        this.origHeight = height;
        this.graphicsPanel = graphicsPanel;
    }

    public void setOrigHeight(int origHeight) {
        this.origHeight = origHeight;
    }

    public int getOrigHeight() {
        return origHeight;
    }

    public void setOrigWidth(int origWidth) {
        this.origWidth = origWidth;
    }


    public int getOrigWidth() {
        return origWidth;
    }

    public Point transformToViewport(Point p) {
        return new Point((int)((double)p.x * widthScaleRatio() + (double)this.x), (int)((double)p.y * heightScaleRatio() + (double)this.y));
    }

    public Rectangle transformToViewport(Rectangle rect) {
        Point newPoint = transformToViewport(new Point(rect.x, rect.y));
        return new Rectangle(newPoint.x, newPoint.y, (int)(rect.getWidth() * widthScaleRatio()), (int)(rect.getHeight() * heightScaleRatio()));
    }

    public Point transformFromViewport(Point p) {
        return new Point((int)((double)(p.x - this.x) / widthScaleRatio()), (int)((double)(p.y - this.y) / heightScaleRatio()));
    }

    public Rectangle transformFromViewport(Rectangle rect) {
        Point newPoint = transformFromViewport(new Point(rect.x, rect.y));
        return new Rectangle(newPoint.x, newPoint.y, (int)(rect.getWidth() / widthScaleRatio()), (int)(rect.getHeight() / heightScaleRatio()));
    }

    private double widthScaleRatio() {
        return (double)getOrigWidth() / getWidth();
    }

    private double heightScaleRatio() {
        return (double)getOrigHeight() / getHeight();
    }

}
