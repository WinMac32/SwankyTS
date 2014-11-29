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
package ca.viaware.tileset;

import java.awt.*;

public class Region extends Rectangle {

    private String name;

    public Region(int x, int y, int width, int height, String name) {
        super(x, y, width, height);
        this.name = name;
    }

    public Region(int x, int y, int width, int height) {
        this(x, y, width, height, "REGION");
    }

    public Region() {
        this(0, 0, 0, 0);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
