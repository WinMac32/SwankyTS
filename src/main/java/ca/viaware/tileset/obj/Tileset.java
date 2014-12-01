/*
Copyright 2014 Seth Traverse

This file is part of SwankyTS.

SwankyTS is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SwankyTS is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Project Sierra.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.viaware.tileset.obj;

import ca.viaware.tileset.utils.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tileset {
    private File dataFile;
    private File imageFile;

    private BufferedImage image;

    private ArrayList<Region> regions;

    private boolean isShowingGrid;
    private boolean isAlignToGrid;
    private Rectangle gridConfig;

    public Tileset(File dataFile, File imageFile) {
        setDataFile(dataFile);
        setImageFile(imageFile);
    }

    public void loadImage() throws IOException {
        setImage(ImageIO.read(getImageFile()));
        setRegions(FileUtils.loadRegions(getDataFile()));
    }

    public File getDataFile() {
        return dataFile;
    }

    private void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }

    public File getImageFile() {
        return imageFile;
    }

    private void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public BufferedImage getImage() {
        return image;
    }

    private void setImage(BufferedImage image) {
        this.image = image;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    private void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public boolean isShowingGrid() {
        return isShowingGrid;
    }

    public void setShowingGrid(boolean isShowingGrid) {
        this.isShowingGrid = isShowingGrid;
    }

    public boolean isAlignToGrid() {
        return isAlignToGrid;
    }

    public void setAlignToGrid(boolean isAlignToGrid) {
        this.isAlignToGrid = isAlignToGrid;
    }

    public Rectangle getGridConfig() {
        return gridConfig;
    }

    public void setGridConfig(Rectangle gridConfig) {
        this.gridConfig = gridConfig;
    }
}
