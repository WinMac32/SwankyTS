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
            handleExport(tileset, out);
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Tileset runImport(File sourceFile) throws IOException {
        return handleImport(sourceFile, new FileInputStream(sourceFile));
    }

    protected abstract void handleExport(Tileset tileset, OutputStream out) throws IOException;
    protected abstract Tileset handleImport(File source, InputStream in) throws IOException;

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

}
