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

import java.awt.Rectangle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.Region;

public class FileUtils {

    public static void saveRegions(File file, ArrayList<Region> regions) {
        try {
            Log.info("Saving file %0", file.getAbsolutePath());
            if (!file.exists()) file.createNewFile();
            DataOutputStream output = new DataOutputStream(new FileOutputStream(file));

            for (Region region : regions) {
                output.writeShort(region.x);
                output.writeShort(region.y);
                output.writeShort(region.width);
                output.writeShort(region.height);
                output.writeUTF(region.getName());
            }
            output.writeShort(-100);
            
            output.close();
            
            Log.info("Done.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Region> loadRegions(File file) {
        Log.info("Loading file %0", file.getAbsolutePath());
        ArrayList<Region> regions = new ArrayList<Region>();

        if (!file.exists()) return regions;

        try {
            DataInputStream input = new DataInputStream(new FileInputStream(file));

            while (true) {
                int[] read = new int[4];
                for (int i = 0; i < read.length; i++) {
                    int r = input.readShort();
                    if (r == -100) {
                        input.close();
                        return regions;
                    }
                    read[i] = r;
                }
                regions.add(new Region(read[0], read[1], read[2], read[3], input.readUTF()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return regions;
    }

}
