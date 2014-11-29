package ca.viaware.tileset.gui.control;

import ca.viaware.api.gui.base.VButton;

import javax.swing.*;
import java.awt.*;

public class ControlWindow {

    private JFrame frame;

    public ControlWindow() {
        setFrame(new JFrame("Tileset Creator"));
        initFrame();
    }

    private void initFrame() {
        getFrame().setSize(400, 100);
        getFrame().setResizable(false);
        getFrame().getContentPane().setLayout(new FlowLayout());

        //TODO Verify saved
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getFrame().getContentPane().add(new VButton("Open", new ControlListener(), "OPEN"));
    }

    private JFrame getFrame() {
        return frame;
    }

    private void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void show() {
        getFrame().setVisible(true);
    }

}
