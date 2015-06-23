package ca.viaware.tileset.file;

import ca.viaware.api.logging.Log;
import ca.viaware.tileset.obj.Tileset;

import java.io.*;

public abstract class FileInterface {

    private String name;
    private String[] extensions;

    protected FileInterface(String name, String[] extensions) {
        this.name = name;
        this.extensions = extensions;

        Log.info("Registered export interface %0", name);
    }

    public boolean runExport(Tileset tileset, String outName) {
        File outFile = new File(outName);

        try {
            outFile.createNewFile();
            OutputStream out = new FileOutputStream(outFile);
            handleExport(tileset, out);
            out.close();
            Log.info("Wrote file %0", outFile.getAbsolutePath());
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

    public String[] getExtensions() {
        return extensions;
    }

}
