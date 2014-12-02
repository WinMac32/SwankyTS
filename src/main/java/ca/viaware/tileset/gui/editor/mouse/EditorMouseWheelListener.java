package ca.viaware.tileset.gui.editor.mouse;

import ca.viaware.tileset.gui.editor.panel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.render.Viewport;
import ca.viaware.tileset.obj.Tileset;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class EditorMouseWheelListener implements MouseWheelListener {

    private Tileset tileset;
    private EditorGraphicsPanel graphicsPanel;
    private MouseInfo mouseInfo;
    private Viewport viewport;

    public EditorMouseWheelListener(Tileset tileset, EditorGraphicsPanel graphicsPanel, MouseInfo mouseInfo, Viewport viewport) {
        this.tileset = tileset;
        this.graphicsPanel = graphicsPanel;
        this.mouseInfo = mouseInfo;
        this.viewport = viewport;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int change = -e.getWheelRotation();
        mouseInfo.setZoomLevel(mouseInfo.getZoomLevel() + change);

        //TODO Scale around mouse pointer

        viewport.width = viewport.getOrigWidth() / mouseInfo.getZoomLevel();
        viewport.height = viewport.getOrigHeight() / mouseInfo.getZoomLevel();

        graphicsPanel.repaint();
    }
}
