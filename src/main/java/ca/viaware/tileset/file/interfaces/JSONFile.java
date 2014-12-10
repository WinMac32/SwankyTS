package ca.viaware.tileset.file.interfaces;

import ca.viaware.tileset.file.FileInterface;
import ca.viaware.tileset.obj.Region;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class JSONFile extends FileInterface {

    public JSONFile() {
        super("JSON", "json");
    }

    @Override
    protected void handleExport(ArrayList<Region> regions, OutputStream out) throws IOException {
        JSONWriter jsonWriter = new JSONWriter(new OutputStreamWriter(out));
        jsonWriter.object().key("frames").object();
        for (Region region : regions) {
            jsonWriter.key(region.getName()).object();

            jsonWriter.key("frame").object();
                jsonWriter.key("x").value(region.getX());
                jsonWriter.key("y").value(region.getY());
                jsonWriter.key("w").value(region.getWidth());
                jsonWriter.key("h").value(region.getHeight());
            jsonWriter.endObject();

            jsonWriter.key("rotated").value(false);
            jsonWriter.key("trimmed").value(false);

            jsonWriter.key("spriteSourceSize").object();
                jsonWriter.key("x").value(0);
                jsonWriter.key("y").value(0);
                jsonWriter.key("w").value(region.getWidth());
                jsonWriter.key("h").value(region.getHeight());
            jsonWriter.endObject();

            jsonWriter.key("sourceSize").object();
                jsonWriter.key("w").value(region.getWidth());
                jsonWriter.key("h").value(region.getHeight());
            jsonWriter.endObject();

            jsonWriter.endObject();
        }
        jsonWriter.endObject();
        jsonWriter.endObject();
    }

    @Override
    protected ArrayList<Region> handleImport(InputStream in) throws IOException {
        return null;
    }
}
