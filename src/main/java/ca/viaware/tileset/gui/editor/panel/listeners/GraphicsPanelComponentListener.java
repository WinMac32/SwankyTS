package ca.viaware.tileset.gui.editor.panel.listeners;

import ca.viaware.tileset.gui.editor.panel.EditorGraphicsPanel;
import ca.viaware.tileset.gui.editor.render.Viewport;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GraphicsPanelComponentListener implements ComponentListener {

    private Viewport viewport;
    private EditorGraphicsPanel graphicsPanel;

    public GraphicsPanelComponentListener(Viewport viewport, EditorGraphicsPanel graphicsPanel) {
        this.viewport = viewport;
        this.graphicsPanel = graphicsPanel;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        viewport.setOrigWidth(graphicsPanel.getWidth());
        viewport.setOrigHeight(graphicsPanel.getHeight());
        graphicsPanel.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
