package ca.viaware.tileset.gui.editor;

import ca.viaware.api.gui.base.VButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabPanel extends JPanel implements ActionListener {

    private JTabbedPane parent;

    public TabPanel(String name, JTabbedPane parent) {
        this.parent = parent;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 20));
        setOpaque(false);

        add(new JLabel(name), BorderLayout.LINE_START);
        add(new VButton("x", this, ""), BorderLayout.LINE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.removeTabAt(parent.indexOfTabComponent(this));
    }
}
