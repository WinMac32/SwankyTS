package ca.viaware.tileset.file.interfaces;

import ca.viaware.tileset.file.FileInterface;
import ca.viaware.tileset.obj.Region;
import ca.viaware.tileset.obj.Tileset;
import ca.viaware.tileset.utils.FileUtils;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JSONFile extends FileInterface {

    public JSONFile() {
        super("JSON", "json");
    }

    @Override
    protected void handleExport(Tileset tileset, OutputStream out) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        JSONWriter jsonWriter = new JSONWriter(writer);
        jsonWriter.object().key("frames").object();
        writer.newLine();
        for (Region region : tileset.getRegions()) {
            jsonWriter.key(region.getName()).object();
            writer.newLine();

            jsonWriter.key("frame").object();
                jsonWriter.key("x").value(region.getX());
                jsonWriter.key("y").value(region.getY());
                jsonWriter.key("w").value(region.getWidth());
                jsonWriter.key("h").value(region.getHeight());
            jsonWriter.endObject();
            writer.newLine();

            jsonWriter.key("rotated").value(false);
            writer.newLine();

            jsonWriter.key("trimmed").value(false);
            writer.newLine();

            jsonWriter.key("spriteSourceSize").object();
                jsonWriter.key("x").value(0);
                jsonWriter.key("y").value(0);
                jsonWriter.key("w").value(region.getWidth());
                jsonWriter.key("h").value(region.getHeight());
            jsonWriter.endObject();
            writer.newLine();

            jsonWriter.key("sourceSize").object();
                jsonWriter.key("w").value(region.getWidth());
                jsonWriter.key("h").value(region.getHeight());
            jsonWriter.endObject();
            writer.newLine();

            jsonWriter.endObject();
            writer.newLine();
        }
        jsonWriter.endObject();
        writer.newLine();

        jsonWriter.key("meta").object();
            jsonWriter.key("app").value("SwankyTS");
            jsonWriter.key("version").value("0.0.1");
            jsonWriter.key("image").value(tileset.getImageFile().getName());
            jsonWriter.key("format").value("RGBA8888"); //TODO this actually needs to be representative of image fmt
            jsonWriter.key("size").object();
                jsonWriter.key("w").value(tileset.getImage().getWidth());
                jsonWriter.key("h").value(tileset.getImage().getHeight());
            jsonWriter.endObject();
            jsonWriter.key("scale").value("1");
        jsonWriter.endObject();

        jsonWriter.endObject();
        writer.flush();
    }

    @Override
    protected Tileset handleImport(File source, InputStream in) throws IOException {
        String jsonString = "";
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            jsonString += scanner.nextLine();
        }
        JSONObject json = new JSONObject(jsonString);

        ArrayList<Region> regions = new ArrayList<Region>();
        JSONObject frames = json.getJSONObject("frames");

        for (String key : frames.keySet()) {
            JSONObject frame = frames.getJSONObject(key);
            JSONObject dim = frame.getJSONObject("frame");
            Region region = new Region(dim.getInt("x"), dim.getInt("y"), dim.getInt("w"), dim.getInt("h"), key);
            regions.add(region);
        }

        JSONObject meta = json.getJSONObject("meta");
        String image = meta.getString("image");

        return new Tileset(source, new File(image), regions);
    }
}
