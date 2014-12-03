package ca.viaware.tileset.gui.editor.editorpanel.render;

import ca.viaware.tileset.gui.editor.editorpanel.EditorGraphicsPanel;

import java.awt.*;

public class Viewport extends Rectangle {

    private EditorGraphicsPanel graphicsPanel;

    private int viewHeight;
    private int viewWidth;

    public Viewport(int x, int y, int width, int height, EditorGraphicsPanel graphicsPanel) {
        super(x, y, width, height);
        this.viewWidth = width;
        this.viewHeight = height;
        this.graphicsPanel = graphicsPanel;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }


    public int getViewWidth() {
        return viewWidth;
    }

    public Point originToScreen(Point p) {
        return new Point((int)(p.x * widthScaleRatio() + this.x), (int)(p.y * heightScaleRatio() + this.y));
    }

    public Rectangle originToScreen(Rectangle rect) {
        Point newPoint = originToScreen(new Point(rect.x, rect.y));
        return new Rectangle(newPoint.x, newPoint.y, (int)(rect.getWidth() * widthScaleRatio()), (int)(rect.getHeight() * heightScaleRatio()));
    }

    public Point screenToOrigin(Point p) {
        return new Point((int)((p.x - this.x + (widthScaleRatio() / 2)) / widthScaleRatio()), (int)((p.y - this.y + (heightScaleRatio() / 2)) / heightScaleRatio()));
    }

    private double widthScaleRatio() {
        return (double) getViewWidth() / getWidth();
    }

    private double heightScaleRatio() {
        return (double) getViewHeight() / getHeight();
    }

}
