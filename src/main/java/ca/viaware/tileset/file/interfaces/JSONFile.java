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
        JSONObject frames = new JSONObject();

        for (Region region : tileset.getRegions()) {
            JSONObject regionObject = new JSONObject();

            JSONObject frame = new JSONObject();
            frame.put("x", region.getX());
            frame.put("y", region.getY());
            frame.put("w", region.getWidth());
            frame.put("h", region.getHeight());
            regionObject.put("frame", frame);

            JSONObject spriteSourceSize = new JSONObject();
            spriteSourceSize.put("x", 0);
            spriteSourceSize.put("y", 0);
            spriteSourceSize.put("w", region.getWidth());
            spriteSourceSize.put("h", region.getHeight());
            regionObject.put("spriteSourceSize", spriteSourceSize);

            JSONObject sourceSize = new JSONObject();
            sourceSize.put("w", region.getWidth());
            sourceSize.put("h", region.getHeight());
            regionObject.put("sourceSize", sourceSize);

            regionObject.put("rotated", false);
            regionObject.put("trimmed", false);

            frames.put(region.getName(), regionObject);
        }

        JSONObject meta = new JSONObject();
        meta.put("app", "SwankyTS");
        meta.put("version", "0.0.2");
        meta.put("image", tileset.getImageFile().getName());
        meta.put("format", "RGBA8888");
        JSONObject size = new JSONObject();
        size.put("w", tileset.getImage().getWidth());
        size.put("h", tileset.getImage().getHeight());
        meta.put("size", size);
        meta.put("scale", "1");

        JSONObject root = new JSONObject();
        root.put("frames", frames);
        root.put("meta", meta);
        String json = root.toString(4);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(json);
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
