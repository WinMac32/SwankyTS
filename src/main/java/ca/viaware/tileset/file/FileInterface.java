package ca.viaware.tileset.file;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;

import java.io.*;
import java.util.ArrayList;

public abstract class FileInterface {

    private String name;
    private String extension;

    protected FileInterface(String name, String extension) {
        this.name = name;
        this.extension = extension;

        Log.info("Registered export interface %0", name);
    }

    public boolean runExport(Tileset tileset, String outName) {
        File outFile = new File(outName + "." + extension);
        try {
            outFile.createNewFile();
            OutputStream out = new FileOutputStream(outFile);
            handleExport(tileset.getRegions(), out);
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected abstract void handleExport(ArrayList<Region> regions, OutputStream out) throws IOException;
    protected abstract ArrayList<Region> handleImport(InputStream in) throws IOException;

    public String getName() {
        return name;
    }

}
