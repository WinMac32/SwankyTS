/*
Copyright 2014 Seth Traverse

This file is part of Project Sierra.

Project Sierra is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Project Sierra is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Project Sierra.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.gui.editor;

import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.Utils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

@SuppressWarnings("serial")
public class EditorPanel extends JPanel {

    private Tileset tileset;

    private boolean mouseDown;
    private Point mouseDownPoint;
    private Point mouseUpPoint;

    private int zoomlevel;

    public EditorPanel(final Tileset tileset) {
        this.tileset = tileset;

        setZoomLevel(1);
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
                    for (int i = tileset.getRegions().size() - 1; i >= 0; i--) {
                        if (tileset.getRegions().get(i).contains(Utils.adjustToNormal(mouseEvent.getPoint(), getZoomlevel()))) {
                            tileset.getRegions().remove(i);
                            repaint();
                            break;
                        }
                    }
                } else if ((mouseEvent.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) == MouseEvent.SHIFT_DOWN_MASK) {
                    for (Region region : tileset.getRegions()) {
                        if (region.contains(Utils.adjustToNormal(mouseEvent.getPoint(), getZoomlevel()))) {
                            String newName = JOptionPane.showInputDialog(getParent(), "Enter new region name", region.getName());
                            if (newName != null) {
                                region.setName(newName);
                                repaint();
                            }
                            break;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) != MouseEvent.CTRL_DOWN_MASK) {
                    mouseDown = true;
                    mouseDownPoint = confine(Utils.adjustToGrid(tileset, Utils.adjustToNormal(new Point(mouseEvent.getX(), mouseEvent.getY()), getZoomlevel())));
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (mouseDown) {
                    mouseDown = false;
                    mouseUpPoint = confine(Utils.adjustToGrid(tileset, Utils.adjustToNormal(new Point(mouseEvent.getX(), mouseEvent.getY()), getZoomlevel())));
                    if (Math.abs(mouseUpPoint.x - mouseDownPoint.x) > 1 && Math.abs(mouseUpPoint.y - mouseDownPoint.y) > 1) {
                        String name = JOptionPane.showInputDialog(getParent(), "Enter region name", "REGION");
                        if (name != null) {
                            tileset.getRegions().add(Utils.formRegion(mouseDownPoint, mouseUpPoint, name));
                        }
                    }
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == MouseEvent.CTRL_DOWN_MASK) {
                    deleteRectAt(mouseEvent.getPoint());
                } else {
                    mouseUpPoint = confine(Utils.adjustToGrid(tileset, Utils.adjustToNormal(new Point(mouseEvent.getX(), mouseEvent.getY()), getZoomlevel())));
                    repaint();
                    getParent().repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {

            }
        });
        
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double lastzoom = getZoomlevel();
                setZoomLevel(getZoomlevel() + -e.getWheelRotation());
                if (getZoomlevel() < 1) {
                    setZoomLevel(1);
                } else {
                    repaint();
                    JViewport viewport = (JViewport) getParent();
                    Point viewPos = viewport.getViewPosition();
                    double zoomChange = ((double)getZoomlevel()) / lastzoom;
                    viewport.setViewPosition(new Point((int)(((double)viewPos.x) * zoomChange), (int)(((double)viewPos.y) * zoomChange)));
                }
            }
        });
    }

    public Dimension getImageDimensions() {
        return new Dimension(tileset.getImage().getWidth(), tileset.getImage().getHeight());
    }

    public int getZoomlevel() {
        return zoomlevel;
    }

    public void deleteRectAt(Point p) {
        for (int i = tileset.getRegions().size() - 1; i >= 0; i--) {
            if (tileset.getRegions().get(i).contains(Utils.adjustToNormal(p, getZoomlevel()))) {
                tileset.getRegions().remove(i);
                repaint();
                break;
            }
        }
    }

    public void setZoomLevel(int zoomlevel) {
        this.zoomlevel = zoomlevel;
        Dimension newSize = new Dimension(tileset.getImage().getWidth() * zoomlevel, tileset.getImage().getHeight() * zoomlevel);
        setPreferredSize(newSize);
        setMinimumSize(newSize);
        setMaximumSize(newSize);
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int width = getPreferredSize().width;
        int height = getPreferredSize().height;

        int zoom = getZoomlevel();

        g2d.drawImage(tileset.getImage(), 0, 0, width, height, this);
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
        g2d.setPaint(Color.WHITE);
        for (Region region : tileset.getRegions()) {
            g2d.drawRect(region.x * zoom, region.y * zoom, region.width * zoom, region.height * zoom);
            g2d.drawString(Utils.adjustToWidth(g2d.getFont(), g2d.getFontRenderContext(), region.getName(), region.width * zoom), region.x * zoom, region.y * zoom + g2d.getFontMetrics().getHeight());
        }
        if (mouseDown) {
            g2d.setPaint(Color.RED);
            Rectangle rect = Utils.formRegion(mouseDownPoint, mouseUpPoint, "");
            g2d.drawRect(rect.x * zoom, rect.y * zoom, rect.width * zoom, rect.height * zoom);
        }
    }

    private Point confine(Point p) {
        int w = tileset.getImage().getWidth();
        int h = tileset.getImage().getHeight();
        return new Point((p.x > w ? w : (p.x < 0 ? 0 : p.x)), (p.y > h ? h : (p.y < 0 ? 0 : p.y)));
    }

}
