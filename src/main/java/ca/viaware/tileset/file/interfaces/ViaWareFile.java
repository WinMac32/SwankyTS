package ca.viaware.tileset.file.interfaces;

import ca.viaware.tileset.file.FileInterface;
import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;

import java.io.*;
import java.util.ArrayList;

public class ViaWareFile extends FileInterface {

    public ViaWareFile() {
        super("ViaWare", new String[] {"regions"});
    }

    @Override
    protected void handleExport(Tileset tileset, OutputStream out) throws IOException {
        DataOutputStream output = new DataOutputStream(out);

        output.writeUTF(tileset.getImageFile().getName());

        for (Region region : tileset.getRegions()) {
            output.writeShort(region.x);
            output.writeShort(region.y);
            output.writeShort(region.width);
            output.writeShort(region.height);
            output.writeUTF(region.getName());
        }
        output.writeShort(-100);
    }

    @Override
    protected Tileset handleImport(File source, InputStream in) throws IOException {
        ArrayList<Region> regions = new ArrayList<Region>();
        DataInputStream input = new DataInputStream(in);

        String image = input.readUTF();

        while (true) {
            int[] read = new int[4];
            for (int i = 0; i < read.length; i++) {
                int r = input.readShort();
                if (r == -100) {
                    input.close();
                    return new Tileset(source, new File(image), regions);
                }
                read[i] = r;
            }
            regions.add(new Region(read[0], read[1], read[2], read[3], input.readUTF()));
        }
    }

}
