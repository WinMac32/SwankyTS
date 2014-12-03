package ca.viaware.tileset.gui.editor.panel.listeners;

import ca.viaware.tileset.gui.editor.ActionExecutor;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GraphicsPanelComponentListener implements ComponentListener {

    private ActionExecutor actionExecutor;

    public GraphicsPanelComponentListener(ActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        actionExecutor.resizeViewport(e.getComponent().getWidth(), e.getComponent().getHeight());
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
