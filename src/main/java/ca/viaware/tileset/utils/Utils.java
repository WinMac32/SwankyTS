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
package ca.viaware.tileset.utils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;

public class Utils {

    public static Point adjustToGrid(Tileset tileset, Point point) {
        if (!tileset.isAlignToGrid()) return point;

        Rectangle gridConfig = tileset.getGridConfig();

        int newX = point.x - ((point.x - (gridConfig.width / 2)) % gridConfig.width) + (gridConfig.width / 2);
        int newY = point.y - ((point.y - (gridConfig.height / 2)) % gridConfig.height) + (gridConfig.height / 2);

        if (point.x < gridConfig.width) newX = 0;
        if (point.y < gridConfig.height) newY = 0;

        newX += gridConfig.x;
        newY += gridConfig.y;

        return new Point(newX, newY);
    }

    public static Region formRegion(Point point1, Point point2, String name) {
        int width = Math.abs(point1.x - point2.x);
        int height = Math.abs(point1.y - point2.y);

        int x = (point1.x < point2.x ? point1.x : point2.x);
        int y = (point1.y < point2.y ? point1.y : point2.y);

        return new Region(x, y, width, height, name);
    }

    public static ArrayList<Region> generateRegionsFromGrid(Tileset tileset, int width, int height) {
    	ArrayList<Region> rectangles = new ArrayList<Region>();

        Rectangle gridConfig = tileset.getGridConfig();

    	for (int x = 0; x < width; x++) {
    		for (int y = 0; y < height; y++) {
    			rectangles.add(new Region((x * gridConfig.width) + gridConfig.x, (y * gridConfig.height) + gridConfig.y, gridConfig.width, gridConfig.height, "tile_" + x + "_" + y));
    		}
    	}
    	
    	return rectangles;
    }
    
    public static Point adjustToNormal(Point p, int scale) {
    	return new Point(p.x / scale, p.y / scale);
    }

    public static String adjustToWidth(Font font, FontRenderContext context, String string, int width) {
        String newString = "";
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            newString += chars[i];
            if (font.getStringBounds(newString + "...", context).getWidth() > width && i < chars.length - 1){
                newString += "...";
                break;
            }
        }
        return newString;
    }
}
