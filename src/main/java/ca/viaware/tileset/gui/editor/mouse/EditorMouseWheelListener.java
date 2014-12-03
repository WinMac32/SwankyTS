package ca.viaware.tileset.gui.editor.mouse;

import ca.viaware.tileset.gui.editor.ActionExecutor;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class EditorMouseWheelListener implements MouseWheelListener {

    private ActionExecutor actionExecutor;

    public EditorMouseWheelListener(ActionExecutor actionExecutor) {
        this.actionExecutor = actionExecutor;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        actionExecutor.zoom(-e.getWheelRotation(), e.getPoint());
    }
}
